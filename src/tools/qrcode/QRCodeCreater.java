package tools.qrcode;
import java.awt.image.BufferedImage;

import magicbook.objects.Medium;


public abstract class QRCodeCreater {

	
	/**Creates an QR-Code with the data returned by {@link getDataForQRCode()} which is then returned as a BufferedImage.
	 * Its size can be set (in pixels x pixels) through the parameters.
	 * @param m The medium the qr code belongs to. This medium delivers the data which will be saved in the qr code.
	 * @param height height of the qr-code in pixels
	 * @param width width of the qr-code in pixels
	 * @return the qr code for this medium
	 */
	public abstract BufferedImage getQRCode(Medium m,int height,int width);
	
	/**Return the data which should be saved in the qr codes.
	 * @param m the medium for which the qr code is created
	 * @return the data as a string
	 */
	protected String getDataForQRCode(Medium m){
		return ""+m.getMediaType() + " "+ m.getID();
	}
}
