package nui.squirt.component;

import java.util.ArrayList;
import java.util.List;

import nui.squirt.Component;
import nui.squirt.Container;
import nui.squirt.render.Renderer;
import nui.squirt.render.RenderingEngine;

public abstract class AbstractContainer extends AbstractComponent implements Container {

	protected List<Component> children = new ArrayList<Component>();

	public AbstractContainer(float x, float y) {
		super(x, y);
	}

	public void addChild(Component c) {
		children.add(c);
		Renderer r = this.getRenderer();
		if (r != null) {
			RenderingEngine e = r.getRenderingEngine();
			Renderer r2 = e.getRenderer(c);
			c.setRenderer(r2);
		}
	}
	
	public void removeChild(Component c) {
		children.remove(c);
	}

	public List<Component> getChildren() {
		return children;
	}
	
	public void render() {
		this.getRenderer().prepare(this);
		
		this.getRenderer().draw(this);
		
		for (Component c: children) {
			c.render();
		}
		
		this.getRenderer().postDraw(this);
	}

}
