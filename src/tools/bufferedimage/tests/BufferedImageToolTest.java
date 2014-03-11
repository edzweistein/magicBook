package tools.bufferedimage.tests;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;

import magicbook.MagicBook;

import org.junit.Before;
import org.junit.Test;

import tools.bufferedimage.BufferedImageTool;

public class BufferedImageToolTest {

	String _pfad;
	BufferedImage _img1, _img2;

	@Before
	public void setUp() {
		_pfad = MagicBook.getMagicDirectory() + "/images/";
		_img1 = BufferedImageTool.loadImage(_pfad + "edit_add.png");
		_img2 = BufferedImageTool.loadImage(new File(_pfad + "edit_add.png"));
	}

	@Test
	public void test() {

		assertTrue(_img1 != null);
		assertTrue(_img2 != null);

		// img1 == img2?
		assertTrue(_img1.getWidth() == _img2.getWidth());
		assertTrue(_img1.getHeight() == _img2.getHeight());
		assertTrue(_img1.getRGB(0, 0) == _img2.getRGB(0, 0));

		BufferedImage img2_scale = BufferedImageTool.scaleImage(_img2, 10, 10);
		assertTrue(img2_scale != null);
		String img2_scale_path = _pfad + "tmp_img2_scale";
		System.out.println("path: " + img2_scale_path + ".jpg");
		if (img2_scale != null) {
			BufferedImageTool.saveImage(img2_scale, img2_scale_path);
			File datei = new File(img2_scale_path+".jpg");
			assertTrue(datei.exists());
			BufferedImage img2_scale_loaded = BufferedImageTool
					.loadImage(img2_scale_path + ".jpg");
			assertTrue(img2_scale_loaded != null);

			// img2_scale == img2_scale_loaded?
			assertTrue(img2_scale.getWidth() == img2_scale_loaded.getWidth());
			assertTrue(img2_scale.getHeight() == img2_scale_loaded.getHeight());
		}

	}

}
