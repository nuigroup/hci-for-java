package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Frame;


public abstract class FrameRenderer extends AbstractRenderer {
	
	private Frame frame;

	public FrameRenderer(RenderingEngine e, Frame f) {
		super(e);
		this.setComponent(f);
	}
	
	@Override
	public Component getComponent() {
		return frame;
	}
	
	@Override
	public void setComponent(Component c) {
		this.setComponent((Frame) c);
	}
	
	public void setComponent(Frame f) {
		this.frame = f;
	}

}
