package spaceduell;

import java.awt.image.BufferedImage;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Ship extends SpaceObject {

	enum RotState {
		none, left, right;
	}
	
	private RotState rotation = RotState.none;
	private boolean accelerate = false;
	
	public Ship(double xCoord, double yCoord, double xVel, double yVel,
			double mass, BufferedImage img) {
		Vector2D pos = new Vector2D(xCoord, yCoord);
		Vector2D vel = new Vector2D(xVel, yVel);
		Vector2D tempPos = new Vector2D(0,0);
		Vector2D imgCenter = new Vector2D(img.getWidth(null)/2,img.getHeight(null)/2);
		
		setPosition(pos);
		setVelocity(vel);
		setTempPosition(tempPos);
		setMass(mass);
		setImage(img);
		setAngle(0);
		
		setImageCenter(imgCenter);
		
		setTrace(pos);
		setType("ship");
	}

	public RotState getRotation() {
		return rotation;
	}

	public void setRotation(RotState rotation) {
		this.rotation = rotation;
	}

	public boolean isAccelerate() {
		return accelerate;
	}

	public void setAccelerate(boolean accelerate) {
		this.accelerate = accelerate;
	}

	
}
