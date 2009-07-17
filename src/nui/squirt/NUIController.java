package nui.squirt;

import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map.Entry;

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
			smooth();
			
			textFont(createFont("Helvetica", 32));
		}
		
		@Override
		public void draw() {
			AffineTransformStack s = new AffineTransformStack();
			getInstance().preRender(this, s);
			getInstance().render(this, s);
			getInstance().postRender(this, s);
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

	public void update(AffineTransformStack s) {
		s.pushTransform();
		s.translate(screenWidth/2, screenHeight/2);
	}

	public void preRender(PApplet p, AffineTransformStack s) {
		update(s);
		
		p.pushMatrix();
		p.translate(p.width/2, p.height/2);
		
		p.background(255);
	}

	public void postRender(PApplet p, AffineTransformStack s) {
		s.popTransform();
		p.popMatrix();
	}

	public void render(PApplet p, AffineTransformStack s) {
		for (Component c: getComponents()) {
			c.preRender(p, s);
			c.render(p, s);
			c.postRender(p, s);
		}
		
		for (Entry<TuioCursor, TUIOControlPoint> tuioEntry: tuioControlPoints.entrySet()) {
			TuioCursor tc = tuioEntry.getKey();
			TUIOControlPoint tcp = tuioEntry.getValue();
			
			p.ellipseMode(PApplet.CENTER);
			p.fill(tcp.isDead() ? p.color(255, 0, 0, 150) : p.color(150, 150));
			p.stroke(0);
			p.strokeWeight(1);

			
			try {
				Point2D xy = s.inverseTransform(tcp.getX(), tcp.getY());
				p.ellipse((float) xy.getX(), (float) xy.getY(), 30, 30);
				p.fill(0);
				p.text(tc.getCursorID(), (float) xy.getX(), (float) xy.getY());
			} catch (NoninvertibleTransformException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean canAcceptMoreControlPoints() {
		return true;
	}

	public boolean offer(ControlPoint cp, AffineTransformStack s) {
		ListIterator<Component> i = getComponents().listIterator(getComponents().size());
		while (i.hasPrevious()) {
			Component c = i.previous();
			if (c.offer(cp, s)) {
				i.remove();
				getComponents().add(c);
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
		offer(tcp, s);
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
		AffineTransformStack s = new AffineTransformStack();
		s.translate(screenWidth/2, screenHeight/2);
		offer(mouseControlPoint, s);
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
