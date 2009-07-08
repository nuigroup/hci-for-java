package nui.squirt.context.spatial;

import nui.squirt.Component;
import nui.squirt.Context;

public class RectangularRegionContext extends SpatialContext {
	
	private float x = 0;
	private float y = 0;
	private float width = 0;
	private float height = 0;
	private float rotation = 0;
	private float scale = 1;

	public RectangularRegionContext(Component c) {
		super(c);
	}
	
	public RectangularRegionContext(Component c, Component parent) {
		super(c, parent);
	}

	public boolean matches(Context c) {
		// TODO Auto-generated method stub
		return false;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void invalidate() {
		this.valid = false;
		if (getParentComponent() != null) {
			for (Context c: getParentComponent().getContexts()) {
				if (c instanceof SpatialContext)
					c.invalidate();
			}
		}
	}

	public void validate() {
		if (getComponent().hasLayout()) {
			for (Context c: getComponent().getLayout().getManagedContexts()) {
				if (c instanceof SpatialContext && !c.isValid())
					c.validate();
			}
			getComponent().getLayout().layout(this);
		}
		this.valid = true;
	}
}
