package org.hullhul.spaceduell;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class SpaceObject {
	private double mass,angle;
	private Vector2D position, tempPosition, velocity, acceleration, imageCenter;
	private ArrayList<Vector2D> trace = new ArrayList<Vector2D>();

	private String type;

	private BufferedImage image;
	private ArrayList<SpaceObject> spaceObjects = Game.getSpaceObjects();
	

	void update() {
		position = position.add(tempPosition);
		trace.add(position);
	}

	void calculateMovement(double deltime) {
		for (int i = 0; i < spaceObjects.size(); i++) {
			if (checkTypes(i)) {
				calculateGravition(this, spaceObjects.get(i));
				velocity = velocity.add(acceleration.scalarMultiply(deltime));
				tempPosition = (velocity.scalarMultiply(deltime));
			}
		}
	}

	void calculateGravition(SpaceObject obj1, SpaceObject obj2) {
		Vector2D tmp1;
		double distanceCube;
		tmp1 = obj2.getPosition().subtract(obj1.getPosition());
		distanceCube = Math.pow(
				Vector2D.distance(obj2.getPosition(), obj1.getPosition()), 3);

		obj1.setAcceleration(tmp1.scalarMultiply(obj2.getMass() / distanceCube
				* 1000000));
	}

	private boolean checkTypes(int i) {
		boolean result = false;

		if (!this.equals(spaceObjects.get(i))) {
			if (type == "ship" && spaceObjects.get(i).type == "sun") {
				result = true;
			}
		}
		return result;
	}

	public int getIntX() {
		return (int) Math.round(position.getX());
	}

	public int getIntY() {
		return (int) Math.round(position.getY());
	}
	
	public int getIntCenterX() {
		return (int) Math.round(imageCenter.getX());
	}

	public int getIntCenterY() {
		return (int) Math.round(imageCenter.getY());
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public Vector2D getTempPosition() {
		return tempPosition;
	}

	public void setTempPosition(Vector2D tempPosition) {
		this.tempPosition = tempPosition;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public Vector2D getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Vector2D> getTrace() {
		return trace;
	}

	public void setTrace(Vector2D vec) {
		this.trace.add(vec);
	}

	public Vector2D getImageCenter() {
		return imageCenter;
	}

	public void setImageCenter(Vector2D imageCenter) {
		this.imageCenter = imageCenter;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

}
