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

public class Game extends Applet implements Runnable, KeyListener {

	private static double VERSIONNUMBER = 0.1;

	private double time, deltatime;

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
		sun = new Sun(400, 240, 0, 0, 100, imageSun);
		ship1 = new Ship(400, 0, 500, 0, 1, imageShip1);

		spaceObjects.add(sun);
		spaceObjects.add(ship1);

		time = System.nanoTime();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			deltatime = (System.nanoTime() - time) / Math.pow(10, 9);
			for (int i = 0; i < spaceObjects.size(); i++) {
				spaceObjects.get(i).calculateMovement(deltatime);
			}

			for (int i = 0; i < spaceObjects.size(); i++) {
				spaceObjects.get(i).update();
			}
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
		for (int i = 0; i < spaceObjects.size(); i++) {
			g.drawImage(spaceObjects.get(i).getImage(), spaceObjects.get(i)
					.getIntX(), spaceObjects.get(i).getIntY(), this);
		}
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
