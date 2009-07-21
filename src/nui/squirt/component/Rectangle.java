package nui.squirt.component;

import java.awt.Color;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;

import nui.squirt.ControlPoint;
import nui.squirt.util.AffineTransformStack;
import processing.core.PApplet;

public class Rectangle extends AbstractComponent {
	
	private float rotation;
	private float width;
	private float height;
	
	private Color fillColor = Color.GREEN;
	private Color strokeColor = Color.BLACK;
	private float strokeWeight = 1;
	
	private Collection<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
	private static final int MAX_CONTROL_POINTS = 2;

	public Rectangle(float x, float y, float w, float h) {
		super(x, y);
		this.width = w;
		this.height = h;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
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

	public void update(AffineTransformStack s) {		
		s.pushTransform();
		s.translate(getX(), getY());
		s.rotate(getRotation());
	}

	public void preRender(PApplet p, AffineTransformStack s) {
		update(s);
		
		p.rectMode(PApplet.CENTER);
		p.fill(getFillColor().getRGB());
		p.stroke(getStrokeColor().getRGB());
		p.strokeWeight(getStrokeWeight());
		
		p.pushMatrix();
		
		p.translate(getX(), getY());
		p.rotate(getRotation());
	}
	
	public void postRender(PApplet p, AffineTransformStack s) {
		p.popMatrix();
		s.popTransform();
	}

	public void render(PApplet p, AffineTransformStack s) {
		p.rect(0, 0, getWidth(), getHeight());
	}

	public boolean canAcceptMoreControlPoints() {
		return controlPoints.size() <= MAX_CONTROL_POINTS;
	}

	public boolean offer(ControlPoint cp, AffineTransformStack s) {
		if (!canAcceptMoreControlPoints()) return false;
		
		s.pushTransform();
		s.translate(getX(), getY());
		s.rotate(getRotation());
		
		try {
			Point2D xy = s.inverseTransform(cp.getX(), cp.getY());
			if (xy.getX() > -getWidth()/2 && xy.getX() < getWidth()/2 && xy.getY() > -getHeight()/2 && xy.getY() < getHeight()/2) {
				s.popTransform();
				return true;
			}
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		s.popTransform();
		return false;
	}

	public void controlPointCreated(ControlPoint cp) {
		controlPoints.add(cp);
	}

	public void controlPointDied(ControlPoint cp) {
		controlPoints.remove(cp);
	}

	public void controlPointUpdated(ControlPoint cp) {
		switch (controlPoints.size()) {
			case 1:
				float diffX = cp.getX() - cp.getPreviousX();
				float diffY = cp.getY() - cp.getPreviousY();
				setX(getX() + diffX);
				setY(getY() + diffY);
				break;
			case 2:
				ControlPoint other;
				for (ControlPoint p: controlPoints) {
					if (!cp.equals(p)) {
						other = p;
					}
				}
				// TODO add matrix manipulation code
		}
	}

}
