package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Label;

public interface RenderingEngine {

	public Renderer getRenderer(Component c);
	
	public LabelRenderer getLabelRenderer(Label l);

}
