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
		c.setParent(null);
	}
	
	public void removeComponent(Component c) {
		components.remove(c);
		c.setParent(null);
	}

	public void render() {
		for (Component c: components)
			c.render();
	}
	
	/*
	 *  This is a convenience method to establish a parent child relationship.
	 *  This both adds a child to the Container, and sets the parent attribute of the child.
	 *  Do not establish this relationship by merely calling addChild() on the Container,
	 *  the child's parent pointer will remain as-is potentially generating undesired behavior.
	 */
	public static void setParentChildPair(Container parent, Component child) {
		parent.addChild(child);
		child.setParent(parent);
	}
	
	/*
	 *  This is a convenience method to un-establish a parent child relationship.
	 *  This both removes a child from the Container, and sets the parent attribute of the child.
	 *  Do not un-establish this relationship by merely calling removeChild() on the Container,
	 *  the child's parent pointer will remain as-is potentially generating undesired behavior.
	 */
	public static void endParentChildPair(Container parent, Component child) {
		parent.removeChild(child);
		child.setParent(null);
	}

}
