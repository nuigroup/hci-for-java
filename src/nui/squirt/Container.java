package nui.squirt;

import java.util.Collection;

public interface Container extends Component {
	
	public void add(Component c);
	public void add(Component c, Object constraints);
	public void remove(Component c);
	public Collection<Component> getComponents();

}
