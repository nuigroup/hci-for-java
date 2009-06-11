package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Slider;

public abstract class SliderRenderer extends AbstractRenderer {

	public SliderRenderer(RenderingEngine e) {
		super(e);
	}

	public void prepare(Component c) {
		prepare((Slider) c);
	}
	
	public abstract void prepare(Slider s);

	public void draw(Component c) {
		draw((Slider) c);
	}
	
	public abstract void draw(Slider s);

	public void postDraw(Component c) {
		postDraw((Slider) c);
	}
	
	public abstract void postDraw(Slider s);

}
