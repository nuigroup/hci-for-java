package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Image;

public abstract class ImageRenderer extends AbstractRenderer {

	public ImageRenderer(RenderingEngine e) {
		super(e);
	}

	public void prepare(Component c) {
		prepare((Image) c);
	}
	
	public abstract void prepare(Image i);

	public void draw(Component c) {
		draw((Image) c);
	}
	
	public abstract void draw(Image i);

	public void postDraw(Component c) {
		postDraw((Image) c);
	}
	
	public abstract void postDraw(Image i);

}
