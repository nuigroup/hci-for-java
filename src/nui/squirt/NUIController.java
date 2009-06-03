package nui.squirt;

import java.util.ArrayList;
import java.util.List;

import nui.squirt.render.Renderer;
import nui.squirt.render.RenderingEngine;

public class NUIController {
	
	private RenderingEngine renderingEngine;
	private List<Component> components = new ArrayList<Component>();

	public NUIController(RenderingEngine r) {
		this.renderingEngine = r;
	}
	
	public void addComponent(Component c) {
		components.add(c);
		Renderer r = renderingEngine.getRenderer(c);
		c.setRenderer(r);
	}

	public void render() {
		for (Component c: components)
			c.render();
	}

}
