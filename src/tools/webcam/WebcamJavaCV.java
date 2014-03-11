package tools.webcam;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import magicbook.MagicBook;
import magicbook.objects.Medium;
import tools.database.DataBaseToolGen;
import tools.qrcode.QRCodeReader;
import tools.qrcode.QRCodeReaderZXING;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.VideoInputFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 * Implementation for the WebcamTool which uses the JavaCV library and therefore
 * depends on an installed opencv runtime on this system
 * 
 * @author jonas
 * 
 */
public class WebcamJavaCV implements WebcamTool {

	private int _indexCamera = 0;
	private OpenCVFrameGrabber _grabber;
	private VideoInputFrameGrabber _grabberWindows;
	private String _os;

	public WebcamJavaCV(boolean useSecondaryCamera) throws CameraException {
		_os = System.getProperty("os.name").toLowerCase();
		
		/* TODO: check if opencv runtime is working/installed*/
		if(useSecondaryCamera){
			_indexCamera=1;
		}
		try {
			openConnection();
		} catch (Exception e) {
			throw new CameraException(e.getMessage());
		}
	}
	

	@Override
	public Medium getMedium() throws CameraException {

		QRCodeReader r = new QRCodeReaderZXING();
		//for multiple qr-codes
		// String[] s = r.getQRCodeInformation(getBufferedImage());
		// String qrText = s[0];
		
		//just one qr-code
		String qrText = r.getQRCodeInformationSingle(getBufferedImage());

		// if (s != null && qrText != null) {
		if (qrText != null) {

			System.out.println(qrText);
			DataBaseToolGen database = MagicBook.getDatabase();
			if (database != null) {
				Medium m = database.loadMediumbyQRCode(qrText);
				if (m == null) {
					System.out.println("No connected medium in database!");
				}
				return m;
			}

		}
		return null;
	}

	/**
	 * Gets an BufferedImage from the webcam
	 * 
	 * @return
	 * @throws CameraException
	 */
	public BufferedImage getBufferedImage() throws CameraException {
		// 0-default camera, 1 - next camera...so on

		try {
			IplImage img;
			if(isRunningOnWindows()){
				img = _grabberWindows.grab();
			}else{
				img = _grabber.grab();
			}
				
			
			if (img != null) {
				return img.getBufferedImage();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new CameraException(
					"Error when grabbing a frame from the webcam : "
							+ e.getMessage());
		}
		return null;

	}

	/**
	 * Opens the connection to the webcam with _indexCamera. Starts seeing
	 * through the eyes of the webcam :)
	 * 
	 * @throws Exception
	 */
	public void openConnection() throws CameraException {

		if(isRunningOnWindows()){//Operating system = windows
			_grabberWindows = new VideoInputFrameGrabber(_indexCamera);
			
			try {
				_grabberWindows.tryLoad();
			} catch (Exception e) {
				_grabberWindows = new VideoInputFrameGrabber(0);// standard camera
				throw new CameraException(e.getMessage());
			}
			try {
				_grabberWindows.start();
			} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
				throw new CameraException(e.getMessage());
			}
		}else{//Unix system
			_grabber = new OpenCVFrameGrabber(_indexCamera);
			
			try {
				_grabber.tryLoad();
			} catch (Exception e) {
				_grabber = new OpenCVFrameGrabber(0);// standard camera
				throw new CameraException(e.getMessage());
			}
			try {
				_grabber.start();
			} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
				throw new CameraException(e.getMessage());
			}
		}
		
	}
	
	/**Is the operating system windows?
	 * @return
	 */
	private boolean isRunningOnWindows(){
		if (_os.startsWith("windows")) {
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Closes the connection to the webcam
	 * 
	 * @throws Exception
	 */
	public void closeConnection() throws CameraException {
		try {
			if(isRunningOnWindows()){
				_grabberWindows.stop();
			}else{
				_grabber.stop();
			}
			
		} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
			throw new CameraException(e.getMessage());
		}
	}

	@Override
	public void setCamera(int index) throws CameraException {
		// TODO be careful with threads here
		/*try {
			closeConnection();
			_indexCamera = index;
			openConnection();
		} catch (Exception e) {
			throw new CameraException(e.getMessage());
		}*/

	}

}
