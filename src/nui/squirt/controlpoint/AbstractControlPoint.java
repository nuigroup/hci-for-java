package nui.squirt.controlpoint;

import nui.squirt.ControlPoint;


public abstract class AbstractControlPoint implements ControlPoint {
	
	public boolean changed = false;
	public boolean dead = false;
	
	public boolean isChanged() {
		return changed;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void kill() {
		this.dead = true;
	}
	
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
}