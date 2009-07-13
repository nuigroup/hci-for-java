package nui.squirt.component;

import java.awt.Color;

import nui.squirt.ControlPoint;
import nui.squirt.util.AffineTransformStack;

import processing.core.PApplet;

public class Circle extends AbstractComponent {
	
	private float radius;
	
	private Color fillColor = Color.BLUE;
	private Color strokeColor = Color.BLACK;
	private float strokeWeight;

	public Circle(float x, float y, float r) {
		super(x, y);
		this.radius = r;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	public float getStrokeWeight() {
		return strokeWeight;
	}

	public void setStrokeWeight(float strokeWeight) {
		this.strokeWeight = strokeWeight;
	}

	public void update(AffineTransformStack s) {}

	public void preRender(PApplet p, AffineTransformStack s) {
		update(s);
		
		p.ellipseMode(PApplet.CENTER);
		p.fill(getFillColor().getRGB());
		p.stroke(getStrokeColor().getRGB());
		p.strokeWeight(getStrokeWeight());
		
		p.pushMatrix();
		
		p.translate(getX(), getY());
	}

	public void postRender(PApplet p, AffineTransformStack s) {
		p.popMatrix();
	}

	public void render(PApplet p, AffineTransformStack s) {
		p.ellipse(0, 0, getRadius()*2, getRadius()*2);
	}

	public boolean canAcceptMoreControlPoints() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean offer(ControlPoint cp, AffineTransformStack s) {
		// TODO Auto-generated method stub
		return false;
	}

}
