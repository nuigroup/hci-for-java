package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Knob;

public abstract class KnobRenderer extends AbstractRenderer {

	public KnobRenderer(RenderingEngine e) {
		super(e);
	}

	public void prepare(Component c) {
		prepare((Knob) c);
	}
	
	public abstract void prepare(Knob k);

	public void draw(Component c) {
		draw((Knob) c);
	}
	
	public abstract void draw(Knob k);

	public void postDraw(Component c) {
		postDraw((Knob) c);
	}
	
	public abstract void postDraw(Knob k);

}
