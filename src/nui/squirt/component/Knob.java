package nui.squirt.component;

import nui.squirt.LayoutManager;


public class Knob extends AbstractValuable {
	
//	private KnobRenderer renderer;
	
	private float radius;
	private float minAngle = 0;
	private float maxAngle = (float) (2 * Math.PI);
	

//	public Knob(float x, float y, float r) {
//		super(x, y);
//		this.radius = r;
//	}
//	
//	public Knob(float x, float y, float r, float minValue, float maxValue) {
//		this(x, y, r);
//		this.maxValue = maxValue;
//		this.minValue = minValue;
//		setValue(getMinValue());
//	}
//	
//	public Knob(float x, float y, float r, float minValue, float maxValue, float initValue) {
//		this(x, y, r, minValue, maxValue);
//		setValue(initValue);
//	}
//	
//	public Knob(float x, float y, float r, float minValue, float maxValue, float minAngle, float maxAngle) {
//		this(x, y, r, minValue, maxValue);
//		this.minAngle = minAngle;
//		this.maxAngle = maxAngle;
//	}
//	
//	public Knob(float x, float y, float r, float minValue, float maxValue, float minAngle, float maxAngle, float initValue) {
//		this(x, y, r, minValue, maxValue, minAngle, maxAngle);
//		setValue(initValue);
//	}

//	public Renderer getRenderer() {
//		return renderer;
//	}
//
//	public void setRenderer(Renderer r) {
//		setRenderer((KnobRenderer) r);
//	}
//	
//	public void setRenderer(KnobRenderer r) {
//		this.renderer = r;
//	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

public float getMinAngle() {
		return minAngle;
	}

	public void setMinAngle(float minAngle) {
		this.minAngle = minAngle;
	}

	public float getMaxAngle() {
		return maxAngle;
	}

	public void setMaxAngle(float maxAngle) {
		this.maxAngle = maxAngle;
	}

	public float getAngleRange() {
		return getMaxAngle() - getMinAngle();
	}
	
	public float getRotation() {
		return getAngleRange() * (getValue()-getMinValue())/getValueRange() + getMinAngle();
	}

	public LayoutManager getLayout() {
		// TODO Auto-generated method stub
		return null;
	}

//	public void setRotation(float rotation) {
//		float r = rotation;
//		
//		if (rotation > getMaxAngle())
//			r = getMaxAngle();
//		else if (rotation < getMinAngle())
//			r = getMinAngle();
//		
//		setValue((r-getMinAngle())/getAngleRange() * getValueRange() + getMinValue());
//	}

}
