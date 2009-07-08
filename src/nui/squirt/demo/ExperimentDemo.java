package nui.squirt.demo;

import java.awt.geom.Point2D;

import nui.squirt.NUIController;
import nui.squirt.component.Button;
import nui.squirt.component.Label;
import nui.squirt.event.ActionEvent;
import nui.squirt.listener.ActionListener;
import nui.squirt.render.processing.ProcessingRenderingEngine;
import processing.core.PApplet;

public class ExperimentDemo extends PApplet {
	
	public class ButtonLabelChanger implements ActionListener {
		int pressCount = 0;
		public void actionPerformed(ActionEvent e) {
			Button butt = (Button) e.getSource();
			butt.setText("Pressed " + ++pressCount + " times");
		}
	}
	
	private static final long serialVersionUID = -7289243930244218793L;
	
	NUIController controller;

	private Button b;

	@Override
	public void setup() {
		size(400, 400);
		smooth();
		
		b = new Button("Press Me!");
		b.addActionListener(new ButtonLabelChanger());
		
		Label l = new Label(Double.toString(Math.random()));
		
		
//		Frame f = new Frame(new BorderLayout());
//		Frame f1 = new Frame(new FlowLayout());
//		
//		f.addChild(b, BorderLayout.PAGE_END);
//		f.addChild(l, BorderLayout.CENTER);
//		
//		f1.addChild(b);
//		f1.addChild(l);
//		
		controller = new NUIController(new ProcessingRenderingEngine(this));
//		controller.addComponent(f);
//		controller.addComponent(f1);
		controller.addChild(b, new Point2D.Double(Math.random()*width/2-width/4, Math.random()*height/2-height/4));
		controller.addChild(b.getLabel(), new Point2D.Double(Math.random()*width/2-width/4, Math.random()*height/2-height/4));
		controller.addChild(l, new Point2D.Double(Math.random()*width/2-width/4, Math.random()*height/2-height/4));
	}
	
	@Override
	public void draw() {
		background(255);
		translate(width/2, height/2);
		controller.render();
		
		if (Math.random() > 0.99) {
			b.press();
			b.release();
		}
	}
	
	public static void main(String[] args) {
		PApplet.main(new String[] {"nui.squirt.demo.ExperimentDemo"});
	}

}
