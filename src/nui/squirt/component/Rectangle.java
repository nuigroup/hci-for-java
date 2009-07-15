package nui.squirt.component;

import java.awt.Color;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

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
	
	private ControlPoint[] controlPoints = new ControlPoint[1];
	private int controlPointCount = 0;

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
		
		for (int i = 0; i < controlPointCount; i++) {
			if (controlPoints[i].isDead()) {
				controlPoints[i] = i+1 < controlPointCount ? controlPoints[i+1] : null;
				controlPointCount--;
				i--;
			}
		}
		switch(controlPointCount) {
			case 1:
				if (controlPoints[0].isChanged()) {
					float diffX = (float) (controlPoints[0].getX() - controlPoints[0].getPreviousX());
					float diffY = (float) (controlPoints[0].getY() - controlPoints[0].getPreviousY());
					setX(getX() + diffX);
					setY(getY() + diffY);
					controlPoints[0].setChanged(false);
				}
				break;
			case 2:
				break;
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
		return controlPointCount < controlPoints.length;
	}

	public boolean offer(ControlPoint cp, AffineTransformStack s) {
		if (!canAcceptMoreControlPoints()) return false;
		
		s.pushTransform();
		s.translate(getX(), getY());
		s.rotate(getRotation());
		
		try {
			Point2D xy = s.inverseTransform(cp.getX(), cp.getY());
			if (xy.getX() > -getWidth()/2 && xy.getX() < getWidth()/2 && xy.getY() > -getHeight()/2 && xy.getY() < getHeight()/2) {
				controlPoints[controlPointCount++] = cp;
				s.popTransform();
				return true;
			}
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		s.popTransform();
		return false;
	}

}
