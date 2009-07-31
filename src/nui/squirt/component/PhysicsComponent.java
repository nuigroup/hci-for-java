package nui.squirt.component;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.joints.MouseJointDef;

import nui.squirt.ControlPoint;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class PhysicsComponent extends AbstractComponent {

	private World world;
	private Body body;
	private MouseJoint mouseJoint;
	
	private HashMap<ControlPoint, MouseJoint> joints = new HashMap<ControlPoint, MouseJoint>();
	
	public PhysicsComponent(float x, float y, World world) {
		this.world = world;
		
		// Create physics body
		BodyDef bodyDef = new BodyDef();
		bodyDef.position = new Vec2(x, y);
		bodyDef.linearDamping = 0.9f;
		bodyDef.angularDamping = 0.99f;
		this.body = getWorld().createBody(bodyDef);
	}

	public World getWorld() {
		return world;
	}

	public Body getBody() {
		return body;
	}
//
//	public MouseJoint getMouseJoint() {
//		return mouseJoint;
//	}

	public void render(PApplet p) {
		for (MouseJoint j: joints.values()) {
			p.stroke(Color.RED.getRGB());
			Vec2 localAnchor = j.m_localAnchor;
			Vec2 target = getBody().getLocalPoint(j.m_target);
			p.line(localAnchor.x, localAnchor.y, target.x, target.y);
		}
	}

	public boolean canAcceptMoreControlPoints() {
		return true;
	}

	public void controlPointCreated(ControlPoint cp) {
		PVector local = transformToLocalSpace(new PVector(cp.getX(), cp.getY()));
		Vec2 pos = getBody().getWorldPoint(new Vec2(local.x, local.y));
		MouseJointDef mouseJointDef = new MouseJointDef();
        mouseJointDef.body1 = getWorld().getGroundBody();
        mouseJointDef.body2 = getBody();
        mouseJointDef.target.set(pos);
        mouseJointDef.maxForce = 100000.0f * getBody().m_mass;
        joints.put(cp, (MouseJoint) getWorld().createJoint(mouseJointDef));
//        this.mouseJoint = (MouseJoint) getWorld().createJoint(mouseJointDef);
        getBody().wakeUp();
	}

	public void controlPointDied(ControlPoint cp) {
		MouseJoint j = joints.get(cp);
		if (j != null) {
			getWorld().destroyJoint(j);
			joints.remove(cp);
//			this.mouseJoint = null;
		}
	}

	public void controlPointUpdated(ControlPoint cp) {
		MouseJoint j = joints.get(cp);
		if (j != null) {
			PVector local = transformToLocalSpace(new PVector(cp.getX(), cp.getY()));
			Vec2 pos = getBody().getWorldPoint(new Vec2(local.x, local.y));
			j.setTarget(pos);
		}
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
