package nui.squirt;

import java.util.Collection;

public interface LayoutManager {
	
	public void layout(Context t);
	
	public Collection<Context> getManagedContexts();
	
	public Context addComponent(Component c);
	
	public Context addComponent(Component c, Object constraints);
}
