package nui.squirt;


public interface ControlPoint {
	
	public boolean isDead();
	public void kill();

	public float getX();
	public float getY();
	public float getPreviousX();
	public float getPreviousY();
	
	public boolean isChanged();
	public void setChanged(boolean changed);
}
