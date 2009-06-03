package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.*;

public abstract class AbstractRenderingEngine implements RenderingEngine {

	public Renderer getRenderer(Component c) {
		if (c instanceof Label)
			return getLabelRenderer((Label) c);
		else return null;
	}

}
