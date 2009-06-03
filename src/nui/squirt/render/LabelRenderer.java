package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Label;


public abstract class LabelRenderer extends AbstractRenderer {

	private Label label;

	public LabelRenderer(RenderingEngine e, Label l) {
		super(e);
		this.setComponent(l);
	}
	
	@Override
	public Component getComponent() {
		return label;
	}
	
	@Override
	public void setComponent(Component c) {
		this.setComponent((Label) c);
	}
	
	public void setComponent(Label l) {
		this.label = l;
	}
	
}
