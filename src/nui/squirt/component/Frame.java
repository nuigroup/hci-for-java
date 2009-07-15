package nui.squirt.component;

import java.util.List;

import nui.squirt.Component;
import nui.squirt.Container;
import processing.core.PApplet;


public class Frame extends Rectangle implements Container {

	public Frame(float x, float y) {
		super(x, y, 0, 0);
		// TODO Auto-generated constructor stub
	}

	public void postRender(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	public void preRender(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	public void render(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void add(Component c) {
		// TODO Auto-generated method stub
		
	}

	public void add(Component c, Object constraints) {
		// TODO Auto-generated method stub
		
	}

	public List<Component> getComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(Component c) {
		// TODO Auto-generated method stub
		
	}
	
	// Old rendering code
//	public void draw(Frame f) {
//	ProcessingRenderingEngine engine = (ProcessingRenderingEngine) getRenderingEngine();
//	PApplet pApplet = engine.getPApplet();
//	
//	// Draw a rectangle representing the frame
//	pApplet.rectMode(PApplet.CENTER);
//	pApplet.noStroke();
//	pApplet.fill(75, 75, 75, 150);
//	pApplet.rect(0, 0, f.getWidth(), f.getHeight());
//}

}
