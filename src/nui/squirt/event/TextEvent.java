package nui.squirt.event;

import nui.squirt.TextInput;

public class TextEvent extends Event {
	
	private String oldText;
	private String newText;

	public TextEvent(TextInput source) {
		super(source);
	}

	public String getOldText() {
		return oldText;
	}

	public void setOldText(String oldText) {
		this.oldText = oldText;
	}

	public String getNewText() {
		return newText;
	}

	public void setNewText(String newText) {
		this.newText = newText;
	}

}
