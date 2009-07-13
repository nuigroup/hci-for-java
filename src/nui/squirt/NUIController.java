package nui.squirt;

import nui.squirt.component.AbstractContainer;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class NUIController extends AbstractContainer {
	
	private static NUIController n;
	
	public NUIController() {
		this(0, 0);
	}
	
	public NUIController(float x, float y) {
		super(x, y);
	}

	public static class SquirtPApplet extends PApplet {
		@Override
		public void setup() {
			size(screen.width, screen.height);
			smooth();
			
			textFont(createFont("Helvetica", 32));
		}
		
		@Override
		public void draw() {
			getInstance().preRender(this);
			getInstance().render(this);
			getInstance().postRender(this);
		}
	}
	
	public static NUIController getInstance() {
		if (n == null)
			n = new NUIController();
		return n;
	}
	
	public void start() {
		PApplet.main(new String[]{ "--present", "nui.squirt.NUIController$SquirtPApplet" });
	}

	public void update() {}

	public void preRender(PApplet p) {
		update();
		
		p.translate(p.width/2, p.height/2);
		
		p.background(255);
	}

	public void postRender(PApplet p) {}

	public void render(PApplet p) {
		for (Component c: getComponents()) {
			c.preRender(p);
			c.render(p);
			c.postRender(p);
		}
	}

}
