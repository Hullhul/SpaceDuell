package spaceduell;

import java.awt.Image;
import java.util.ArrayList;

public class SpaceObject {
	private double coordX, coordY, veloX, veloY, tmpcoordX, tmpcoordY, mass;
	private Image image;
	private ArrayList<SpaceObject> spaceObjects = Game.getSpaceObjects();

	void update() {
		coordX += tmpcoordX;
		coordY += tmpcoordY;
	}

	void calculateMovement(double deltime) {
		for (int i = 0; i < spaceObjects.size(); i++) {
			if (!this.equals(spaceObjects.get(i))) {
				tmpcoordX = 0.5*spaceObjects.get(i).getMass()
						* (spaceObjects.get(i).getCoordX()-this.coordX)
						/ Math.pow(
								Math.abs(spaceObjects.get(i).getCoordX()-this.coordX), 3)*Math.pow(deltime,2);
				tmpcoordY = 0.5*spaceObjects.get(i).getMass()
						* (spaceObjects.get(i).getCoordY()-this.coordY)
						/ Math.pow(
								Math.abs(spaceObjects.get(i).getCoordY()-this.coordY), 3)*Math.pow(deltime,2);
			}
		}
	}
	
	void calculateGravition(){
		
	}

	public int getIntX() {
		return (int) Math.round(coordX);
	}

	public int getIntY() {
		return (int) Math.round(coordY);
	}

	public double getCoordX() {
		return coordX;
	}

	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getVeloX() {
		return veloX;
	}

	public void setVeloX(double veloX) {
		this.veloX = veloX;
	}

	public double getVeloY() {
		return veloY;
	}

	public void setVeloY(double veloY) {
		this.veloY = veloY;
	}

	public Image getImage() {
		return image;
	}

	public double getTmpcoordX() {
		return tmpcoordX;
	}

	public void setTmpcoordX(double tmpcoordX) {
		this.tmpcoordX = tmpcoordX;
	}

	public double getTmpcoordY() {
		return tmpcoordY;
	}

	public void setTmpcoordY(double tmpcoordY) {
		this.tmpcoordY = tmpcoordY;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	

}
