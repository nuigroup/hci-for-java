package nui.squirt.render;


public abstract class AbstractRenderer implements Renderer {
	
	protected RenderingEngine engine;
	
	public AbstractRenderer(RenderingEngine e) {
		this.engine = e;
	}

	public RenderingEngine getRenderingEngine() {
		return engine;
	}

}
