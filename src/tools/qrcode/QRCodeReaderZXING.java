    package tools.qrcode;

import java.awt.image.BufferedImage;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

public class QRCodeReaderZXING implements QRCodeReader {

	@Override
	public String[] getQRCodeInformation(BufferedImage img) {
		/* TODO: testen, ist das schön so? 10.05.2012 */
		// ZXING
		QRCodeMultiReader reader = new QRCodeMultiReader();
		LuminanceSource source = new BufferedImageLuminanceSource(img);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result[] result = null;
		try {

			result = reader.decodeMultiple(bitmap);
			if (result != null) {
				String[] codes = new String[result.length];
				// System.out.println("result.length "+result.length);
				for (int i = 0; i < result.length; i++) {
					codes[i] = result[i].getText();
					System.out.println(result[i].getText());
				}
				return codes;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public String getQRCodeInformationSingle(BufferedImage img) {

		LuminanceSource source = new BufferedImageLuminanceSource(img);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Reader reader = new MultiFormatReader();
		Result result;
		try {
			result = reader.decode(bitmap);
			return result.getText();
		} catch (NotFoundException e) {
			
		} catch (ChecksumException e) {
			e.printStackTrace();
		} catch (FormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
