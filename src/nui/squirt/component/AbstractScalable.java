package nui.squirt.component;

import nui.squirt.Scalable;

public abstract class AbstractScalable extends AbstractComponent implements Scalable {
	
	public AbstractScalable(float x, float y) {
		super(x, y);
	}

	private float scale = 1;

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;		
	}
	
}
