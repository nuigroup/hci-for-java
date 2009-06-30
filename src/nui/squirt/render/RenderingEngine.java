package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.Context;

public interface RenderingEngine {
	
	public void render(Component c, Context t);

//	public Renderer getRenderer(Component c, Context t);
//	
//	public LabelRenderer getLabelRenderer();
//	public FrameRenderer getFrameRenderer();
//	public ButtonRenderer getButtonRenderer();
//	public KnobRenderer getKnobRenderer();
//	public SliderRenderer getSliderRenderer();
//	public ImageRenderer getImageRenderer();

}
