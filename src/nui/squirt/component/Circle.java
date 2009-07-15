package nui.squirt.component;

import java.awt.Color;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import nui.squirt.ControlPoint;
import nui.squirt.util.AffineTransformStack;

import processing.core.PApplet;

public class Circle extends AbstractComponent {
	
	private float radius;
	
	private Color fillColor = Color.BLUE;
	private Color strokeColor = Color.BLACK;
	private float strokeWeight;
	
	private ControlPoint[] controlPoints = new ControlPoint[1];
	private int controlPointCount = 0;

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

	public void update(AffineTransformStack s) {
		s.pushTransform();
		s.translate(getX(), getY());
		
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
		
		p.ellipseMode(PApplet.CENTER);
		p.fill(getFillColor().getRGB());
		p.stroke(getStrokeColor().getRGB());
		p.strokeWeight(getStrokeWeight());
		
		p.pushMatrix();
		
		p.translate(getX(), getY());
	}

	public void postRender(PApplet p, AffineTransformStack s) {
		p.popMatrix();
		s.popTransform();
	}

	public void render(PApplet p, AffineTransformStack s) {
		p.ellipse(0, 0, getRadius()*2, getRadius()*2);
	}

	public boolean canAcceptMoreControlPoints() {
		return controlPointCount < controlPoints.length;
	}

	public boolean offer(ControlPoint cp, AffineTransformStack s) {
		if (!canAcceptMoreControlPoints()) return false;
		
		s.pushTransform();
		s.translate(getX(), getY());
		
		try {
			Point2D xy = s.inverseTransform(cp.getX(), cp.getY());
			if (xy.distance(0, 0) <= getRadius()) {
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
