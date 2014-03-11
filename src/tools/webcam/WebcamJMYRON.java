package tools.webcam;

import java.awt.image.BufferedImage;

import magicbook.objects.Medium;
import tools.database.DatabaseTool;
import tools.qrcode.QRCodeReader;
import tools.qrcode.QRCodeReaderZXING;
import JMyron.JMyron;

/**
 * JMyron is an external library. It should work under windows.
 * But is not used anymore, but maybe somebody can learn something about JMyron
 * by looking at this code :)
 * 
 * @author jonas
 * 
 */
public class WebcamJMYRON implements WebcamTool {

	private JMyron _myron = null;
	private QRCodeReader _qrReader;
	private final int WIDTH = 320;
	private final int HEIGHT = 240;

	public WebcamJMYRON() {
		setUp();
		_qrReader = new QRCodeReaderZXING();
	}

	private void setUp() {
		_myron = new JMyron();// make a new instance of the object
		try {
			openConnection();
		} catch (CameraException e) {

			e.printStackTrace();
		}
	}

	private BufferedImage getFrame() {
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		_myron.update();// update the camera view
		int[] img = _myron.image(); // get the normal image of the camera
		bi.setRGB(0, 0, WIDTH, HEIGHT, img, 0, WIDTH);
		return bi;
	}

	@Override
	public Medium getMedium() throws CameraException {
		String[] codeTexts = _qrReader.getQRCodeInformation(getFrame());
		/*TODO: dont just look at the first qr-code, 
		 * 	handle more qr-codes at the same time, if there are more?!*/
		/* TODO: testing*/
		String[] parts = codeTexts[0].split(" ");
		return DatabaseTool.loadMediumFromFile(parts[0], parts[1]);

	}

	@Override
	public void setCamera(int index) throws CameraException {
		// TODO Auto-generated method stub

	}

	@Override
	public void openConnection() throws CameraException {
		if (_myron != null) {
			_myron.start(WIDTH, HEIGHT);// start a capture at 320x240
			_myron.findGlobs(0);
		}
	}

	@Override
	public void closeConnection() throws CameraException {
		// TODO Auto-generated method stub
		if(_myron != null){
			_myron.stop();
		}
	}

}
