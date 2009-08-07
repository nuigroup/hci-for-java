package nui.squirt.event;

import nui.squirt.Valuable;

public class ValueEvent extends Event {
	
	private float oldValue;
	private float newValue;

	public ValueEvent(Valuable source) {
		super(source);
	}

	public float getOldValue() {
		return oldValue;
	}

	public void setOldValue(float oldValue) {
		this.oldValue = oldValue;
	}

	public float getNewValue() {
		return newValue;
	}

	public void setNewValue(float newValue) {
		this.newValue = newValue;
	}

}
