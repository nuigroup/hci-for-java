package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Button;


public abstract class ButtonRenderer extends AbstractRenderer {
	
	private Button button;

	public ButtonRenderer(RenderingEngine e, Button b) {
		super(e);
		this.setComponent(b);
	}
	
	@Override
	public Component getComponent() {
		return button;
	}
	
	@Override
	public void setComponent(Component c) {
		this.setComponent((Button) c);
	}
	
	public void setComponent(Button b) {
		this.button = b;
	}

}
