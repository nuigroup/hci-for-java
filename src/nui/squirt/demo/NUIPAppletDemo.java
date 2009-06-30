package nui.squirt.demo;

import nui.squirt.NUIController;
import nui.squirt.component.Button;
import nui.squirt.component.Frame;
import nui.squirt.component.Image;
import nui.squirt.component.Knob;
import nui.squirt.component.Label;
import nui.squirt.component.Slider;
import nui.squirt.event.ActionEvent;
import nui.squirt.event.ValueEvent;
import nui.squirt.listener.ActionListener;
import nui.squirt.listener.ValueListener;
import nui.squirt.render.processing.ProcessingRenderingEngine;
import processing.core.PApplet;


/* 
 * This demo illustrates the use of the Squirt UI framework.
 * 
 * First this PApplet initializes an NUIController and adds several UI components to it.
 * At each frame it rotates frame 0 and calls render() on the NUIController.
 * The render() function recursively traverses down the component tree, rendering children after rendering the parent.
 * 
 * Notice how the child components of the Frame instances are rendered relative to the parent's position, rotation, and scale.
 * Also notice the transparency of the Frame components when rendered.
 * 
 */
public class NUIPAppletDemo extends PApplet {

	private static final long serialVersionUID = -5298894969210176542L;
	
	private NUIController controller;
	private Frame f, f1, f2;
	
	private Knob k;
	private ValueLabel knobLabel;
	private float diff = (float) 0.01;
	
	private Slider s;
	private ValueLabel sLabel;
	
	private Image img;

	private Button moving;
	
	private Button b;

	
	@Override
	public void setup() {
		size(screen.width, screen.height);
		smooth();
		rectMode(CENTER);
		ellipseMode(CENTER);
		imageMode(CENTER);
		
		controller = new NUIController(new ProcessingRenderingEngine(this));
		
//		img = new Image(width/2, height/2, "squirtUI.png", width/2, height/2);
//		img.setScale((float) 0.5);
//		controller.addComponent(img);
//		
//		f = new Frame(width/2, height/2, width/2, height/2);
//		controller.addComponent(f);
//		f.addChild(new Label(0, 0, "Frame0"));
//		f.addChild(new Label(60-f.getWidth()/2, 30-f.getHeight()/2, "Label"));
//		f.addChild(new Button(50, -50, "Button"));
//		
//		f1 = new Frame(width*3/4+width/8, height/2+height/8, width/8, height/8);
//		controller.addComponent(f1);
//		f1.addChild(new Label(0, 0, "Frame1"));
//		
//		// Adding sub-component prior to adding to controller
//		f2 = new Frame(width*3/4+width/8, height/4+height/8, width/8, height/8);
//		f2.addChild(new Label(0, 0, "Frame2"));
//		controller.addComponent(f2);
//		
//		moving = new Button(120, 120, "Elusive");
//		
//		Frame f3 = new Frame(width/4, height/4, Math.max(width/6, height/6), Math.max(width/6, height/6));
//		k = new Knob(0, 0, f3.getHeight()/2, 0, 100, -((float) Math.PI)*3/4, ((float) Math.PI)/2, 50);
//		knobLabel = new ValueLabel(0, f3.getHeight()*3/8, Float.toString(k.getValue()));
//		k.addValueListener(knobLabel);
//		f3.addChild(k);
//		f3.addChild(knobLabel);
//		
//		controller.addComponent(f3);
//		
//		Frame f4 = new Frame(width/2, height*7/8, width*7/8, height*7/32);
//		s = new Slider(0, 0, width*3/4, 0, 100, 75);
//		s.setRotation((float) (PI*0.9/2));
//		sLabel = new ValueLabel(0, f4.getHeight()/4, Float.toString(s.getValue()));
//		s.addValueListener(sLabel);
//		f4.addChild(s);
//		f4.addChild(sLabel);
//		controller.addComponent(f4);
//		
//		b = new Button(width/4, height/2, "Not Pressed");
//		b.addActionListener(new ButtonLabelChanger());
//		controller.addComponent(b);
	}
	
	@Override
	public void draw() {
		background(255);
		imageMode(CENTER);
		
//		f.setRotation((float) (f.getRotation()+0.01));
//		
//		if (f.getRotation()%TWO_PI < 0.01) {
//			f.addChild(moving);
//			if (diff == (float) 0.01)
//				diff = (float) -0.01;
//			else diff = (float) 0.01;
//			f2.setVisible(false);
//			b.press();
//		}
//		else if (f.getRotation()%PI < 0.01) {
//			f.removeChild(moving);
//			f2.setVisible(true);
//			b.release();
//		}
//		
//		img.setRotation(img.getRotation()+diff);
//		
//		k.setRotation((float) (k.getRotation()+diff));
//		knobLabel.setText(Float.toString(k.getValue()));
//		
//		s.setValue(s.getValue()+20*diff);
//		sLabel.setText(Float.toString(s.getValue()));
		
		controller.render();
	}

	public static void main(String[] args) {
		PApplet.main(new String[]{ "--present", "nui.squirt.demo.NUIPAppletDemo" });
	}

	public class ValueLabel extends Label implements ValueListener {
		
	
		public ValueLabel(String t) {
			super(t);
		}
	
		public void valueChanged(ValueEvent e) {
			super.setText(Float.toString(e.getNewValue()));
		}
		
		@Override
		public void setText(String t) {
			// Empty. We don't want anything external to change the text of this Label.
		}
	
	}

	public class ButtonLabelChanger implements ActionListener {
		
		int pressCount = 0;
	
		public void actionPerformed(ActionEvent e) {
			Button butt = (Button) e.getSource();
			butt.setText("Pressed " + ++pressCount + " times");
		}
	}
}
