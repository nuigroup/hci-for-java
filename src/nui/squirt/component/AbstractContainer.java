package nui.squirt.component;

import java.util.ArrayList;
import java.util.Collection;

import nui.squirt.Component;
import nui.squirt.Container;
import nui.squirt.LayoutManager;

public abstract class AbstractContainer extends AbstractComponent implements Container {

	protected Collection<Component> children = new ArrayList<Component>();
	private LayoutManager layout;
	
	public AbstractContainer(LayoutManager l) {
		this.layout = l;
	}

	public void addChild(Component c) {
		children.add(c);
		getLayout().addComponent(c);
	}
	
	public void addChild(Component c, Object constraints) {
		children.add(c);
		getLayout().addComponent(c, constraints);
	}
	
	public void removeChild(Component c) {
		children.remove(c);
	}

	public Collection<Component> getChildren() {
		return children;
	}
	
	public LayoutManager getLayout() {
		return layout;
	}
}
