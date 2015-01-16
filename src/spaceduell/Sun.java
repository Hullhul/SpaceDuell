package spaceduell;

import java.awt.Image;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Sun extends SpaceObject {

	public Sun(double xCoord, double yCoord, double xVel, double yVel, double mass, Image img){
		Vector2D pos = new Vector2D(xCoord,yCoord);
		Vector2D vel = new Vector2D(xVel,yVel);
		Vector2D tempPos = new Vector2D(0,0);
		System.out.println(img.getWidth(null));
		Vector2D imgCenter = new Vector2D(img.getWidth(null)/2,img.getWidth(null)/2);
		
		setPosition(pos);
		setVelocity(vel);
		setTempPosition(tempPos);
		setMass(mass);
		setImage(img);
		
		setImageCenter(imgCenter);
		
		setTrace(pos);
		setType("sun");
	}

}
