package nui.squirt.controlpoint;

import java.util.Vector;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioPoint;
import TUIO.TuioTime;

public class TUIOControlPoint extends AbstractControlPoint implements TuioListener {
	
	private TuioCursor cursor;
	
	private float screenWidth;
	private float screenHeight;

	public TUIOControlPoint(TuioCursor c, float screenWidth, float screenHeight) {
		this.cursor = c;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	public TuioCursor getCursor() {
		return cursor;
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

	public void addTuioCursor(TuioCursor tcur) {}

	public void removeTuioCursor(TuioCursor tcur) {
		if (tcur.equals(cursor)) {
			kill();
		}
	}

	public void updateTuioCursor(TuioCursor tcur) {
		if (tcur.equals(cursor)) {
			fireControlPointUpdatedEvent();
		}
	}

	public void refresh(TuioTime ftime) {}

	public void addTuioObject(TuioObject tobj) {}

	public void removeTuioObject(TuioObject tobj) {}

	public void updateTuioObject(TuioObject tobj) {}

}
