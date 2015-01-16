package spaceduell;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Game extends Applet implements Runnable, KeyListener {

	private static double VERSIONNUMBER = 0.1;

	private double time, deltatime;

	private Graphics second;

	private Sun sun;
	private Ship ship1, ship2, ship3;
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
			base = getCodeBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Bildeinstellung
		System.out.println(getCodeBase()+"data/sun1.png");
		try {
			imageSun = ImageIO.read(new URL(base, "data/sun1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			imageShip1 = ImageIO.read(new URL(base, "data/ship1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		sun = new Sun(400, 240, 0, 0, 100, imageSun);
		//sun2 = new Sun(700, 240, 0, 0, 1, imageSun);
		ship1 = new Ship(400, 0, 500, 0, 1, imageShip1);
		ship2 = new Ship(400, 480, -500, 0, 1, imageShip1);
		ship3 = new Ship(100, 240, 0, -300, 1, imageShip1);

		spaceObjects.add(sun);
		//spaceObjects.add(sun2);
		spaceObjects.add(ship1);
		spaceObjects.add(ship2);
		spaceObjects.add(ship3);

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
			drawTrace(g, spaceObjects.get(i));
			g.drawImage(spaceObjects.get(i).getImage(), spaceObjects.get(i)
					.getIntX()-spaceObjects.get(i).getIntCenterX(), spaceObjects.get(i).getIntY()-spaceObjects.get(i).getIntCenterY(), this);
		}
	}

	public void drawTrace(Graphics g, SpaceObject spaceObj) {
		int xvals[] = new int[spaceObj.getTrace().size()];
		int yvals[] = new int[xvals.length];

		for (int i = 0; i < spaceObj.getTrace().size(); i++) {
			xvals[i] = (int) Math.round(spaceObj.getTrace().get(i).getX());
			yvals[i] = (int) Math.round(spaceObj.getTrace().get(i).getY());
		}
		g.setColor(Color.BLUE);
		g.drawPolyline(xvals, yvals, xvals.length);
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
