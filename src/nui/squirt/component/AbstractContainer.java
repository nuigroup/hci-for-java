package nui.squirt.component;

import java.util.ArrayList;
import java.util.Collection;

import nui.squirt.Component;
import nui.squirt.Container;

public abstract class AbstractContainer extends AbstractComponent implements Container {

	private Collection<Component> components = new ArrayList<Component>();
	
	public AbstractContainer(float x, float y) {
		super(x, y);
	}

	public void add(Component c) {
		components.add(c);
	}
	
	public void add(Component c, Object constraints) {
		components.add(c);
	}
	
	public void remove(Component c) {
		components.remove(c);
	}

	public Collection<Component> getComponents() {
		return components;
	}
}
