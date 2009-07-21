package nui.squirt.component;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import nui.squirt.Component;
import nui.squirt.Container;
import nui.squirt.listener.ControlPointListener;
import processing.core.PApplet;
import processing.core.PMatrix;
import processing.core.PMatrix2D;
import processing.core.PVector;

public abstract class AbstractComponent implements Component, ControlPointListener {
	
	private AffineTransform transformMatrix;
	
	private Container parent;
	
	public AbstractComponent(float x, float y) {
		this.transformMatrix = AffineTransform.getTranslateInstance(x, y);
	}
	
	public void preRender(PApplet p) {
		update();
		
		p.pushMatrix();
		double[] matrix = new double[6];
		getTransformMatrix().getMatrix(matrix);
		PMatrix m = new PMatrix2D((float)matrix[0], (float)matrix[2], (float)matrix[4], (float)matrix[1], (float)matrix[3], (float)matrix[5]);
		p.applyMatrix(m);
	}
	
	public void postRender(PApplet p) {
		p.popMatrix();
	}
	
	public AffineTransform getTransformMatrix() {
		return transformMatrix;
	}
	
	public AffineTransform getAbsoluteTransformMatrix() {
		AffineTransform t = new AffineTransform();
		if (parent != null) {
			t = parent.getAbsoluteTransformMatrix();
		}
		t.concatenate(getTransformMatrix());
		return t;
	}
	
	public PVector transformToLocalSpace(PVector v) {
		PVector result = new PVector();
		double[] points = { v.x, v.y };
		try {
			getAbsoluteTransformMatrix().inverseTransform(points, 0, points, 0, 1);
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.x = (float) points[0];
		result.y = (float) points[1];
		return result;
	}
	
//	public float getX() {
//		return (float) getTransformMatrix().getTranslateX();
//	}
//	
//	public void setX(float x) {
//		float r = getRotation();
//		setRotation(0);
//		getTransformMatrix().translate(x-getX(), 0);
//		setRotation(r);
//	}
//	
//	public float getY() {
//		return (float) getTransformMatrix().getTranslateY();
//	}
//	
//	public void setY(float y) {
//		float r = getRotation();
//		setRotation(0);
//		getTransformMatrix().translate(0, y-getY());
//		setRotation(r);
//	}
//	
//	public float getRotation() {
//		float[] pts = { getX(), getY(), getX()+1, getY() };
//
//		getTransformMatrix().transform(pts, 0, pts, 0, 2);
//
//		double dy = Math.abs(pts[3] - pts[1]);
//		double l = PVector.dist(new PVector(pts[0], pts[1]), new PVector(pts[2], pts[3]));
//		double rotation = Math.asin(dy / l);		
//		
//		// correct for quadrant
//		if (pts[3] - pts[1] > 0) {
//			if (pts[2] - pts[0] < 0) {
//				rotation = Math.PI - rotation;
//			}
//		} else {
//			if (pts[2] - pts[0] > 0) {
//				rotation = 2 * Math.PI - rotation;
//			} else {
//				rotation = rotation + Math.PI;
//			}
//		}
//
//		return (float) rotation;
//	}
//	
//	public void setRotation(float theta) {
//		getTransformMatrix().rotate(theta-getRotation(), getX(), getY());
//	}
//	
//	public void rotate(float theta) {
//		getTransformMatrix().rotate(theta, getX(), getY());
//	}
	
	public Container getParent() {
		return parent;
	}
	
	public void setParent(Container p) {
		this.parent = p;
	}
	
}
