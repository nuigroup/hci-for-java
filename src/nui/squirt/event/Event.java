package nui.squirt.event;

import nui.squirt.Component;

public class Event {
	
	private Component source;

	public Event(Component source) {
		this.source = source;
	}

	public Component getSource() {
		return source;
	}
	
}
