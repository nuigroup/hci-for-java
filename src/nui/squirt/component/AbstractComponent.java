package nui.squirt.component;

import java.util.ArrayList;
import java.util.Collection;

import nui.squirt.Component;
import nui.squirt.ControlPoint;
import nui.squirt.listener.ControlPointListener;

public abstract class AbstractComponent implements Component, ControlPointListener {
	
	private float x;
	private float y;
	
	private Collection<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
	private static final int MAX_CONTROL_POINTS = 1;
	
	public AbstractComponent(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}

	public boolean canAcceptMoreControlPoints() {
		return controlPoints.size() <= MAX_CONTROL_POINTS;
	}
	
	public void controlPointCreated(ControlPoint cp) {
		controlPoints.add(cp);
	}

	public void controlPointDied(ControlPoint cp) {
		controlPoints.remove(cp);
	}

	public void controlPointUpdated(ControlPoint cp) {
		switch (controlPoints.size()) {
			case 1:
				float diffX = cp.getX() - cp.getPreviousX();
				float diffY = cp.getY() - cp.getPreviousY();
				setX(getX() + diffX);
				setY(getY() + diffY);
				break;
		}
	}
	
}
