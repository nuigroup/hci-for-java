package nui.squirt.render;

import nui.squirt.Component;
import nui.squirt.component.*;

public abstract class AbstractRenderingEngine implements RenderingEngine {

	public Renderer getRenderer(Component c) {
		if (c instanceof Label)
			return getLabelRenderer((Label) c);
		else if (c instanceof Frame)
			return getFrameRenderer((Frame) c);
		else if (c instanceof Button)
			return getButtonRenderer((Button) c);
		else if (c instanceof Knob)
			return getKnobRenderer((Knob) c);
		else if (c instanceof Slider)
			return getSliderRenderer((Slider) c);
		else if (c instanceof Image)
			return getImageRenderer((Image) c);
		else return null;
	}

}
