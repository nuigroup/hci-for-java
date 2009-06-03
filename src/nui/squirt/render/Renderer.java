package nui.squirt.render;

import nui.squirt.Component;

public interface Renderer {
	
	public void setComponent(Component c);
	public Component getComponent();
	
	public RenderingEngine getRenderingEngine();
	
	public void prepare();
	public void draw();
	public void postDraw();

}
