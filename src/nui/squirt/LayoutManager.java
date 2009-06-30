package nui.squirt;

import java.util.Collection;

public interface LayoutManager {
	
	public void layout(Context t);
	
	public Collection<Context> getContexts();
	
	public Context addComponent(Component c);
}
