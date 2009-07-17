package nui.squirt.component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import nui.squirt.Actionable;
import nui.squirt.event.ActionEvent;
import nui.squirt.listener.ActionListener;
import nui.squirt.util.AffineTransformStack;
import processing.core.PApplet;


public class Button extends Rectangle implements Actionable {
	
	private static final Color PRESSED_COLOR = Color.GRAY;
	private static final Color NOT_PRESSED_COLOR = Color.LIGHT_GRAY;
	private static final Color BORDER_COLOR = Color.BLACK;
	private static final float BORDER_WIDTH = 4;

	private Label label;

	private boolean pressed;

	private Collection<ActionListener> listeners = new ArrayList<ActionListener>();
	
//	private HashMap<ControlPoint, Boolean> controlPoints = new HashMap<ControlPoint, Boolean>();
	
	public Button(float x, float y, String text) {
		super(x, y, 0, 0);
		this.label = new Label(0, 0, text);
	}

	public Label getLabel() {
		return label;
	}
	
	public void setText(String text) {
		getLabel().setText(text);
	}

	public boolean isPressed() {
		return pressed;
	}
	
	public void press() {
		this.pressed = true;
	}
	
	public void release() {
		this.pressed = false;
		fireAction(new ActionEvent(this));
	}

	public void addActionListener(ActionListener a) {
		listeners.add(a);
	}

	public void fireAction(ActionEvent e) {
		for (ActionListener l: listeners) {
			l.actionPerformed(e);
		}
	}

	public String getAction() {
		return getLabel().getText();
	}

	public void setAction(String action) {
		setText(action);
	}
	
	@Override
	public void update(AffineTransformStack s) {
		super.update(s);
		
		setFillColor(isPressed() ? PRESSED_COLOR : NOT_PRESSED_COLOR);
		setStrokeColor(BORDER_COLOR);
		setStrokeWeight(BORDER_WIDTH);
		
		setWidth((float) (label.getWidth() * 1.4));
		setHeight((float) (label.getHeight() * 1.6));
	}
	
	@Override
	public void render(PApplet p, AffineTransformStack s) {
		super.render(p, s);
		
		getLabel().preRender(p, s);
		getLabel().render(p, s);
		getLabel().postRender(p, s);
	}
	
//	@Override
//	protected void addControlPoint(ControlPoint cp) {
//		controlPoints.put(cp, Boolean.TRUE);
//	}
//	
//	@Override
//	protected Collection<ControlPoint> getControlPoints() {
//		return controlPoints.keySet();
//	}
	
}
