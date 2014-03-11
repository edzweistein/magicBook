package tools.webcam;

import magicbook.objects.Medium;

/**Builds an connection to the webcam when initialized.
 * Returns a medium if one was recognized by calling the getMedium method.
 * @author jonas
 *
 */
public interface WebcamTool {

	/**Gets an picture from the webcam and searches for qr-codes in it.
	 * If one is recognized the information is read and it will look for the linked medium in the database.
	 * @return Medium whose qr code was found in the frame caught by the webcam
	 */
public Medium getMedium() throws CameraException;

/**Selects the camera 
 * @param index the camera index,0 is the internal camera,1 is e.g. an external USB-camera if there is a internal camera,
 * otherwise it might also be 0 for the external camera
 * @throws CameraException
 */
public void setCamera(int index) throws CameraException;

/**Opens the connection to the webcam
 * @throws CameraException
 */
public void openConnection() throws CameraException;


/**Closes the connection to the webcam
 * @throws CameraException
 */
public void closeConnection() throws CameraException;
	
	
}
