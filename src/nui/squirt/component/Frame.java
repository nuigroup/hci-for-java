package nui.squirt.component;

import nui.squirt.Component;
import nui.squirt.render.FrameRenderer;
import nui.squirt.render.Renderer;
import nui.squirt.render.RenderingEngine;

public class Frame extends AbstractContainer {

	private FrameRenderer renderer;

	public Frame(float x, float y, float w, float h) {
		super(x, y, w, h);
	}

	public FrameRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer r) {
		this.setRenderer((FrameRenderer) r);
	}

	public void setRenderer(FrameRenderer renderer) {
		this.renderer = renderer;
		RenderingEngine e = renderer.getRenderingEngine();
		
		for (Component c: components) {
			Renderer r = c.getRenderer();
			if (r == null || !r.getRenderingEngine().equals(e)) {
				c.setRenderer(e.getRenderer(c));
			}
		}
	}

}
