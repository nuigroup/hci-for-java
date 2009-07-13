package nui.squirt.component;

import java.awt.Color;

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

	public void update() {}
	
	@Override
	public void preRender(PApplet p) {
		super.preRender(p);
		p.textAlign(PApplet.CENTER, PApplet.CENTER);
		
		setWidth(p.textWidth(getText()));
		setHeight(p.textAscent()+p.textDescent());
	}

	public void render(PApplet p) {
		p.text(getText(), 0, 0);
	}

}
