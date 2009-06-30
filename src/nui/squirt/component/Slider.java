package nui.squirt.component;

import nui.squirt.LayoutManager;


public class Slider extends AbstractValuable {

//	private SliderRenderer renderer;
	
	private float length;
	
	private float thumbWidth = 30;
	private float thumbHeight = 60;

//	public Slider(float x, float y, float l) {
//		super(x, y);
//		this.length = l;
//	}
//	
//	public Slider(float x, float y, float l, float minValue, float maxValue) {
//		this(x, y, l);
//		this.minValue = minValue;
//		this.maxValue = maxValue;
//		this.value = getCenterValue();
//	}
//	
//	public Slider(float x, float y, float l, float minValue, float maxValue, float initValue) {
//		this(x, y, l, minValue, maxValue);
//		this.value = initValue;
//	}

//	public Renderer getRenderer() {
//		return renderer;
//	}
//
//	public void setRenderer(Renderer r) {
//		setRenderer((SliderRenderer) r);
//	}
//	
//	public void setRenderer(SliderRenderer r) {
//		this.renderer = r;
//	}

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

	public LayoutManager getLayout() {
		// TODO Auto-generated method stub
		return null;
	}

}
