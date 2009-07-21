package nui.squirt.component;

import nui.squirt.Component;
import nui.squirt.listener.ControlPointListener;

public abstract class AbstractComponent implements Component, ControlPointListener {
	
	private float x;
	private float y;
	
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
	
}
