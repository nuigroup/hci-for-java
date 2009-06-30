package nui.squirt.component;

import java.util.ArrayList;
import java.util.List;

import nui.squirt.Actionable;
import nui.squirt.event.ActionEvent;
import nui.squirt.listener.ActionListener;

public abstract class AbstractActionable extends AbstractComponent implements Actionable {

	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	private String action;

	public void addActionListener(ActionListener a) {
		this.actionListeners.add(a);
	}

	public void fireAction(ActionEvent e) {
		for (ActionListener l: actionListeners) {
			l.actionPerformed(e);
		}
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
