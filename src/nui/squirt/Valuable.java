package nui.squirt;

import nui.squirt.event.ValueEvent;
import nui.squirt.listener.ValueListener;

/*
 * Interface representing Components that have a modifiable continuous value
 */
public interface Valuable extends Component {

	public void addValueListener(ValueListener l);
	
	public void fireValueChanged(ValueEvent e);
	
	public float getValue();
	public void setValue(float v);
}
