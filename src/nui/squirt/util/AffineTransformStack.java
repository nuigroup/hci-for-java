package nui.squirt.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.Stack;

public class AffineTransformStack {
	
	private static AffineTransformStack s;
	
	private Stack<AffineTransform> stack = new Stack<AffineTransform>();
	
	public AffineTransformStack() {
		stack.push(new AffineTransform());
	}
	
	public static AffineTransformStack getInstance() {
		if (s == null) {
			s = new AffineTransformStack();
		}
		return s;
	}
	
	public void translate(double x, double y) {
		stack.peek().translate(x, y);
	}
	
	public void rotate(double theta) {
		stack.peek().rotate(theta);
	}
	
	public void scale(double s) {
		stack.peek().scale(s, s);
	}
	
	public void scale(double sx, double sy) {
		stack.peek().scale(sx, sy);
	}
	
	public void shear(double shx, double shy) {
		stack.peek().shear(shx, shy);
	}
	
	public void pushTransform() {
		stack.push(new AffineTransform(stack.peek()));
	}
	
	public AffineTransform popTransform() {
		return stack.pop();
	}
	
	public AffineTransform peek() {
		return stack.peek();
	}
	
	// TODO add transform and inverse transform methods directly to this stack
	public Point2D transform(float origX, float origY) {
		Point2D orig = new Point2D.Float(origX, origY);
		return stack.peek().transform(orig, null);
	}
	
	public Point2D inverseTransform(float origX, float origY) throws NoninvertibleTransformException {
		Point2D orig = new Point2D.Float(origX, origY);
		return stack.peek().inverseTransform(orig, null);
	}

}
