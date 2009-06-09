package nui.squirt.render;

import nui.squirt.Component;


public interface Renderer {
	
	public RenderingEngine getRenderingEngine();
	
	public void prepare(Component c);
	public void draw(Component c);
	public void postDraw(Component c);

}
