package nui.squirt;

import nui.squirt.render.Renderer;

public interface Component {
	
	public void setRenderer(Renderer r);
	public Renderer getRenderer();
	
	public void setX(float x);
	public float getX();
	public void setY(float y);
	public float getY();
	
	public void setParent(Container parent);
	public Container getParent();
	
	public void setRotation(float theta);
	public float getRotation();
	
	public void render();

}
