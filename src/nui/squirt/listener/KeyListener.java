package nui.squirt.listener;

import nui.squirt.Keyboard;
import nui.squirt.event.KeyEvent;


public interface KeyListener {
	
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void keyTyped(KeyEvent e);
	
	// for internal use
	// addKeyListener() and removeKeyListener() methods should call these
	public void addKeyboard(Keyboard k);
	public void removeKeyboard(Keyboard k);

}
