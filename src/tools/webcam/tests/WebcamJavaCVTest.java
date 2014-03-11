package tools.webcam.tests;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Test;

import tools.qrcode.QRCodeReader;
import tools.qrcode.QRCodeReaderZXING;
import tools.webcam.CameraException;
import tools.webcam.WebcamJavaCV;

public class WebcamJavaCVTest {

	@Test
	public void test() {

		QRCodeReader r = new QRCodeReaderZXING();
		String[] s;

		try {
			WebcamJavaCV webcam = new WebcamJavaCV(false);//use first,standard camera
			assertTrue(webcam != null);
			BufferedImage img = webcam.getBufferedImage();
			assertTrue(img != null);
			s = r.getQRCodeInformation(img);

			if (s != null) {
				for (String string : s)
					System.out.println(string);
			}
		} catch (CameraException e) {
			System.out.println(e.getText());
			assertTrue(false);
		}
	}

}
