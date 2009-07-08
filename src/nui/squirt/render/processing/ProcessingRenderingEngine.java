package nui.squirt.render.processing;

import java.awt.Dimension;

import nui.squirt.Component;
import nui.squirt.Context;
import nui.squirt.component.Button;
import nui.squirt.component.Frame;
import nui.squirt.component.Image;
import nui.squirt.component.Knob;
import nui.squirt.component.Label;
import nui.squirt.component.Slider;
import nui.squirt.context.spatial.RectangularRegionContext;
import nui.squirt.render.RenderingEngine;
import processing.core.PApplet;
import processing.core.PFont;

public class ProcessingRenderingEngine implements RenderingEngine {
	
	public interface ProcessingDrawer {
		public void draw(Component c, RectangularRegionContext r);
	}

	public class ImageDrawer implements ProcessingDrawer {
		public void draw(Component c, RectangularRegionContext r) {
			// TODO Auto-generated method stub
		}
	}

	public class SliderDrawer implements ProcessingDrawer {
		public void draw(Component c, RectangularRegionContext r) {
			// TODO Auto-generated method stub
		}
	}

	public class KnobDrawer implements ProcessingDrawer {
		public void draw(Component c, RectangularRegionContext r) {
			// TODO Auto-generated method stub
		}
	}

	public class LabelDrawer implements ProcessingDrawer {
		public void draw(Component c, RectangularRegionContext r) {
			Label l = (Label) c;
			PApplet p = getPApplet();
			
			p.textFont(getFont());
			p.textMode(PApplet.MODEL);
			p.textAlign(PApplet.CENTER, PApplet.CENTER);
			p.fill(25);
			
			l.setPreferredSize(new Dimension(((int) p.textWidth(l.getText())), (int) (p.textAscent()+p.textDescent())));
			p.text(l.getText(), 0, 0);
		}
	}

	public class ButtonDrawer implements ProcessingDrawer {
		public void draw(Component c, RectangularRegionContext r) {
			Button b = (Button) c;
			PApplet p = getPApplet();
			
			p.textFont(getFont());
			p.textMode(PApplet.MODEL);
			p.rectMode(PApplet.CENTER);
			
			p.fill(b.isPressed() ? 170: 200);
			p.stroke(50);
			p.rect(0, 0, r.getWidth(), r.getHeight());
		}
	}

	public class FrameDrawer implements ProcessingDrawer {
		public void draw(Component c, RectangularRegionContext r) {
			// TODO Auto-generated method stub
		}
	}

	private PApplet pApplet;
	private PFont font;
	
	private FrameDrawer frameDrawer;
	private ButtonDrawer buttonDrawer;
	private LabelDrawer labelDrawer;
	private KnobDrawer knobDrawer;
	private SliderDrawer sliderDrawer;
	private ImageDrawer imageDrawer;

	public ProcessingRenderingEngine(PApplet p) {
		this.setPApplet(p);
		PFont f = p.createFont("Helvetica", 20);
		this.setFont(f);
		this.getPApplet().textFont(f);
	}

	public PApplet getPApplet() {
		return pApplet;
	}

	public void setPApplet(PApplet pApplet) {
		this.pApplet = pApplet;
	}

	public PFont getFont() {
		return font;
	}

	public void setFont(PFont font) {
		this.font = font;
	}

	public void render(Component c, Context t) {
		if (t instanceof RectangularRegionContext) {
			RectangularRegionContext r = (RectangularRegionContext) t;
			
			// Ensure the Context is valid.
			// If it isn't, validate() should layout() the Component and validate all child Contexts.
			if (!r.isValid()) {				
				r.validate();
			}
			
			PApplet p = getPApplet();
			
			// Save the current matrix transform so we can step back to it
			p.pushMatrix();
			
			// Perform translation
			p.translate(r.getX(), r.getY());
			p.rotate(r.getRotation());
			p.scale(r.getScale());
			
			// Draw Component c with RectangularRegionContext r on PApplet p
			getDrawer(c).draw(c, r);
			
			// Render the children contexts of this Component
			if (c.hasLayout()) {
				for (Context con: c.getLayout().getManagedContexts()) {
					render(con.getComponent(), con);
				}
			}
			
			// Undo transform
			p.popMatrix();
		}
	}

	private ProcessingDrawer getDrawer(Component c) {
		if (c instanceof Label)
			return getLabelDrawer();
		else if (c instanceof Frame)
			return getFrameDrawer();
		else if (c instanceof Button)
			return getButtonDrawer();
		else if (c instanceof Knob)
			return getKnobDrawer();
		else if (c instanceof Slider)
			return getSliderDrawer();
		else if (c instanceof Image)
			return getImageDrawer();
		else return null;
	}

	public FrameDrawer getFrameDrawer() {
		if (frameDrawer == null)
			frameDrawer = new FrameDrawer();
		return frameDrawer;
	}

	public ButtonDrawer getButtonDrawer() {
		if (buttonDrawer == null)
			buttonDrawer = new ButtonDrawer();
		return buttonDrawer;
	}

	public LabelDrawer getLabelDrawer() {
		if (labelDrawer == null)
			labelDrawer = new LabelDrawer();
		return labelDrawer;
	}
	
	public KnobDrawer getKnobDrawer() {
		if (knobDrawer == null)
			knobDrawer = new KnobDrawer();
		return knobDrawer;
	}

	public SliderDrawer getSliderDrawer() {
		if (sliderDrawer == null)
			sliderDrawer = new SliderDrawer();
		return sliderDrawer;
	}

	public ImageDrawer getImageDrawer() {
		if (imageDrawer == null)
			imageDrawer = new ImageDrawer();
		return imageDrawer;
	}

}
