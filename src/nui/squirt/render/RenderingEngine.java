package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.Button;
import nui.squirt.component.Frame;
import nui.squirt.component.Image;
import nui.squirt.component.Knob;
import nui.squirt.component.Label;
import nui.squirt.component.Slider;

public interface RenderingEngine {

	public Renderer getRenderer(Component c);
	
	public LabelRenderer getLabelRenderer(Label l);
	public FrameRenderer getFrameRenderer(Frame f);
	public ButtonRenderer getButtonRenderer(Button b);
	public KnobRenderer getKnobRenderer(Knob k);
	public SliderRenderer getSliderRenderer(Slider s);
	public ImageRenderer getImageRenderer(Image i);

}
