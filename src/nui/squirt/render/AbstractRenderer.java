package nui.squirt.render;

import nui.squirt.Component;

public abstract class AbstractRenderer implements Renderer {
	
	protected RenderingEngine engine;
	private Component component;
	
	public AbstractRenderer(RenderingEngine e) {
		this.engine = e;
	}

	public RenderingEngine getRenderingEngine() {
		return engine;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component c) {
		this.component = c;
	}

}
