package nui.squirt.component;

import java.util.ArrayList;
import java.util.List;

import nui.squirt.Component;
import nui.squirt.Container;
import nui.squirt.render.Renderer;
import nui.squirt.render.RenderingEngine;

public abstract class AbstractContainer extends AbstractComponent implements Container {

	protected List<Component> components = new ArrayList<Component>();

	public AbstractContainer(float x, float y, float w, float h) {
		super(x, y, w, h);
	}

	public void addComponent(Component c) {
		components.add(c);
		Renderer r = this.getRenderer();
		if (r != null) {
			RenderingEngine e = r.getRenderingEngine();
			Renderer r2 = e.getRenderer(c);
			c.setRenderer(r2);
		}
	}

	public List<Component> getComponents() {
		return components;
	}
	
	public void render() {
		this.getRenderer().prepare();
		
		this.getRenderer().draw();
		
		for (Component c: components) {
			c.render();
		}
		
		this.getRenderer().postDraw();
	}

}
