package nui.squirt;

import java.awt.Dimension;
import java.util.Collection;

public interface Component {
	
//	public void setRenderer(Renderer r);
//	public Renderer getRenderer();
	
//	public void setX(float x);
//	public float getX();
//	public void setY(float y);
//	public float getY();
	
//	public void setRotation(float theta);
//	public float getRotation();
	
//	public void setVisible(boolean v);
//	public boolean isVisible();
	
//	public void render();
	
	public void setMaximumSize(Dimension d);
	public Dimension getMaximumSize();
	
	public void setMinimumSize(Dimension d);
	public Dimension getMinimumSize();
	
	public void setPreferredSize(Dimension d);
	public Dimension getPreferredSize();
	
	public boolean hasLayout();
	public LayoutManager getLayout();
	
	public void addContext(Context c);
	public Collection<Context> getContexts();
	
}
