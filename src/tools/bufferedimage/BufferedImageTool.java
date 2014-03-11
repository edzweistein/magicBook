package tools.bufferedimage;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageTool {

	/**
	 * Saves an image (img) to the path.
	 * 
	 * @param img
	 *            Image you want to save
	 * @param path
	 *            path where it shall be saved
	 */
	public static void saveImage(BufferedImage img, String path) {// in jpg
		try {
			if(img!=null){
				ImageIO.write(img, "jpg", new File(path + ".jpg"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Scales an given image (img) to the target size
	 * 
	 * @param img
	 * @param targetWidth
	 * @param targetHeight
	 * @return the scaled image
	 */
	public static BufferedImage scaleImage(BufferedImage img, int targetWidth,
			int targetHeight) {
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		int w, h;
		w = targetWidth;
		h = targetHeight;
		BufferedImage tmp = new BufferedImage(w, h, type);
		Graphics2D g2 = tmp.createGraphics();
		// g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		return tmp;

	}

	/**
	 * Loads an image from the given path
	 * 
	 * @param path
	 * @return the image which belongs to the path
	 */
	public static BufferedImage loadImage(String path) {
		return loadImage(new File(path));
	}

	/**
	 * Loads an image from the given file
	 * 
	 * @param file
	 * @return the image which belongs to the file
	 */
	public static BufferedImage loadImage(File file) {

		BufferedImage i = null;
		try {
			//System.out.println("" + file.getCanonicalPath());
			if (file.exists()) {
				i = ImageIO.read(file);
			}
		} catch (IOException evv) {
			evv.printStackTrace();
			return null;
		}
		return i;
	}

}
