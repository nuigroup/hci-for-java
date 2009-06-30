package nui.squirt.context;

import nui.squirt.Component;
import nui.squirt.Context;

public abstract class AbstractContext implements Context {
	
	private Component component;
	private Component parent;
	
	protected boolean valid;

	public AbstractContext(Component c) {
		this.component = c;
	}
	
	public AbstractContext(Component c, Component parent) {
		this(c);
		this.parent = parent;
	}

	public Component getComponent() {
		return component;
	}

	public Component getParentComponent() {
		return parent;
	}

	public void setComponent(Component c) {
		this.component = c;
	}

	public void setParentComponent(Component parent) {
		this.parent = parent;
	}
	
	public boolean isValid() {
		return valid;
	}

}
