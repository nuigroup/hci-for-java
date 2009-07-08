package nui.squirt;

import nui.squirt.component.AbstractContainer;
import nui.squirt.layout.AbsoluteLayout;
import nui.squirt.render.RenderingEngine;

public class NUIController extends AbstractContainer {
	
	private RenderingEngine renderingEngine;

	public NUIController(RenderingEngine r) {
		this(r, new AbsoluteLayout());
	}

	public NUIController(RenderingEngine r, LayoutManager l) {
		super(l);
		this.renderingEngine = r;
	}
	
//	public void addComponent(Component c) {
//		RectangularRegionContext r = new RectangularRegionContext(c);
//		r.setX((float) (Math.random()*300 - 150));
//		r.setY((float) (Math.random()*300 - 150));
//		r.setWidth(c.getPreferredSize().width);
//		r.setHeight(c.getPreferredSize().height);
//		r.setRotation((float) (Math.random()*Math.PI*2));
//		r.setScale(1);
//		
//		c.addContext(r);
//		components.put(c, r);
//	}

	public void render() {
		for (Context c: getLayout().getManagedContexts()) {
			renderingEngine.render(c.getComponent(), c);
		}
	}

}
