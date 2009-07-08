package nui.squirt;

import java.util.Collection;

public interface Container extends Component {
	
	public void addChild(Component c);
	public void addChild(Component c, Object constraints);
	public void removeChild(Component c);
	public Collection<Component> getChildren();

}
