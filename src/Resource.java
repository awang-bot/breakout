/*
 * Anne Liu and Atisa Wang
 * January 16, 2023
 * Resource
 * This class will manage the resourcse (i.e. images, fonts) used throughout the program.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resource { // this class is primarily for efficiency

	public Resource() {
	}

	// images
	public static BufferedImage getResourceImage(String name) {
		String path = "resources/" + name; // get the path of the image
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img; // return the image
	}

	public static Font getResourceFont(String name, float size) {
		String path = "resources/" + name; // get the path of the font
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return font; // return the font
	}

}
