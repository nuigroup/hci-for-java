package nui.squirt.component;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import nui.squirt.Component;
import nui.squirt.Context;
import nui.squirt.LayoutManager;
import nui.squirt.context.spatial.RectangularRegionContext;
import nui.squirt.event.ActionEvent;


public class Button extends AbstractActionable {
	
	private class ButtonLayoutManager implements LayoutManager {
		private RectangularRegionContext labelContext;
		
		public Collection<Context> getManagedContexts() {
			Collection<Context> contexts = new ArrayList<Context>();
			contexts.add(labelContext);
			return Collections.unmodifiableCollection(contexts);
		}
		
		public void layout(Context t) {
			if (t instanceof RectangularRegionContext && t.getComponent() == Button.this) {
				RectangularRegionContext r = (RectangularRegionContext) t;
				
				// Layout the context for the label
				Dimension preferred = getLabel().getPreferredSize();
				labelContext.setWidth(preferred.width);
				labelContext.setHeight(preferred.height);
				labelContext.setRotation(0);
				labelContext.setScale(1);
				labelContext.setX(-5);
				labelContext.setY(0);
				labelContext.setParentComponent(Button.this);
				
				// Update the Context for the Button
				r.setWidth(labelContext.getWidth() + 20);
				r.setHeight(labelContext.getHeight() + 10);
			}
		}
		
		public Context addComponent(Component c) {
			if (c == getLabel()) {
				RectangularRegionContext newContext = new RectangularRegionContext(c, Button.this);
				newContext.setWidth(c.getPreferredSize().width);
				newContext.setHeight(c.getPreferredSize().height);
				c.addContext(newContext);
				labelContext = newContext;
				return newContext;
			}
			else return null;
		}

		public Context addComponent(Component c, Object constraints) {
			return addComponent(c);
		}
	}
	
	private Label label;

	private boolean pressed;
	
	private LayoutManager buttonLayout = new ButtonLayoutManager();
	
	public Button(String text) {
		this.label = new Label(text);
		getLayout().addComponent(this.label);
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

	public LayoutManager getLayout() {
		return buttonLayout;
	}
	
}
