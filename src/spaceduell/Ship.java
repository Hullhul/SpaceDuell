package spaceduell;

import java.awt.Image;

public class Ship extends SpaceObject {

	public Ship(double xCoord, double yCoord, double xVel, double yVel, double mass, Image img){
		setCoordX(xCoord);
		setCoordY(yCoord);
		setMass(mass);
		setVeloX(xVel);
		setVeloY(yVel);
		setImage(img);
	}

}
