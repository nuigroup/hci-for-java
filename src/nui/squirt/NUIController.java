package nui.squirt;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;

import nui.squirt.component.AbstractContainer;
import nui.squirt.controlpoint.MouseControlPoint;
import nui.squirt.controlpoint.TUIOControlPoint;
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
	
	private Queue<ControlPoint> newControlPointsQueue = new ConcurrentLinkedQueue<ControlPoint>();

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
		TuioClient tc = new TuioClient();
		tc.addTuioListener(this);
		tc.connect();
	}

	public void update(AffineTransformStack s) {
		s.pushTransform();
		s.translate(screenWidth/2, screenHeight/2);
	
		ControlPoint cp = newControlPointsQueue.poll();
		while (cp != null && !cp.isDead()) {
			ListIterator<Component> i = getComponents().listIterator(getComponents().size());
			while (i.hasPrevious() && !i.previous().offer(cp, s));
			cp = newControlPointsQueue.poll();
		}
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
		// TODO Auto-generated method stub
		return false;
	}

	public void addTuioCursor(TuioCursor c) {
AffineTransform t = new AffineTransform();
t.translate(screenWidth/2, screenHeight/2);
double[] origXY = { c.getScreenX((int) screenWidth), c.getScreenY((int) screenHeight) };
double[] newXY = new double[2];
try {
	t.inverseTransform(origXY, 0, newXY, 0, 1);
} catch (NoninvertibleTransformException e) {
	e.printStackTrace();
}
System.out.println("Adding " + c + " at " + newXY[0] + "," + newXY[1]);
		TUIOControlPoint tcp = new TUIOControlPoint(c, screenWidth, screenHeight);
		newControlPointsQueue.offer(tcp);
		tuioControlPoints.put(c, tcp);
	}

	public void removeTuioCursor(TuioCursor c) {
AffineTransform t = new AffineTransform();
t.translate(screenWidth/2, screenHeight/2);
double[] origXY = { c.getScreenX((int) screenWidth), c.getScreenY((int) screenHeight) };
double[] newXY = new double[2];
try {
	t.inverseTransform(origXY, 0, newXY, 0, 1);
} catch (NoninvertibleTransformException e) {
	e.printStackTrace();
}
System.out.println("Removing " + c + " at " + newXY[0] + "," + newXY[1]);
		TUIOControlPoint tcp = tuioControlPoints.remove(c);
		if (tcp != null) {
			tcp.kill();
		}
	}

	public void updateTuioCursor(TuioCursor c) {
AffineTransform t = new AffineTransform();
t.translate(screenWidth/2, screenHeight/2);
double[] origXY = { c.getScreenX((int) screenWidth), c.getScreenY((int) screenHeight) };
double[] newXY = new double[2];
try {
	t.inverseTransform(origXY, 0, newXY, 0, 1);
} catch (NoninvertibleTransformException e) {
	e.printStackTrace();
}
System.out.println("Updating " + c + " at " + newXY[0] + "," + newXY[1]);
		TUIOControlPoint tcp = tuioControlPoints.get(c);
		if (tcp != null) {
			tcp.setChanged(true);
		}
	}

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
		MouseControlPoint mcp = new MouseControlPoint(initialPoint);
		newControlPointsQueue.offer(mcp);
		mouseControlPoint = mcp;
	}
	
	public void removeMouseControlPoint() {
		if (mouseControlPoint != null) {
			mouseControlPoint.kill();
		}
	}
	
	public void updateMouseControlPoint(PVector newPoint) {
		if (mouseControlPoint != null) {
			mouseControlPoint.addToPath(newPoint);
			mouseControlPoint.setChanged(true);
		}
	}

}
