package nui.squirt.component;

import java.util.ArrayList;
import java.util.List;

import nui.squirt.Valuable;
import nui.squirt.event.ValueEvent;
import nui.squirt.listener.ValueListener;

public abstract class AbstractValuable extends AbstractComponent implements Valuable {
	
	private List<ValueListener> valueListeners = new ArrayList<ValueListener>();
	
	private float minValue = 0;
	private float maxValue = 1;
	private float value = (float) 0.5;

	public AbstractValuable(float x, float y) {
		super(x, y);
	}

	public void addValueListener(ValueListener l) {
		this.valueListeners.add(l);
	}

	public void fireValueChanged(ValueEvent e) {
		for (ValueListener l: valueListeners) {
			l.valueChanged(e);
		}
	}
	
	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		ValueEvent e = new ValueEvent(this);
		e.setOldValue(getValue());
		
		float v = value;
		if (value > getMaxValue())
			v = getMaxValue();
		else if (value < getMinValue())
			v = getMinValue();
		this.value = v;
		
		e.setNewValue(getValue());
		fireValueChanged(e);
	}

	public float getValueRange() {
		return getMaxValue() - getMinValue();
	}
	
	public float getCenterValue() {
		return getValueRange()/2 + getMinValue();
	}

}
