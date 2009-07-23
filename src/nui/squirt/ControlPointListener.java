package nui.squirt;


public interface ControlPointListener {
	
	public void controlPointCreated(ControlPoint cp);
	public void controlPointDied(ControlPoint cp);
	public void controlPointUpdated(ControlPoint cp);
	
	/**
	 * @return this Component can accept offers of additional ControlPoints
	 */
	public boolean canAcceptMoreControlPoints();
	
	/**
	 * @param a ControlPoint to potentially be offered to this Component
	 * @return the ControlPoint falls within the interactive area of a Component and thus should be offered
	 */
	public boolean isUnderPoint(ControlPoint cp);
	
	/**
	 *  This function assumes that recent calls have been made to canAcceptMoreControlPoints()
	 *  and isUnderPoint() before the point is offered.
	 * @param a ControlPoint to be offered to this Component
	 * @return the ControlPoint is accepted and no other Components should be offered it.
	 */
	public boolean offer(ControlPoint cp);

}
