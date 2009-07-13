package nui.squirt;

import nui.squirt.util.AffineTransformStack;
import processing.core.PApplet;

public interface Component {
	
	public void update(AffineTransformStack s);
	
	public void preRender(PApplet p, AffineTransformStack s);
	public void render(PApplet p, AffineTransformStack s);
	public void postRender(PApplet p, AffineTransformStack s);
	
	public void setX(float x);
	public float getX();
	public void setY(float y);
	public float getY();
	
	// Indicates whether this Component can accept offers of additional control points
	public boolean canAcceptMoreControlPoints();
	
	// Returns true if no other Components should process this point
	public boolean offer(ControlPoint cp, AffineTransformStack s);
}
