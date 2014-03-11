package tools.qrcode;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import magicbook.objects.Medium;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeCreaterZXING extends QRCodeCreater{

	@Override
	public BufferedImage getQRCode(Medium m, int height, int width) {
		Charset charset = Charset.forName("ISO-8859-1");
		CharsetEncoder encoder = charset.newEncoder();
		byte[] b = null;
		try {
			// Convert a string to ISO-8859-1 bytes in a ByteBuffer
			ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(getDataForQRCode(m)));
			b = bbuf.array();
		} catch (CharacterCodingException e) {
			System.out.println(e.getMessage());
		}

		String data = null;
		try {
			data = new String(b, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

		// get a byte matrix for the data
		BitMatrix matrix = null;
		QRCodeWriter writer = new QRCodeWriter();
		try {
			matrix = writer.encode(data,
					com.google.zxing.BarcodeFormat.QR_CODE, width, height);
		} catch (com.google.zxing.WriterException e) {
			System.out.println(e.getMessage());
		}

		return MatrixToImageWriter.toBufferedImage(matrix);
	}

}
