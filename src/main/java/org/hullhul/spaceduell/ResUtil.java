package org.hullhul.spaceduell;

import java.applet.Applet;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ResUtil {

	/**
	 * works NOT with Applet
	 * @param imagename
	 * @return
	 */
	public static BufferedImage readImage(String imagename) {
		//System.out.println(getCodeBase() + "data/sun1.png");
		URL url = ResUtil.class.getClass().getResource("/images/" + imagename); // works with Unit test but not with applet
		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * works with Applets
	 * @param applet
	 * @param imagename
	 * @return
	 */
	public static BufferedImage readImage(Applet applet, String imagename) {
		System.out.println(applet.getCodeBase());
		
		try {
			URL url = new URL(applet.getCodeBase(), "images/" + imagename);
			return ImageIO.read(url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
}
