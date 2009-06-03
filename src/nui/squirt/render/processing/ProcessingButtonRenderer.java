package nui.squirt.render.processing;

import processing.core.PApplet;
import nui.squirt.component.Button;
import nui.squirt.component.Label;
import nui.squirt.render.ButtonRenderer;
import nui.squirt.render.RenderingEngine;

public class ProcessingButtonRenderer extends ButtonRenderer {

	public ProcessingButtonRenderer(RenderingEngine e, Button b) {
		super(e, b);
	}

	public void prepare() {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.performTranslation(getComponent());
	}

	public void draw() {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		PApplet pApplet = engine.getPApplet();
		
		Button button = (Button) getComponent();
		Label l = button.getLabel();
		
		pApplet.textFont(engine.getFont());
		pApplet.textMode(PApplet.MODEL);
		button.setWidth(pApplet.textWidth(l.getText()) + 10);
		button.setHeight(pApplet.textAscent() + pApplet.textDescent() + 10);
		pApplet.fill(170);
		pApplet.stroke(230);
		pApplet.rect(0, 0, button.getWidth(), button.getHeight());
	}

	public void postDraw() {
		ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
		engine.undoTranslation();
	}

}
