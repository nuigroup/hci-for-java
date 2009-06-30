package nui.squirt;

import java.util.HashMap;
import java.util.Map;

import nui.squirt.context.spatial.RectangularRegionContext;
import nui.squirt.render.RenderingEngine;

public class NUIController {
	
	private RenderingEngine renderingEngine;
	private Map<Component, Context> components = new HashMap<Component, Context>();

	public NUIController(RenderingEngine r) {
		this.renderingEngine = r;
	}
	
	public void addComponent(Component c) {
		RectangularRegionContext r = new RectangularRegionContext(c);
		r.setX((float) (Math.random()*300 - 150));
		r.setY((float) (Math.random()*300 - 150));
		r.setWidth(c.getPreferredSize().width);
		r.setHeight(c.getPreferredSize().height);
		r.setRotation((float) (Math.random()*Math.PI*2));
		r.setScale(1);
		
		c.addContext(r);
		components.put(c, r);
	}
	
	public void removeComponent(Component c) {
		components.remove(c);
	}

	public void render() {
		for (Map.Entry<Component, Context> e: components.entrySet()) {
			renderingEngine.render(e.getKey(), e.getValue());
		}
	}

}
