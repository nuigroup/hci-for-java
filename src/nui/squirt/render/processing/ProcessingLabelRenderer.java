package nui.squirt.render.processing;

import nui.squirt.Context;
import nui.squirt.component.Label;
import nui.squirt.context.spatial.RectangularRegionContext;
import nui.squirt.render.LabelRenderer;
import nui.squirt.render.RenderingEngine;
import processing.core.PApplet;

public class ProcessingLabelRenderer extends LabelRenderer {

	public ProcessingLabelRenderer(RenderingEngine e) {
		super(e);
	}

//	public void prepare(Label l) {
//		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
//		engine.performTranslation(l);
//	}
//
//	public void draw(Label l) {
//		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
//		PApplet pApplet = engine.getPApplet();
//		
//		pApplet.textFont(engine.getFont());
//		pApplet.textMode(PApplet.MODEL);
//		pApplet.textAlign(PApplet.CENTER, PApplet.CENTER);
//		pApplet.fill(25);
//		pApplet.text(l.getText(), 0, 0);
//	}
//
//	public void postDraw(Label l) {
//		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
//		engine.undoTranslation();
//	}
	
	public void render(Label l, Context t) {
		if (t instanceof RectangularRegionContext) {
			RectangularRegionContext r = (RectangularRegionContext) t;
			
			ProcessingRenderingEngine e = (ProcessingRenderingEngine) getRenderingEngine();
			PApplet p = e.getPApplet();
			
			// Save the current matrix transform so we can step back to it
			p.pushMatrix();
			
			// Perform translation
			p.translate(r.getX(), r.getY());
			p.rotate(r.getRotation());
			p.scale(r.getScale());
			
			// Draw Label
			p.textFont(e.getFont());
			p.textMode(PApplet.MODEL);
			p.textAlign(PApplet.CENTER, PApplet.CENTER);
			p.fill(25);
			p.text(l.getText(), 0, 0);
			
			// Undo transform
			p.popMatrix();
		}
	}

}
