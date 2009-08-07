package nui.squirt;

import nui.squirt.event.TextEvent;
import nui.squirt.listener.TextListener;

public interface TextInput extends Component {
	
	public void addTextListener(TextListener l);
	public void removeTextListener(TextListener l);
	
	public void fireTextChanged(TextEvent e);
	
	public String getText();
	public void setText(String t);

}
