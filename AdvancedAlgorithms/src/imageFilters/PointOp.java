package imageFilters;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 * Reads an image from file and performs pointoperations on it, changin contrast and gamma via a raster.
 * @author Jens Andreassen
 *
 */
public class PointOp {
/**
 * Creates and returns a new BufferedImage based on the parameter image and int c representing contrast value and
 * int b representing brightnes value. Time complexity for the method is: O(C*(NM)) where c is the number of colors in the image, 
 * n the pixel-widht and m the pixel-height.
 * @param img Image
 * @param c Contrast value
 * @param b	Brightness value
 * @return new Image
 */
	private static BufferedImage changeValue(BufferedImage img, int c, int b) {
		int width = img.getWidth();
		int height = img.getHeight();

		WritableRaster inraster = img.getRaster();

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Assume image is color
		int colors = 3;
		try {
			int dummy = inraster.getSample(0, 0, 2);
		} catch (Exception e) { // Try accessing blue color value!
			System.out.println("Picture is monochrome");
			image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);// If exception, it is monochrome!
			colors = 1;
		}
		WritableRaster outraster = image.getRaster();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				for (int co = 0; co < colors; co++) {
					int pixel = inraster.getSample(i, j, co);
					if ((pixel * c) + b > 255) {
						pixel = 255;
					} else if ((pixel * c) + b < 0) {
						pixel = 0;
					} else {
						pixel = (pixel * c) + b;
					}
					outraster.setSample(i, j, co, pixel);
				}
			}
		}
		return image;
	}

	public static void main(String[] args) {
		try {
			String file = "files\\yellow_flower.jpg";
			BufferedImage img = ImageIO.read(new File(file));
			BufferedImage newImg = changeValue(img, -2, 200);
			ImageIO.write(newImg, "PNG", new File("files\\testfile.png"));
			System.out.println("Processing comlpete");
		} catch (Exception e) {
			System.out.println("Failed processing!");
		}
	}
}
