package nui.squirt.component;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import processing.core.PApplet;
import nui.squirt.Component;
import nui.squirt.ControlPoint;
import nui.squirt.util.AffineTransformStack;

public class Rectangle extends AbstractComponent implements Component {
	
	private float rotation;
	private float width;
	private float height;
	
	private Color fillColor = Color.GREEN;
	private Color strokeColor = Color.BLACK;
	private float strokeWeight = 1;
	
	private ControlPoint[] controlPoints = new ControlPoint[2];
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
		
		AffineTransform inverse = null;
		
		
		for (int i = 0; i < controlPointCount; i++) {
			if (controlPoints[i].isDead()) {
				controlPoints[i] = i+1 < controlPointCount ? controlPoints[i+1] : null;
				controlPointCount--;
				i--;
			}
		}
		if (controlPointCount > 0) {
			try {
				inverse = s.peek().createInverse();
			} catch (NoninvertibleTransformException e) {
				e.printStackTrace();
			}
		}
		switch(controlPointCount) {
			case 1:
				if (controlPoints[0].isChanged() && inverse != null) {
					double[] xy = { controlPoints[0].getX(), controlPoints[0].getY(), controlPoints[0].getPreviousX(), controlPoints[0].getPreviousY() };
					inverse.transform(xy, 0, xy, 0, 2);
					float diffX = (float) (xy[0] - xy[2]);
					float diffY = (float) (xy[1] - xy[3]);
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
		
		double[] origXY = { cp.getX(), cp.getY() };
		double[] newXY = new double[2];
		try {
			s.peek().inverseTransform(origXY, 0, newXY, 0, 1);
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		
		if (newXY[0] > getX()-getWidth()/2 && newXY[0] < getX()+getWidth()/2 && newXY[1] > getY()-getHeight()/2 && newXY[1] < getY()+getHeight()/2) {
			controlPoints[controlPointCount++] = cp;
			s.popTransform();
			return true;
		}
		else {
			s.popTransform();
			return false;
		}
	}

}
