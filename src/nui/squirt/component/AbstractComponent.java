package nui.squirt.component;

import nui.squirt.Component;
import nui.squirt.Container;

public abstract class AbstractComponent implements Component {
	
	protected float x;
	protected float y;
	protected Container parent;
	protected float rotation = 0;
	protected boolean visible = true;
	
	public AbstractComponent(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Container getParent() {
		return parent;
	}

	public void setParent(Container parent) {
		this.parent = parent;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void render() {
		if (isVisible()) {
			getRenderer().prepare(this);
			getRenderer().draw(this);
			getRenderer().postDraw(this);
		}
	}
	
}
