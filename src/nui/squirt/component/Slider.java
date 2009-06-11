package nui.squirt.component;

import nui.squirt.render.Renderer;
import nui.squirt.render.SliderRenderer;

public class Slider extends AbstractComponent {

	private SliderRenderer renderer;
	
	private float length;

	private float minValue = 0;
	private float maxValue = 1;
	private float value = (float) 0.5;
	
	private float thumbWidth = 30;
	private float thumbHeight = 60;

	public Slider(float x, float y, float l) {
		super(x, y);
		this.length = l;
	}
	
	public Slider(float x, float y, float l, float minValue, float maxValue) {
		this(x, y, l);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.value = getCenterValue();
	}
	
	public Slider(float x, float y, float l, float minValue, float maxValue, float initValue) {
		this(x, y, l, minValue, maxValue);
		this.value = initValue;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer r) {
		setRenderer((SliderRenderer) r);
	}
	
	public void setRenderer(SliderRenderer r) {
		this.renderer = r;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getThumbWidth() {
		return thumbWidth;
	}

	public void setThumbWidth(float thumbWidth) {
		this.thumbWidth = thumbWidth;
	}

	public float getThumbHeight() {
		return thumbHeight;
	}

	public void setThumbHeight(float thumbHeight) {
		this.thumbHeight = thumbHeight;
	}

	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		float v = value;
		if (value > getMaxValue())
			v = getMaxValue();
		else if (value < getMinValue())
			v = getMinValue();
		this.value = v;
	}

	public float getValueRange() {
		return getMaxValue() - getMinValue();
	}
	
	public float getCenterValue() {
		return getValueRange()/2 + getMinValue();
	}

}
