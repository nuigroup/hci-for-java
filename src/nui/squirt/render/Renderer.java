package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.Context;


public interface Renderer {
	
	public RenderingEngine getRenderingEngine();
	
	public void render(Component c, Context t);
	
//	public void prepare(Component c);
//	public void draw(Component c);
//	public void postDraw(Component c);

}
