package nui.squirt.render.processing;

import processing.core.PApplet;
import nui.squirt.component.Slider;
import nui.squirt.render.RenderingEngine;
import nui.squirt.render.SliderRenderer;

public class ProcessingSliderRenderer extends SliderRenderer {

	public ProcessingSliderRenderer(RenderingEngine e) {
		super(e);
	}

	@Override
	public void prepare(Slider s) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.performTranslation(s);
	}

	@Override
	public void draw(Slider s) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		PApplet pApplet = engine.getPApplet();
		
		pApplet.stroke(25);
		pApplet.fill(75);
		pApplet.rectMode(PApplet.CENTER);
		
		pApplet.rect(0, 0, 5, s.getLength());
		
		float h = -(s.getValue()-s.getCenterValue()) / s.getValueRange() * s.getLength();
		
		pApplet.stroke(25);
		pApplet.rect(0, h, s.getThumbWidth(), s.getThumbHeight());
		pApplet.stroke(100);
		pApplet.line(-s.getThumbWidth()/2, h, s.getThumbWidth()/2, h);
	}

	@Override
	public void postDraw(Slider s) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.undoTranslation();
	}

}
