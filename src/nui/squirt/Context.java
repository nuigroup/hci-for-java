package nui.squirt;


public interface Context {
	
	public Component getComponent();
	public void setComponent(Component c);
	
	public boolean matches(Context c);
	
	public void setParentComponent(Component c);
	public Component getParentComponent();
	
	// If a Context is invalid, then its data may need to be updated by the appropriate LayoutManager
	public boolean isValid();
	public void validate();
	public void invalidate();

}
