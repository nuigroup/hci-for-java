package nui.squirt.component;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import nui.squirt.Component;
import nui.squirt.Context;

public abstract class AbstractComponent implements Component {
	
	private Collection<Context> contexts = new ArrayList<Context>();
	
	private Dimension maximumSize = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	private Dimension minimumSize = new Dimension(Integer.MIN_VALUE, Integer.MIN_VALUE);
	private Dimension preferredSize = new Dimension();
	
	public Dimension getMaximumSize() {
		return maximumSize;
	}

	public void setMaximumSize(Dimension maximumSize) {
		if (!this.maximumSize.equals(maximumSize)) {
			this.maximumSize = maximumSize;
			for (Context c: getContexts()) {
				c.invalidate();
			}
		}
	}

	public Dimension getMinimumSize() {
		return minimumSize;
	}

	public void setMinimumSize(Dimension minimumSize) {
		if (!this.minimumSize.equals(minimumSize)) {
			this.minimumSize = minimumSize;
			for (Context c: getContexts()) {
				c.invalidate();
			}
		}
	}

	public Dimension getPreferredSize() {
		return preferredSize;
	}

	public void setPreferredSize(Dimension preferredSize) {
		if (!this.preferredSize.equals(preferredSize)) {
			this.preferredSize = preferredSize;
			for (Context c: getContexts()) {
				c.invalidate();
			}
		}
	}
	
	public boolean hasLayout() {
		return getLayout() != null;
	}
	
	public void addContext(Context c) {
		if (c.getComponent() == this) {
			contexts.add(c);
		}
	}
	
	public Collection<Context> getContexts() {
		return contexts;
	}

//	public boolean isVisible() {
//		return visible;
//	}
//
//	public void setVisible(boolean visible) {
//		this.visible = visible;
//	}

//	public void render() {
//		if (isVisible()) {
//			getRenderer().prepare(this);
//			getRenderer().draw(this);
//			getRenderer().postDraw(this);
//		}
//	}
	
}
