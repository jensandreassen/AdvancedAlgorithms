package edgeDetection;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 * Performs manipulation on an image with edgedetection using 
 * sobelconvolution and an threshold-method
 * @author Jens Andreassen
 *
 */
public class EdgeDetection {
	/**
	 * EdgeDetectionMethod, uses different methods and performs edgedetection-manipulation
	 * @param input input Image
	 * @param limit limit for treshold
	 * @return outputimage
	 */
	private static BufferedImage edgeDetection(BufferedImage input, int limit) {
		BufferedImage output = new BufferedImage(input.getWidth(),input.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
		output = convoluteSobel(input, output, limit);
		return output;
		
	}
	/**
	 * Picks out the sorunding pixel and performs a sobel convolution using the convolute-metod.
	 * 
	 * @param input image 
	 * @param output image
	 * @param limit limit for treshold
	 * @return output image
	 */
	private static BufferedImage convoluteSobel(BufferedImage input, BufferedImage output, int limit) {
		int[][] matrix = new int[3][3];
		for(int i=1;i<input.getWidth()-1;i++){
            for(int j=1;j<input.getHeight()-1;j++){
                matrix[0][0]=new Color(input.getRGB(i-1,j-1)).getRed();
                matrix[0][1]=new Color(input.getRGB(i-1,j)).getRed();
                matrix[0][2]=new Color(input.getRGB(i-1,j+1)).getRed();
                matrix[1][0]=new Color(input.getRGB(i,j-1)).getRed();
                matrix[1][2]=new Color(input.getRGB(i,j+1)).getRed();
                matrix[2][0]=new Color(input.getRGB(i+1,j-1)).getRed();
                matrix[2][1]=new Color(input.getRGB(i+1,j)).getRed();
                matrix[2][2]=new Color(input.getRGB(i+1,j+1)).getRed();

                int edge=(int) convolute(matrix);
                output.setRGB(i, j, treshold(edge, limit));
            }
        }
		return output;
	}
	/**
	 * Performs the convolution
	 * @param matrix containing pixelvalues
	 * @return new value
	 */
	private static double convolute(int[][] matrix){
	    int gy=(matrix[0][0]*-1)+(matrix[0][1]*-2)+(matrix[0][2]*-1)+(matrix[2][0])+(matrix[2][1]*2)+(matrix[2][2]*1);
	    int gx=(matrix[0][0])+(matrix[0][2]*-1)+(matrix[1][0]*2)+(matrix[1][2]*-2)+(matrix[2][0])+(matrix[2][2]*-1);
	    return Math.sqrt(Math.pow(gy,2)+Math.pow(gx,2));
	}
	/**
	 * Calculates the new RGB value
	 * @param edge old value
	 * @param limit for treshold
	 * @return new value
	 */
	private static int treshold(int edge, int limit) {
		if(edge<=limit) {
			edge = Color.WHITE.getRGB();
		} else {
			edge = Color.BLACK.getRGB();
		}
		return edge;
	}

	public static void main(String[] args) {
		try {
			int limit = 40;
			String file = "files\\yellow_flower.jpg";
			BufferedImage img = ImageIO.read(new File(file));
			BufferedImage newImg = edgeDetection(img, limit);
			JOptionPane.showMessageDialog(null, new ImageIcon(newImg.getScaledInstance(newImg.getWidth()/2, newImg.getHeight()/2, Image.SCALE_SMOOTH)));
			System.out.println("Processing comlpete");
		} catch (Exception e) {
			System.out.println("Failed processing!");
		}
	}
}
