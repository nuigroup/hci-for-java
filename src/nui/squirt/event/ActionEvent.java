package nui.squirt.event;

import nui.squirt.Actionable;

public class ActionEvent extends Event {

	public ActionEvent(Actionable source) {
		super(source);
	}
	
	public String getAction() {
		return ((Actionable) getSource()).getAction();
	}

}
