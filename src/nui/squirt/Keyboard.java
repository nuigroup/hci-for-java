package nui.squirt;

import nui.squirt.event.KeyEvent;
import nui.squirt.listener.KeyListener;

public interface Keyboard extends Component {
	
	public void addKeyListener(KeyListener l);
	public void removeKeyListener(KeyListener l);
	
	public void fireKeyPressed(KeyEvent e);
	public void fireKeyReleased(KeyEvent e);
	public void fireKeyTyped(KeyEvent e);

}
