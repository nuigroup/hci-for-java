package nui.squirt.controlpoint;

import java.util.Vector;

import TUIO.TuioCursor;
import TUIO.TuioPoint;

public class TUIOControlPoint extends AbstractControlPoint {
	
	private TuioCursor cursor;
	
	private float screenWidth;
	private float screenHeight;

	public TUIOControlPoint(TuioCursor c, float screenWidth, float screenHeight) {
		this.cursor = c;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
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

}
