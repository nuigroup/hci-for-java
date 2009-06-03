package nui.squirt;

import java.util.List;

public interface Container extends Component {
	
	public void addComponent(Component c);
	public List<Component> getComponents();

}
