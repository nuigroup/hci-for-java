package nui.squirt.render.processing;

import nui.squirt.Context;
import nui.squirt.component.Button;
import nui.squirt.render.ButtonRenderer;
import nui.squirt.render.RenderingEngine;

public class ProcessingButtonRenderer extends ButtonRenderer {

	public ProcessingButtonRenderer(RenderingEngine e) {
		super(e);
	}

//	public void prepare(Button b) {
//		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
//		engine.performTranslation(b);
//	}
//
//	public void draw(Button b) {
//		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
//		PApplet pApplet = engine.getPApplet();
//		
//		Label l = b.getLabel();
//		
//		pApplet.textFont(engine.getFont());
//		pApplet.textMode(PApplet.MODEL);
//		pApplet.rectMode(PApplet.CENTER);
//		
//		float w = pApplet.textWidth(l.getText()) * ((float) 1.4);
//		float h = pApplet.textAscent() + pApplet.textDescent() * ((float) 1.6);
//		
//		b.getLabel().setY(h * ((float) -0.1));
//		
//		pApplet.fill(b.isPressed() ? 170: 200);
//		pApplet.stroke(50);
//		pApplet.rect(0, 0, w, h);
//	}
//
//	public void postDraw(Button b) {
//		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
//		engine.undoTranslation();
//	}

	@Override
	public void render(Button b, Context t) {
//		if (t instanceof RectangularRegionContext) {
//			RectangularRegionContext r = (RectangularRegionContext) t;
//			
//			ProcessingRenderingEngine e = (ProcessingRenderingEngine) getRenderingEngine();
//			PApplet p = e.getPApplet();
//			
//			// Save the current matrix transform so we can step back to it
//			p.pushMatrix();
//			
//			// Perform translation
//			p.translate(r.getX(), r.getY());
//			p.rotate(r.getRotation());
//			p.scale(r.getScale());
//			
//			// Draw the Button
//			Label l = b.getLabel();
//			
//			p.textFont(e.getFont());
//			p.textMode(PApplet.MODEL);
//			p.rectMode(PApplet.CENTER);
//			
//			b.setPreferred
//			
//			b.getLabel().setY(h * ((float) -0.1));
//			
//			p.fill(b.isPressed() ? 170: 200);
//			p.stroke(50);
//			p.rect(0, 0, w, h);
//			
//			// Undo transform
//			p.popMatrix();
//		}
	}

}
