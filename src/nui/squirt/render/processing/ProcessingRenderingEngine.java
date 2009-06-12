package nui.squirt.render.processing;

import nui.squirt.Component;
import nui.squirt.Scalable;
import nui.squirt.component.Button;
import nui.squirt.component.Frame;
import nui.squirt.component.Image;
import nui.squirt.component.Knob;
import nui.squirt.component.Label;
import nui.squirt.component.Slider;
import nui.squirt.render.AbstractRenderingEngine;
import nui.squirt.render.ButtonRenderer;
import nui.squirt.render.FrameRenderer;
import nui.squirt.render.ImageRenderer;
import nui.squirt.render.KnobRenderer;
import nui.squirt.render.LabelRenderer;
import nui.squirt.render.SliderRenderer;
import processing.core.PApplet;
import processing.core.PFont;

public class ProcessingRenderingEngine extends AbstractRenderingEngine {
	
	private PApplet pApplet;
	private PFont font;
	
	private FrameRenderer frameRenderer;
	private ButtonRenderer buttonRenderer;
	private LabelRenderer labelRenderer;
	private KnobRenderer knobRenderer;
	private SliderRenderer sliderRenderer;
	private ImageRenderer imageRenderer;

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
		if (frameRenderer == null)
			frameRenderer = new ProcessingFrameRenderer(this);
		return frameRenderer;
	}

	public ButtonRenderer getButtonRenderer(Button b) {
		if (buttonRenderer == null)
			buttonRenderer = new ProcessingButtonRenderer(this);
		return buttonRenderer;
	}

	public LabelRenderer getLabelRenderer(Label l) {
		if (labelRenderer == null)
			labelRenderer = new ProcessingLabelRenderer(this);
		return labelRenderer;
	}
	
	public KnobRenderer getKnobRenderer(Knob k) {
		if (knobRenderer == null)
			knobRenderer = new ProcessingKnobRenderer(this);
		return knobRenderer;
	}

	public SliderRenderer getSliderRenderer(Slider s) {
		if (sliderRenderer == null)
			sliderRenderer = new ProcessingSliderRenderer(this);
		return sliderRenderer;
	}

	public ImageRenderer getImageRenderer(Image i) {
		if (imageRenderer == null)
			imageRenderer = new ProcessingImageRenderer(this);
		return imageRenderer;
	}

	public void performTranslation(Component c) {
		// Save the current matrix transform so we can step back to it
		pApplet.pushMatrix();
		
		// Perform translation
		pApplet.translate(c.getX(), c.getY());
		pApplet.rotate(c.getRotation());
		if (c instanceof Scalable)
			pApplet.scale(((Scalable) c).getScale());
	}

	public void undoTranslation() {
		// Restore the old matrix transform
		pApplet.popMatrix();
	}

}
