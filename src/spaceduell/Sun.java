package spaceduell;

import java.awt.Image;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Sun extends SpaceObject {

	public Sun(double xCoord, double yCoord, double xVel, double yVel, double mass, Image img){
		Vector2D pos = new Vector2D(xCoord,yCoord);
		Vector2D vel = new Vector2D(xVel,yVel);
		
		setPosition(pos);
		setVelocity(vel);
		setMass(mass);
		setImage(img);
	}

}
