package nui.squirt.render.processing;

import nui.squirt.component.Label;
import nui.squirt.render.LabelRenderer;
import nui.squirt.render.RenderingEngine;
import processing.core.PApplet;

public class ProcessingLabelRenderer extends LabelRenderer {

	public ProcessingLabelRenderer(RenderingEngine e) {
		super(e);
	}

	public void prepare(Label l) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.performTranslation(l);
	}

	public void draw(Label l) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		PApplet pApplet = engine.getPApplet();
		
		pApplet.textFont(engine.getFont());
		pApplet.textMode(PApplet.MODEL);
		pApplet.textAlign(PApplet.CENTER, PApplet.CENTER);
		pApplet.fill(25);
		pApplet.text(l.getText(), 0, 0);
	}

	public void postDraw(Label l) {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.undoTranslation();
	}

}
