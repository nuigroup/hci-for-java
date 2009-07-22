package nui.squirt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import nui.squirt.component.AbstractContainer;
import nui.squirt.controlpoint.MouseControlPoint;
import nui.squirt.controlpoint.TUIOControlPoint;
import nui.squirt.listener.ControlPointListener;
import nui.squirt.util.AffineTransformStack;
import processing.core.PApplet;
import processing.core.PVector;
import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;

@SuppressWarnings("serial")
public class NUIController extends AbstractContainer implements TuioListener {
	
	private static NUIController n;
	
	private TuioClient tc;
	
	private HashMap<TuioCursor, TUIOControlPoint> tuioControlPoints = new HashMap<TuioCursor, TUIOControlPoint>();
	
	private float screenWidth;
	private float screenHeight;

	private MouseControlPoint mouseControlPoint;
	
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
			NUIController.getInstance().screenWidth = width;
			NUIController.getInstance().screenHeight = height;
			NUIController.getInstance().getTransformMatrix().translate(width/2, height/2);
			smooth();
			
			textFont(createFont("Helvetica", 32));
		}
		
		@Override
		public void draw() {
			getInstance().preRender(this);
			getInstance().render(this);
			getInstance().postRender(this);
		}
		
		@Override
		public void mousePressed() {
			getInstance().addMouseControlPoint(new PVector(mouseX, mouseY));
		}
		
		@Override
		public void mouseReleased() {
			getInstance().removeMouseControlPoint();
		}
		
		@Override
		public void mouseDragged() {
			getInstance().updateMouseControlPoint(new PVector(mouseX, mouseY));
		}
	}
	
	public static NUIController getInstance() {
		if (n == null)
			n = new NUIController();
		return n;
	}
	
	public void start() {
		PApplet.main(new String[]{ "--present", "nui.squirt.NUIController$SquirtPApplet" });
		tc = new TuioClient();
		tc.addTuioListener(this);
		tc.connect();
	}

	public void update() {}

	public void preRender(PApplet p) {
		super.preRender(p);
		
		p.background(255);
	}

	public void render(PApplet p) {
		List<Component> l = new ArrayList<Component>(getComponents());
		for (Component c: l) {
			c.preRender(p);
			c.render(p);
			c.postRender(p);
		}
		
		Collection<TUIOControlPoint> tcps = tuioControlPoints.values();
		for (TUIOControlPoint tcp: tcps) {
			p.ellipseMode(PApplet.CENTER);
			p.fill(tcp.isDead() ? p.color(255, 0, 0, 150) : p.color(150, 150));
			p.stroke(0);
			p.strokeWeight(1);

			
			PVector v = transformToLocalSpace(new PVector(tcp.getX(), tcp.getY()));
			p.ellipse(v.x, v.y, 30, 30);
			p.fill(0);
			p.text(tcp.getCursor().getCursorID(), v.x, v.y);
		}
	}

	public boolean canAcceptMoreControlPoints() {
		return true;
	}

	public boolean offer(ControlPoint cp) {
		ListIterator<Component> i = getComponents().listIterator(getComponents().size());
		while (i.hasPrevious()) {
			Component c = i.previous();
			if (c.offer(cp)) {
				i.remove();
				getComponents().add(c);
				// TODO fix having this silly cast
				if (c instanceof ControlPointListener) {
					cp.addControlPointListener((ControlPointListener) c);
				}
				return true;
			}
		}
		return false;
	}

	public void addTuioCursor(TuioCursor c) {
		TUIOControlPoint tcp = new TUIOControlPoint(c, screenWidth, screenHeight);
		tc.addTuioListener(tcp);
		AffineTransformStack s = new AffineTransformStack();
		s.pushTransform();
		s.translate(screenWidth/2, screenHeight/2);
		offer(tcp);
		tcp.fireControlPointCreatedEvent();
		
		tuioControlPoints.put(c, tcp);
	}

	public void removeTuioCursor(TuioCursor c) {
		tuioControlPoints.remove(c);
	}

	public void updateTuioCursor(TuioCursor c) {}

	public void addTuioObject(TuioObject o) {
		// TODO Auto-generated method stub
		
	}

	public void removeTuioObject(TuioObject o) {
		// TODO Auto-generated method stub
		
	}

	public void updateTuioObject(TuioObject o) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(TuioTime t) {
		// TODO Auto-generated method stub
		
	}
	
	public void addMouseControlPoint(PVector initialPoint) {
		if (mouseControlPoint != null) {
			mouseControlPoint.kill();
		}
		mouseControlPoint = new MouseControlPoint(initialPoint);
		offer(mouseControlPoint);
		mouseControlPoint.fireControlPointCreatedEvent();
	}
	
	public void removeMouseControlPoint() {
		if (mouseControlPoint != null) {
			mouseControlPoint.kill();
		}
		mouseControlPoint = null;
	}
	
	public void updateMouseControlPoint(PVector newPoint) {
		if (mouseControlPoint != null) {
			mouseControlPoint.addToPath(newPoint);
		}
	}

	public void controlPointCreated(ControlPoint cp) {
		// TODO Auto-generated method stub
		
	}

	public void controlPointDied(ControlPoint cp) {
		// TODO Auto-generated method stub
		
	}

	public void controlPointUpdated(ControlPoint cp) {
		// TODO Auto-generated method stub
		
	}

}
