package nui.squirt.component;

import nui.squirt.render.KnobRenderer;
import nui.squirt.render.Renderer;

public class Knob extends AbstractComponent {
	
	private KnobRenderer renderer;
	
	private float radius;
	private float minValue = 0;
	private float maxValue = 1;
	private float minAngle = 0;
	private float maxAngle = (float) (2 * Math.PI);

	public Knob(float x, float y, float r) {
		super(x, y);
		this.radius = r;
	}
	
	public Knob(float x, float y, float r, float minValue, float maxValue) {
		this(x, y, r);
		this.maxValue = maxValue;
		this.minValue = minValue;
	}
	
	public Knob(float x, float y, float r, float minValue, float maxValue, float initValue) {
		this(x, y, r, minValue, maxValue);
		setRotation(getAngleRange() * (initValue-getMinValue())/getValueRange());
	}
	
	public Knob(float x, float y, float r, float minValue, float maxValue, float minAngle, float maxAngle) {
		this(x, y, r, minValue, maxValue);
		this.minAngle = minAngle;
		this.maxAngle = maxAngle;
		setRotation(getMinAngle());
	}
	
	public Knob(float x, float y, float r, float minValue, float maxValue, float minAngle, float maxAngle, float initValue) {
		this(x, y, r, minValue, maxValue, minAngle, maxAngle);
		setRotation(getAngleRange() * (initValue-getMinValue())/getValueRange());
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer r) {
		setRenderer((KnobRenderer) r);
	}
	
	public void setRenderer(KnobRenderer r) {
		this.renderer = r;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
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
	
	public float getValueRange() {
		return getMaxValue() - getMinValue();
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

	public float getValue() {
		return (getRotation()-getMinAngle())/getAngleRange() * getValueRange() + getMinValue();
	}
	
	@Override
	public void setRotation(float rotation) {
		float r = rotation;
		
		if (rotation > getMaxAngle())
			r = getMaxAngle();
		else if (rotation < getMinAngle())
			r = getMinAngle();
		
		super.setRotation(r);
	}

}
