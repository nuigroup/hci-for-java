package nui.squirt.component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import nui.squirt.Component;
import nui.squirt.Container;
import nui.squirt.ControlPoint;
import processing.core.PApplet;


public class Frame extends Rectangle implements Container {
	
	private List<Component> components = new ArrayList<Component>();
	
	public Frame(float x, float y, float w, float h) {
		super(x,y,w,h);
		setFillColor(new Color(75, 75, 75, 150));
		setStrokeColor(new Color(0, 0, 0, 0));
	}

	public Frame(float x, float y) {
		this(x, y, 100, 100);
	}

	public void render(PApplet p) {
		super.render(p);
		
		List<Component> l = new ArrayList<Component>(getComponents());
		for (Component c: l) {
			c.preRender(p);
			c.render(p);
			c.postRender(p);
		}
	}

	public void add(Component c) {
		getComponents().add(c);
		c.setParent(this);
	}

	public void add(Component c, Object constraints) {
		this.add(c);
	}

	public List<Component> getComponents() {
		return components;
	}

	public void remove(Component c) {
		getComponents().remove(c);
		c.setParent(null);
	}
	
	@Override
	public boolean canAcceptMoreControlPoints() {
		if (super.canAcceptMoreControlPoints()) {
			return true;
		}
		else {
			for (Component c: getComponents()) {
				if (c.canAcceptMoreControlPoints())
					return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean isUnderPoint(ControlPoint cp) {
		if (super.isUnderPoint(cp)) {
			return true;
		}
		else {
			for (Component c: getComponents()) {
				if (c.isUnderPoint(cp))
					return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean offer(ControlPoint cp) {
		ListIterator<Component> i = getComponents().listIterator(getComponents().size());
		while (i.hasPrevious()) {
			Component c = i.previous();
			if (c.isUnderPoint(cp) && c.offer(cp)) {
				i.remove();
				getComponents().add(c);
				return true;
			}
		}
		if (super.canAcceptMoreControlPoints() && super.isUnderPoint(cp)) {
			return super.offer(cp);
		}
		else return false;
	}

}
