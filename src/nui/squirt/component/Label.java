package nui.squirt.component;

import nui.squirt.render.LabelRenderer;
import nui.squirt.render.Renderer;

public class Label extends AbstractComponent {

	private LabelRenderer renderer;
	
	protected String text;
	
	public Label(float x, float y, String l) {
		super(x, y);
		this.text = l;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer r) {
		this.setRenderer((LabelRenderer) r);
	}

	public void setRenderer(LabelRenderer r) {
		this.renderer = r;
	}

	public String getText() {
		return text;
	}

	public void setText(String t) {
		this.text = t;
	}

}
