package nui.squirt.component;

import nui.squirt.Context;
import nui.squirt.LayoutManager;


public class Label extends AbstractComponent {

	protected String text;

	public Label(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String t) {
		if (!this.text.equals(t)) {
			this.text = t;
			for (Context c: getContexts()) {
				c.invalidate();
			}
		}
	}

	public LayoutManager getLayout() {
		return null;
	}

}
