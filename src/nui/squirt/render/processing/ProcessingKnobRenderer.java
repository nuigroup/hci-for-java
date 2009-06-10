package nui.squirt.render.processing;

import processing.core.PApplet;
import nui.squirt.component.Knob;
import nui.squirt.render.KnobRenderer;
import nui.squirt.render.RenderingEngine;

public class ProcessingKnobRenderer extends KnobRenderer {

	public ProcessingKnobRenderer(RenderingEngine e) {
		super(e);
	}

	@Override
	public void prepare(Knob k) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.performTranslation(k);
	}

	@Override
	public void draw(Knob k) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		PApplet pApplet = engine.getPApplet();
		
		pApplet.stroke(25);
		pApplet.fill(75);
		pApplet.ellipseMode(PApplet.CENTER);
		
		float r = k.getRadius();
		
		pApplet.ellipse(0, 0, r, r);
		
		pApplet.noStroke();
		pApplet.fill(100);
		
		float indicatorRadius = Math.max(r * ((float) 0.05), 5);
		
		pApplet.ellipse(0, r * ((float) -0.425), indicatorRadius, indicatorRadius);
	}

	@Override
	public void postDraw(Knob k) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.undoTranslation();
	}

}
