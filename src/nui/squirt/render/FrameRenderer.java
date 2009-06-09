package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Frame;



public abstract class FrameRenderer extends AbstractRenderer {

	public FrameRenderer(RenderingEngine e) {
		super(e);
	}
	
	public void prepare(Component c) {
		prepare((Frame) c);
	}
	
	public abstract void prepare(Frame f);
	
	public void draw(Component c) {
		draw((Frame) c);
	}
	
	public abstract void draw(Frame f);
	
	public void postDraw(Component c) {
		postDraw((Frame) c);
	}
	
	public abstract void postDraw(Frame f);

}
