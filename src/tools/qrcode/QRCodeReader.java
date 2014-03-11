package tools.qrcode;

import java.awt.image.BufferedImage;

/**
 * Recognizes QR-codes in BufferedImages and returns the information.
 * 
 * @author jonas
 * 
 */
public interface QRCodeReader {

	/**
	 * Retrieves QR-Code information from image
	 * 
	 * @param img
	 *            the image to search for qr codes
	 * @return String array containing all the information of all qr codes in
	 *         the image
	 */
	public String[] getQRCodeInformation(BufferedImage img);

	/**
	 * Retrieves the text information of the image
	 * @param img the image to search for one qr code
	 * @return the text the qr code contained
	 */
	public String getQRCodeInformationSingle(BufferedImage img);
}
