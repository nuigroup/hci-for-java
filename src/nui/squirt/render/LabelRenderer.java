package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Label;


public abstract class LabelRenderer extends AbstractRenderer {

	public LabelRenderer(RenderingEngine e) {
		super(e);
	}
	
	public void prepare(Component c) {
		prepare((Label) c);
	}
	
	public abstract void prepare(Label l);
	
	public void draw(Component c) {
		draw((Label) c);
	}
	
	public abstract void draw(Label l);
	
	public void postDraw(Component c) {
		postDraw((Label) c);
	}
	
	public abstract void postDraw(Label l);
	
}
