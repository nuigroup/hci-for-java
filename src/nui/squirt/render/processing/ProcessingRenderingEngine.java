package nui.squirt.render.processing;

import nui.squirt.Component;
import nui.squirt.component.Button;
import nui.squirt.component.Frame;
import nui.squirt.component.Label;
import nui.squirt.render.AbstractRenderingEngine;
import nui.squirt.render.ButtonRenderer;
import nui.squirt.render.FrameRenderer;
import nui.squirt.render.LabelRenderer;
import processing.core.PApplet;
import processing.core.PFont;

public class ProcessingRenderingEngine extends AbstractRenderingEngine {
	
	private PApplet pApplet;
	private PFont font;

	public ProcessingRenderingEngine(PApplet p) {
		this.setPApplet(p);
		PFont f = p.createFont("Helvetica", 20);
		this.setFont(f);
		this.getPApplet().textFont(f);
	}

	public PApplet getPApplet() {
		return pApplet;
	}

	public void setPApplet(PApplet pApplet) {
		this.pApplet = pApplet;
	}

	public PFont getFont() {
		return font;
	}

	public void setFont(PFont font) {
		this.font = font;
	}

	public FrameRenderer getFrameRenderer(Frame f) {
		return new ProcessingFrameRenderer(this, f);
	}

	public ButtonRenderer getButtonRenderer(Button b) {
		return new ProcessingButtonRenderer(this, b);
	}

	public LabelRenderer getLabelRenderer(Label l) {
		return new ProcessingLabelRenderer(this, l);
	}

	public void performTranslation(Component c) {
		// Save the current matrix transform so we can step back to it
		pApplet.pushMatrix();
		
		// Perform translation
		pApplet.translate(c.getX(), c.getY());
		pApplet.rotate(c.getRotation());
		pApplet.scale(c.getScale());
	}

	public void undoTranslation() {
		// Restore the old matrix transform
		pApplet.popMatrix();
	}

}
