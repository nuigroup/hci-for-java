package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.Context;
import nui.squirt.component.Button;

public abstract class ButtonRenderer extends AbstractRenderer {

	public ButtonRenderer(RenderingEngine e) {
		super(e);
	}
	
//	public void prepare(Component c) {
//		prepare((Button) c);
//	}
//	
//	public abstract void prepare(Button b);
//	
//	public void draw(Component c) {
//		draw((Button) c);
//	}
//	
//	public abstract void draw(Button b);
//	
//	public void postDraw(Component c) {
//		postDraw((Button) c);
//	}
//	
//	public abstract void postDraw(Button b);
	
	public void render(Component c, Context t) {
		render((Button) c, t);
	}
	
	public abstract void render(Button b, Context t);

}
