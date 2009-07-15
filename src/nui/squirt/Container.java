package nui.squirt;

import java.util.List;

public interface Container extends Component {
	
	public void add(Component c);
	public void add(Component c, Object constraints);
	public void remove(Component c);
	public List<Component> getComponents();

}
