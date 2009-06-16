package nui.squirt.component;

import java.util.ArrayList;
import java.util.List;

import nui.squirt.Valuable;
import nui.squirt.event.ValueEvent;
import nui.squirt.listener.ValueListener;

public abstract class AbstractValuable extends AbstractComponent implements Valuable {
	
	private List<ValueListener> valueListeners = new ArrayList<ValueListener>();

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

}
