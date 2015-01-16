package spaceduell;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Game extends Applet implements Runnable, KeyListener {

	private static double VERSIONNUMBER = 0.1;

	private double time, deltatime;

	private Vector2D vec = new Vector2D(1,2);
	
	private Graphics second;

	private Sun sun;
	private Ship ship1;
	private URL base;
	private Image image, imageSun, imageShip1;

	private static ArrayList<SpaceObject> spaceObjects = new ArrayList<SpaceObject>();

	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Space Duell Version " + VERSIONNUMBER);

		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Bildeinstellung
		imageSun = getImage(base, "data/sun1.png");
		imageShip1 = getImage(base, "data/ship1.png");
	}

	@Override
	public void start() {
		sun = new Sun(400, 240, 0, 0, 1000000000, imageSun);
		ship1 = new Ship(50, 50, 0, 0, 1000000000, imageShip1);

		spaceObjects.add(sun);
		spaceObjects.add(ship1);

		time = System.nanoTime();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
			while (true) {
			deltatime = (System.nanoTime() - time)/Math.pow(10, 9);
			//for (int i = 0; i < spaceObjects.size(); i++) {
			//	spaceObjects.get(i).calculateGravition(deltatime);
			//}
			double temp = Math.pow(Math.sqrt(Math.pow(sun.getCoordX()-ship1.getCoordX(),2)+Math.pow(sun.getCoordY()-ship1.getCoordY(),2)),3);
			ship1.setTmpcoordX(0.5*deltatime*deltatime*sun.getMass()
						* (sun.getCoordX()-ship1.getCoordX())
						/temp);
			ship1.setTmpcoordY(0.5*deltatime*deltatime*sun.getMass()
					* (sun.getCoordY()-ship1.getCoordY())
					/ temp);
			
			//for (int i = 0; i < spaceObjects.size(); i++) {
			//	spaceObjects.get(i).update();
			//}
			ship1.update();
			repaint();
			time = System.nanoTime();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// Todo Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(sun.getImage(), sun.getIntX(), sun.getIntY(), this);
		g.drawImage(ship1.getImage(), ship1.getIntX(), ship1.getIntY(), this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static ArrayList<SpaceObject> getSpaceObjects() {
		return spaceObjects;
	}

}
