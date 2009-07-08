package nui.squirt.layout;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;

import nui.squirt.Component;
import nui.squirt.Context;
import nui.squirt.LayoutManager;
import nui.squirt.context.spatial.RectangularRegionContext;

public class AbsoluteLayout implements LayoutManager {
	
	private Collection<Context> contexts = new ArrayList<Context>();

	public Context addComponent(Component c) {
		return addComponent(c, new Point2D.Float(0, 0));
	}

	public Context addComponent(Component c, Point2D location) {
		RectangularRegionContext r = new RectangularRegionContext(c);
		r.setWidth(c.getPreferredSize().width);
		r.setHeight(c.getPreferredSize().height);
		r.setX((float) location.getX());
		r.setY((float) location.getY());
		c.addContext(r);
		contexts.add(r);
		return r;
	}
	
	public Context addComponent(Component c, Object constraints) {
		return addComponent(c, (Point2D) constraints);
	}

	public Collection<Context> getManagedContexts() {
		return contexts;
	}

	public void layout(Context t) {
		for (Context c: contexts) {
			RectangularRegionContext r = (RectangularRegionContext) c;
			r.setWidth(c.getComponent().getPreferredSize().width);
			r.setHeight(c.getComponent().getPreferredSize().height);
		}
	}

}
