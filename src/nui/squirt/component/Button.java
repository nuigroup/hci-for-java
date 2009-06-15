package nui.squirt.component;

import nui.squirt.render.ButtonRenderer;
import nui.squirt.render.Renderer;
import nui.squirt.render.RenderingEngine;


public class Button extends AbstractComponent {
	
	private ButtonRenderer renderer;
	
	private Label label;
	
	public Button(float x, float y, String l) {
		super(x, y);
		this.label = new Label(0, 0, l);
	}

	public Renderer getRenderer() {
		return renderer;
	}
	
	public void setRenderer(Renderer renderer) {
		this.setRenderer((ButtonRenderer) renderer);
	}
	
	public void setRenderer(ButtonRenderer r) {
		this.renderer = r;
		RenderingEngine e = renderer.getRenderingEngine();
		Renderer labelRenderer = label.getRenderer();
		if (labelRenderer == null || !labelRenderer.getRenderingEngine().equals(e)) {
			label.setRenderer(e.getRenderer(label));
		}
	}
	
	public Label getLabel() {
		return label;
	}
	
	public void setText(String text) {
		getLabel().setText(text);
	}

	@Override
	public void render() {
		if (isVisible()) {
			getRenderer().prepare(this);
			getRenderer().draw(this);
			getLabel().render();
			getRenderer().postDraw(this);
		}
	}
	
}
