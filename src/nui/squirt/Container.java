package nui.squirt;

import java.util.List;

public interface Container extends Component {
	
	public void addChild(Component c);
	public List<Component> getChildren();

}
