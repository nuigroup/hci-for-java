package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Button;
import nui.squirt.component.Frame;
import nui.squirt.component.Label;

public interface RenderingEngine {

	public Renderer getRenderer(Component c);
	
	public LabelRenderer getLabelRenderer(Label l);
	public FrameRenderer getFrameRenderer(Frame f);
	public ButtonRenderer getButtonRenderer(Button b);

}
