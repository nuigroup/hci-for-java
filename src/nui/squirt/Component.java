package nui.squirt;

import java.awt.geom.AffineTransform;


import processing.core.PApplet;
import processing.core.PVector;

public interface Component extends ControlPointListener {
	
	public void update();
	
	public void preRender(PApplet p);
	public void render(PApplet p);
	public void postRender(PApplet p);
	
	public AffineTransform getTransformMatrix();
	public AffineTransform getAbsoluteTransformMatrix();
	
	public PVector transformToLocalSpace(PVector v);
	
	public void setParent(Container p);
	public Container getParent();
	
}
