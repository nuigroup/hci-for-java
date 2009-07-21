package nui.squirt.component;

import java.util.ArrayList;
import java.util.List;

import nui.squirt.Component;
import nui.squirt.Container;

public abstract class AbstractContainer extends AbstractComponent implements Container {

	private List<Component> components = new ArrayList<Component>();
	
	public AbstractContainer(float x, float y) {
		super(x, y);
	}

	public void add(Component c) {
		components.add(c);
		c.setParent(this);
	}
	
	public void add(Component c, Object constraints) {
		components.add(c);
		c.setParent(this);
	}
	
	public void remove(Component c) {
		components.remove(c);
		c.setParent(null);
	}

	public List<Component> getComponents() {
		return components;
	}
}
