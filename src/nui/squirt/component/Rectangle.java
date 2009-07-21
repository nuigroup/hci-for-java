package nui.squirt.component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import nui.squirt.ControlPoint;
import processing.core.PApplet;
import processing.core.PVector;

public class Rectangle extends AbstractComponent {
	
	private float width;
	private float height;
	
	private Color fillColor = Color.GREEN;
	private Color strokeColor = Color.BLACK;
	private float strokeWeight = 1;
	
	private Collection<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
	private static final int MAX_CONTROL_POINTS = 1;

	public Rectangle(float x, float y, float w, float h) {
		super(x, y);
		this.width = w;
		this.height = h;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color c) {
		this.fillColor = c;
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

	public void update() {}

	public void preRender(PApplet p) {
		super.preRender(p);
		
		p.rectMode(PApplet.CENTER);
		p.fill(getFillColor().getRGB());
		p.stroke(getStrokeColor().getRGB());
		p.strokeWeight(getStrokeWeight());
	}

	public void render(PApplet p) {
		p.rect(0, 0, getWidth(), getHeight());
	}

	public boolean canAcceptMoreControlPoints() {
		return controlPoints.size() <= MAX_CONTROL_POINTS;
	}

	public boolean offer(ControlPoint cp) {
		if (!canAcceptMoreControlPoints()) return false;
		
		PVector l = transformToLocalSpace(new PVector(cp.getX(), cp.getY()));
		return (l.x > -getWidth()/2 && l.x < getWidth()/2 && l.y > -getHeight()/2 && l.y < getHeight()/2);
	}

	public void controlPointCreated(ControlPoint cp) {
		controlPoints.add(cp);
	}

	public void controlPointDied(ControlPoint cp) {
		controlPoints.remove(cp);
	}

	public void controlPointUpdated(ControlPoint cp) {
		PVector newPos = transformToLocalSpace(new PVector(cp.getX(), cp.getY()));
		PVector oldPos = transformToLocalSpace(new PVector(cp.getPreviousX(), cp.getPreviousY()));
		float diffX = newPos.x - oldPos.x;
		float diffY = newPos.y - oldPos.y;
		
		switch (controlPoints.size()) {
			case 1:
				getTransformMatrix().translate(diffX, diffY);
				break;
			case 2:
				ControlPoint other = null;
				for (ControlPoint p: controlPoints) {
					if (!cp.equals(p)) {
						other = p;
					}
				}
				PVector anchor = transformToLocalSpace(new PVector(other.getX(), other.getY()));
				PVector diffNew = PVector.sub(newPos, anchor);
				PVector diffOld = PVector.sub(oldPos, anchor);
				double angle = Math.atan2(diffNew.y, diffNew.x) - Math.atan2(diffOld.y, diffOld.x);
				float diffMag = diffNew.mag()/diffOld.mag();
				
				getTransformMatrix().translate(anchor.x, anchor.y);
				getTransformMatrix().rotate(angle, 0, 0);
				getTransformMatrix().scale(diffMag, diffMag);
				getTransformMatrix().translate(-anchor.x, -anchor.y);
		}
	}

}
