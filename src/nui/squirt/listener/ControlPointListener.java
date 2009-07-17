package nui.squirt.listener;

import nui.squirt.ControlPoint;

public interface ControlPointListener {
	
	public void controlPointCreated(ControlPoint cp);
	public void controlPointDied(ControlPoint cp);
	public void controlPointUpdated(ControlPoint cp);

}
