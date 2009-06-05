package nui.squirt.component;

import nui.squirt.Component;
import nui.squirt.Container;

public abstract class AbstractComponent implements Component {
	
	protected float x;
	protected float y;
	protected Container parent;
	protected float rotation = 0;
	protected float scale = 1;
	
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

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public void render() {
		getRenderer().prepare();
		getRenderer().draw();
		getRenderer().postDraw();
	}
	
}
