package nui.squirt.context.spatial;

import nui.squirt.Component;
import nui.squirt.context.AbstractContext;

public abstract class SpatialContext extends AbstractContext {

	public SpatialContext(Component c) {
		super(c);
	}
	
	public SpatialContext(Component c, Component parent) {
		super(c, parent);
	}

}
