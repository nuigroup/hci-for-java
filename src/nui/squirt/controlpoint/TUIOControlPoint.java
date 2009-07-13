package nui.squirt.controlpoint;

import java.util.Vector;

import nui.squirt.ControlPoint;
import TUIO.TuioCursor;
import TUIO.TuioPoint;

public class TUIOControlPoint implements ControlPoint {
	
	private TuioCursor cursor;
	
	private float screenWidth;
	private float screenHeight;
	
	private boolean dead = false;
	private boolean changed = false;

	public TUIOControlPoint(TuioCursor c, float screenWidth, float screenHeight) {
		this.cursor = c;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	public boolean isDead() {
		return dead;
	}

	public void kill() {
		dead = true;
	}

	public float getX() {
		return cursor.getScreenX((int) screenWidth);
	}

	public float getY() {
		return cursor.getScreenY((int) screenHeight);
	}

	public float getPreviousX() {
		Vector<TuioPoint> v = cursor.getPath();
		return v.get(v.size()-2).getScreenX((int) screenWidth);
	}

	public float getPreviousY() {
		Vector<TuioPoint> v = cursor.getPath();
		return v.get(v.size()-2).getScreenY((int) screenHeight);
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

}
