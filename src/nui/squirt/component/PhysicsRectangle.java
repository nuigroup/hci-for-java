package nui.squirt.component;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import nui.squirt.ControlPoint;

import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.joints.MouseJointDef;

import processing.core.PApplet;
import processing.core.PVector;

public class PhysicsRectangle extends Rectangle {
	
	private World world;
	private Body body;
	private MouseJoint mouseJoint;
	
	public PhysicsRectangle(float x, float y, float w, float h, World world) {
		super(x, y, w, h);
		this.world = world;
		
		// Create physics body
		BodyDef bodyDef = new BodyDef();
		bodyDef.position = new Vec2(x, y);
		bodyDef.linearDamping = 0.9f;
		bodyDef.angularDamping = 0.99f;
		this.body = getWorld().createBody(bodyDef);
		
		// Create collision rectangle
		PolygonDef shapeDef = new PolygonDef();
		// Make sure it has non-zero density, otherwise it will have 0 mass and be static
		shapeDef.density = 0.1f;
		shapeDef.setAsBox(getWidth()/2, getHeight()/2);
		body.createShape(shapeDef);
		
		// Set mass of body
		body.setMassFromShapes();
	}
	
	public World getWorld() {
		return world;
	}
	
	public Body getBody() {
		return body;
	}

	public MouseJoint getMouseJoint() {
		return mouseJoint;
	}
	
	@Override
	public void update() {
		super.update();
		
		if (getBody().isFrozen()) setFillColor(Color.DARK_GRAY);
		if (getBody().isSleeping()) setStrokeColor(Color.BLUE);
	}
	
	@Override
	public void render(PApplet p) {
		super.render(p);
		
		Vec2 pos = getBody().getPosition();
		Vec2 worldPos = getBody().getWorldPoint(pos);
		System.out.println(worldPos.x + "," + worldPos.y);
		System.out.println(getBody().getMass());
		
		if (getMouseJoint() != null) {
			p.stroke(Color.RED.getRGB());
			Vec2 localAnchor = getMouseJoint().m_localAnchor;
			Vec2 target = getBody().getLocalPoint(getMouseJoint().m_target);
			p.line(localAnchor.x, localAnchor.y, target.x, target.y);
		}
	}
	
	@Override
	public boolean canAcceptMoreControlPoints() {
		return getMouseJoint() == null;
	}

	public void controlPointCreated(ControlPoint cp) {
		PVector local = transformToLocalSpace(new PVector(cp.getX(), cp.getY()));
		Vec2 pos = getBody().getWorldPoint(new Vec2(local.x, local.y));
		MouseJointDef mouseJointDef = new MouseJointDef();
        mouseJointDef.body1 = getWorld().getGroundBody();
        mouseJointDef.body2 = getBody();
        mouseJointDef.target.set(pos);
        mouseJointDef.maxForce = 100000.0f * getBody().m_mass;
//        mouseJointDef.dampingRatio = 0;
//        mouseJointDef.frequencyHz = 100;
        this.mouseJoint = (MouseJoint) getWorld().createJoint(mouseJointDef);
        
//        getBody().m_linearDamping = 0.001f;
//        getBody().m_angularDamping = 0.001f;
        getBody().wakeUp();
	}

	public void controlPointDied(ControlPoint cp) {
		if (getMouseJoint() != null) {
			getWorld().destroyJoint(getMouseJoint());
			this.mouseJoint = null;
//			getBody().m_linearDamping = 0.7f;
//			getBody().m_angularDamping = 0.7f;
		}
	}

	public void controlPointUpdated(ControlPoint cp) {
		if (getMouseJoint() != null) {
			PVector local = transformToLocalSpace(new PVector(cp.getX(), cp.getY()));
			Vec2 pos = getBody().getWorldPoint(new Vec2(local.x, local.y));
			getMouseJoint().setTarget(pos);
		}
	}
	
	@Override
	public void setWidth(float width) {
		// Cannot change width after creation
	}
	
	@Override
	public void setHeight(float height) {
		// Cannot change height after creation
	}
	
	@Override
	public AffineTransform getTransformMatrix() {
		Vec2 pos = getBody().getPosition();
		Float angle = getBody().getAngle();

		AffineTransform transform = new AffineTransform();
		transform.translate(pos.x, pos.y);
		transform.rotate(angle);
		return transform;
	}

}
