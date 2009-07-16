package nui.squirt.component;

import java.awt.Color;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import nui.squirt.Component;
import nui.squirt.ControlPoint;
import nui.squirt.util.AffineTransformStack;
import processing.core.PApplet;

public class Rectangle extends AbstractComponent implements Component {
	
	private float rotation;
	private float width;
	private float height;
	
	private Color fillColor = Color.GREEN;
	private Color strokeColor = Color.BLACK;
	private float strokeWeight = 1;
	
	private Collection<ControlPoint> contorlPoints = new ArrayList<ControlPoint>();
	private static final int MAX_CONTROL_POINTS = 1;

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
		
		for (Iterator<ControlPoint> i = getControlPoints().iterator(); i.hasNext();) {
			ControlPoint cp = i.next();
			if (cp.isDead()) i.remove();
		}
		switch (getControlPoints().size()) {
			case 1:
				Iterator<ControlPoint> i = getControlPoints().iterator();
				ControlPoint cp = i.next();
				if (cp.isChanged()) {
					float diffX = cp.getX() - cp.getPreviousX();
					float diffY = cp.getY() - cp.getPreviousY();
					setX(getX() + diffX);
					setY(getY() + diffY);
					cp.setChanged(false);
				}
		}
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
		return contorlPoints.size() <= MAX_CONTROL_POINTS;
	}

	public boolean offer(ControlPoint cp, AffineTransformStack s) {
		if (!canAcceptMoreControlPoints()) return false;
		
		s.pushTransform();
		s.translate(getX(), getY());
		s.rotate(getRotation());
		
		try {
			Point2D xy = s.inverseTransform(cp.getX(), cp.getY());
			if (xy.getX() > -getWidth()/2 && xy.getX() < getWidth()/2 && xy.getY() > -getHeight()/2 && xy.getY() < getHeight()/2) {
				addControlPoint(cp);
				s.popTransform();
				return true;
			}
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		s.popTransform();
		return false;
	}

	protected void addControlPoint(ControlPoint cp) {
		contorlPoints.add(cp);
	}

	protected Collection<ControlPoint> getControlPoints() {
		return contorlPoints;
	}

}
