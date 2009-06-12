package nui.squirt.render.processing;

import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PImage;
import nui.squirt.component.Image;
import nui.squirt.render.ImageRenderer;
import nui.squirt.render.RenderingEngine;

public class ProcessingImageRenderer extends ImageRenderer {
	
	private static Map<String, PImage> images = new HashMap<String, PImage>();

	public ProcessingImageRenderer(RenderingEngine e) {
		super(e);
	}

	@Override
	public void prepare(Image i) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.performTranslation(i);
	}

	@Override
	public void draw(Image i) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		PApplet pApplet = engine.getPApplet();
		
		// Retrieve the image
		PImage p = images.get(i.getImagePath());
		if (p == null) {
			images.put(i.getImagePath(), pApplet.loadImage(i.getImagePath()));
			p = images.get(i.getImagePath());
		}
		
		// Set up PApplet
		pApplet.imageMode(PApplet.CENTER);
		pApplet.noTint();
		
		// Draw the image
		pApplet.image(p, 0, 0, i.getWidth(), i.getHeight());
	}

	@Override
	public void postDraw(Image i) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.undoTranslation();
	}

}
