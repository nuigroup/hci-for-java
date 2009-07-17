package nui.squirt.controlpoint;

import java.util.ArrayList;
import java.util.Collection;

import nui.squirt.ControlPoint;
import nui.squirt.listener.ControlPointListener;


public abstract class AbstractControlPoint implements ControlPoint {
	
	public boolean dead = false;
	private Collection<ControlPointListener> listeners = new ArrayList<ControlPointListener>();
	
	public boolean isDead() {
		return dead;
	}
	
	public void kill() {
		this.dead = true;
		fireControlPointDiedEvent();
	}
	
	public void addControlPointListener(ControlPointListener l) {
		listeners.add(l);
	}
	
	protected Collection<ControlPointListener> getControlPointListeners() {
		return listeners;
	}
	
	public void fireControlPointCreatedEvent() {
		for (ControlPointListener l: getControlPointListeners()) {
			l.controlPointCreated(this);
		}
	}
	
	public void fireControlPointDiedEvent() {
		for (ControlPointListener l: getControlPointListeners()) {
			l.controlPointDied(this);
		}
	}
	
	public void fireControlPointUpdatedEvent() {
		for (ControlPointListener l: getControlPointListeners()) {
			l.controlPointUpdated(this);
		}
	}
	
}