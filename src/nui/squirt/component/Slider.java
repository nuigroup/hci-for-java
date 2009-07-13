package nui.squirt.component;

import java.util.ArrayList;
import java.util.Collection;

import processing.core.PApplet;

import nui.squirt.Valuable;
import nui.squirt.event.ValueEvent;
import nui.squirt.listener.ValueListener;
import nui.squirt.util.AffineTransformStack;


public class Slider extends Rectangle implements Valuable {
	
	private Collection<ValueListener> listeners = new ArrayList<ValueListener>();
	
	private float length;
	
	private float minValue;
	private float maxValue;
	private float value;

	public Slider(float x, float y, float l) {
		super(x, y, 30, 50);
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

	public void addValueListener(ValueListener l) {
		listeners.add(l);
	}

	public void fireValueChanged(ValueEvent e) {
		for (ValueListener l: listeners) {
			l.valueChanged(e);
		}
	}
	
	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
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
		ValueEvent e = new ValueEvent(this);
		e.setOldValue(getValue());
		
		float v = value;
		if (value > getMaxValue())
			v = getMaxValue();
		else if (value < getMinValue())
			v = getMinValue();
		this.value = v;
		
		e.setNewValue(getValue());
		fireValueChanged(e);
	}

	public float getCenterValue() {
		return getValueRange()/2 + getMinValue();
	}

	public float getValueRange() {
		return getMaxValue() - getMinValue();
	}
	
	@Override
	public void update(AffineTransformStack s) {
		// TODO Auto-generated method stub
		super.update(s);
	}
	
	@Override
	public void preRender(PApplet p, AffineTransformStack s) {
		// TODO Auto-generated method stub
		super.preRender(p, s);
	}
	
	@Override
	public void render(PApplet p, AffineTransformStack s) {
		// TODO Auto-generated method stub
		super.render(p, s);
	}
	
	// Old code for rendering
//	public void draw(Slider s) {
//	ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
//	PApplet pApplet = engine.getPApplet();
//	
//	pApplet.stroke(25);
//	pApplet.fill(75);
//	pApplet.rectMode(PApplet.CENTER);
//	
//	pApplet.rect(0, 0, 5, s.getLength());
//	
//	float h = -(s.getValue()-s.getCenterValue()) / s.getValueRange() * s.getLength();
//	
//	pApplet.stroke(25);
//	pApplet.rect(0, h, s.getThumbWidth(), s.getThumbHeight());
//	pApplet.stroke(100);
//	pApplet.line(-s.getThumbWidth()/2, h, s.getThumbWidth()/2, h);
//}

}
