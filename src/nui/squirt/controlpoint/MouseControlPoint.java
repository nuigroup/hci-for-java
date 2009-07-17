package nui.squirt.controlpoint;

import java.util.Vector;

import processing.core.PVector;

public class MouseControlPoint extends AbstractControlPoint {
	
	Vector<PVector> path = new Vector<PVector>();
	
	public MouseControlPoint(PVector initialPoint) {
		path.add(initialPoint);
	}

	public float getPreviousX() {
		return path.size() > 1 ? path.get(path.size() - 2).x : path.lastElement().x;
	}

	public float getPreviousY() {
		return path.size() > 1 ? path.get(path.size() -2).y : path.lastElement().y;
	}

	public float getX() {
		return path.lastElement().x;
	}

	public float getY() {
		return path.lastElement().y;
	}

	public void addToPath(PVector newPoint) {
		path.add(newPoint);
		fireControlPointUpdatedEvent();
	}
}
