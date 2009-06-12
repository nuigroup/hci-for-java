package nui.squirt.demo;

import nui.squirt.NUIController;
import nui.squirt.component.Button;
import nui.squirt.component.Frame;
import nui.squirt.component.Image;
import nui.squirt.component.Knob;
import nui.squirt.component.Label;
import nui.squirt.component.Slider;
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
	private Label knobLabel;
	private float diff = (float) 0.01;
	
	private Slider s;
	private Label sLabel;
	
	private Image img;
//	private PImage img;

	private Button moving;

	
	@Override
	public void setup() {
		size(screen.width, screen.height);
		smooth();
		rectMode(CENTER);
		ellipseMode(CENTER);
		imageMode(CENTER);
		
//		img = createImage(height-80, height-100, RGB);
//		img.loadPixels();
//		for (int i = 0; i < img.pixels.length; i++) {
//			img.pixels[i] = color(random(255), random(255), random(255));
//		}
//		img.updatePixels();
		
		controller = new NUIController(new ProcessingRenderingEngine(this));
		
		img = new Image(width/2, height/2, "squirtUI.png", width/2, height/2);
		img.setScale((float) 0.5);
		controller.addComponent(img);
		
		f = new Frame(width/2, height/2, width/2, height/2);
		controller.addComponent(f);
		NUIController.setParentChildPair(f, new Label(0, 0, "Frame0"));
		NUIController.setParentChildPair(f, new Label(60-f.getWidth()/2, 30-f.getHeight()/2, "Label"));
		NUIController.setParentChildPair(f, new Button(50, -50, "Button"));
		
		f1 = new Frame(width*3/4+width/8, height/2+height/8, width/8, height/8);
		controller.addComponent(f1);
		NUIController.setParentChildPair(f1, new Label(0, 0, "Frame1"));
		
		// Adding sub-component prior to adding to controller
		f2 = new Frame(width*3/4+width/8, height/4+height/8, width/8, height/8);
		NUIController.setParentChildPair(f2, new Label(0, 0, "Frame2"));
		controller.addComponent(f2);
		
		moving = new Button(120, 120, "Elusive");
		
		Frame f3 = new Frame(width/4, height/4, Math.max(width/6, height/6), Math.max(width/6, height/6));
		k = new Knob(0, 0, f3.getHeight()/2, 0, 100, -((float) Math.PI)*3/4, ((float) Math.PI)/2, 50);
		knobLabel = new Label(0, f3.getHeight()*3/8, Float.toString(k.getValue()));
		NUIController.setParentChildPair(f3, k);
		NUIController.setParentChildPair(f3, knobLabel);
		
		controller.addComponent(f3);
		
		Frame f4 = new Frame(width/2, height*7/8, width*7/8, height*7/32);
		s = new Slider(0, 0, width*3/4, 0, 100, 75);
		s.setRotation((float) (PI*0.9/2));
		sLabel = new Label(0, f4.getHeight()/4, Float.toString(s.getValue()));
		NUIController.setParentChildPair(f4, s);
		NUIController.setParentChildPair(f4, sLabel);
		
		controller.addComponent(f4);
	}
	
	@Override
	public void draw() {
		background(255);
		imageMode(CENTER);
//		image(img, width/2, height/2);
		
		f.setRotation((float) (f.getRotation()+0.01));
		
		if (f.getRotation()%TWO_PI < 0.01) {
			NUIController.setParentChildPair(f, moving);
			if (diff == (float) 0.01)
				diff = (float) -0.01;
			else diff = (float) 0.01;
		}
		else if (f.getRotation()%PI < 0.01) {
			NUIController.endParentChildPair(f, moving);
		}
		
		img.setRotation(img.getRotation()+diff);
		
		k.setRotation((float) (k.getRotation()+diff));
		knobLabel.setText(Float.toString(k.getValue()));
		
		s.setValue(s.getValue()+20*diff);
		sLabel.setText(Float.toString(s.getValue()));
		
		controller.render();
	}

	public static void main(String[] args) {
		PApplet.main(new String[]{ "--present", "nui.squirt.demo.NUIPAppletDemo" });
	}
}
