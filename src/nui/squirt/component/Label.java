package nui.squirt.component;

import java.awt.Color;

import nui.squirt.util.AffineTransformStack;
import processing.core.PApplet;


public class Label extends Rectangle {

	protected String text;

	public Label(float x, float y, String text) {
		super(x, y, 0, 0);
		this.text = text;
		setFillColor(Color.BLACK);
		setStrokeColor(Color.BLACK);
	}

	public String getText() {
		return text;
	}

	public void setText(String t) {
		this.text = t;
	}

	public void update(AffineTransformStack s) {
		super.update(s);
	}
	
	@Override
	public void preRender(PApplet p, AffineTransformStack s) {
		super.preRender(p, s);
		p.textAlign(PApplet.CENTER, PApplet.CENTER);
		
		setWidth(p.textWidth(getText()));
		setHeight(p.textAscent()+p.textDescent());
	}

	public void render(PApplet p, AffineTransformStack s) {
		p.text(getText(), 0, 0);
	}

}
