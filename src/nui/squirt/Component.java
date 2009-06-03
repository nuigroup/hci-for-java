package nui.squirt;

import nui.squirt.render.Renderer;

public interface Component {
	
	public void setRenderer(Renderer r);
	public Renderer getRenderer();
	
	public void setX(float x);
	public float getX();
	public void setY(float y);
	public float getY();
	
	public void setWidth(float width);
	public float getWidth();
	public void setHeight(float height);
	public float getHeight();
	
	public void setRotation(float theta);
	public float getRotation();
	
	public void setScale(float scale);
	public float getScale();
	
	public void render();

}
