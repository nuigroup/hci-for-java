package nui.squirt;

import nui.squirt.event.ActionEvent;
import nui.squirt.listener.ActionListener;

/*
 * Interface representing Components capable of firing ActionEvents
 */
public interface Actionable extends Component{
	
	public void addActionListener(ActionListener a);
	public void removerActionListener(ActionListener a);
	
	public void fireAction(ActionEvent e);
	
	public String getAction();
	public void setAction(String action);
}
