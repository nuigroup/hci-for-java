package nui.squirt;

import nui.squirt.listener.ControlPointListener;


public interface ControlPoint {
	
	public boolean isDead();
	public void kill();

	public float getX();
	public float getY();
	public float getPreviousX();
	public float getPreviousY();
	
	public void addControlPointListener(ControlPointListener l);
	public void fireControlPointCreatedEvent();
	public void fireControlPointDiedEvent();
	public void fireControlPointUpdatedEvent();
}
