package nui.squirt.render.processing;

import nui.squirt.component.Label;
import nui.squirt.render.LabelRenderer;
import nui.squirt.render.RenderingEngine;
import processing.core.PApplet;

public class ProcessingLabelRenderer extends LabelRenderer {

	public ProcessingLabelRenderer(RenderingEngine e, Label l) {
		super(e, l);
	}

	public void prepare() {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.performTranslation(getComponent());
	}

	public void draw() {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		PApplet pApplet = engine.getPApplet();
		
		Label label = (Label) getComponent();
		pApplet.textFont(engine.getFont());
		pApplet.textMode(PApplet.MODEL);
		pApplet.textAlign(PApplet.CENTER, PApplet.CENTER);
		pApplet.fill(25);
		pApplet.text(label.getText(), 0, 0);
	}

	public void postDraw() {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.undoTranslation();
	}

}
