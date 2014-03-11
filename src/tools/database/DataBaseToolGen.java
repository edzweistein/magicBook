package tools.database;

import java.math.BigInteger;
import java.util.List;

import magicbook.MagicBook;
import magicbook.material.Options;
import magicbook.objects.Medium;
import tools.vlc.VLCController;

/**
 * public interface for database interaction
 * 
 * @author Niels
 * 
 */
public abstract class DataBaseToolGen {

	/**
	 * Saves all information and media relating a medium.
	 * 
	 */
	public abstract void save(Medium medium) throws Exception;

	/**
	 * Deletes all information and media refering a MagicCard.
	 * 
	 */
	public abstract void delete();

	/**
	 * Indicates whether the String matches with schemata for QR-Codes.
	 * 
	 * 
	 * @param qrCode
	 * @return true, if parameter fits schema
	 */
	public static boolean isCorrectQRCode(String qrCode) {
		/*
		 * TODO: implement check for correct qrcode content
		 * [Medium.TYPE<Leerzeichen>Hash-Code] structure
		 */
		return true;
	}

	/**
	 * Executes Commands sent from the android app
	 * 
	 * @param qrCode
	 */
	public static void executeCommand(String qrCode) {
		if (qrCode != null) {

			String[] pieces = qrCode.split(" ");

			if (pieces[0].equals("c")) {// Control Command
				VLCController vlc = MagicBook.getVLCController();
				if (vlc != null) {
					if (pieces[1].startsWith("0")) {// Prev- Button
						System.out.println("Command: Previous Track");
						vlc.previous();

					} else if (pieces[1].startsWith("1")) {// Play/Pause-Button
						System.out.println("Command: Play/Pause");
						vlc.playPause();
					} else if (pieces[1].startsWith("2")) {// Next-Button
						System.out.println("Command: Next Track");
						vlc.next();
					}
				}
			}

		}

	}

	/**
	 * Loads a media object for the given qr code
	 * 
	 * @param qrCode
	 * @return media object relating to the parameter
	 */
	public Medium loadMediumbyQRCode(String qrCode) {
		if (isCorrectQRCode(qrCode)) {
			if (isCommand(qrCode)) { // && !qrCode.equals(_lastCommand)) {
				System.out.println("Command will be executed: " + qrCode);
				// _lastCommand = qrCode;
				executeCommand(qrCode);
				return null;
			} else if (!isCommand(qrCode)) {
				// System.out.println("Medium To be loaded: " + qrCode);
				String[] s = preprocessString(qrCode);
				// int id = Integer.parseInt(s[1].substring(0, 10));
				if (s.length == 2) {
					BigInteger id = new BigInteger(s[1]);
					String type = s[0].substring(0, s[0].length())
							.toLowerCase();
					return loadMediumForType(type, id.toString());
				}
				return null;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Loads a media object for the id from the specific table in the database.
	 * Depending of 'type' parameter.
	 * 
	 * @param qrCode
	 * @return media object relating to the id
	 */
	public abstract Medium loadMediumForType(String type, String ID);

	/**
	 * Preprocesses the String from the qrCode.
	 * 
	 * @param qrCode
	 *            the String from the QR-Code
	 * @return Returns an String-Array where the first element is the type of
	 *         medium and the second is the id of the medium.
	 */
	public static String[] preprocessString(String qrCode) {
		qrCode = qrCode.replaceAll(":", "");
		qrCode = qrCode.replaceAll("_ID", "");
		String[] parts = qrCode.split("_");
		if (parts.length > 1)
			return parts[0].split(" ");
		else
			return qrCode.split(" ");
	}

	/**
	 * Decides whether the sent data (string) from the android app is a control
	 * command and not a medium
	 * 
	 * @param qrCode
	 * @return
	 */
	public static boolean isCommand(String qrCode) {
		if (qrCode != null) {
			if (qrCode.startsWith("volume"))
				return true;
			if (qrCode.startsWith("c"))
				return true;
			if (qrCode.startsWith("prev"))
				return true;

		}
		return false;
	}

	/**
	 * Converts the type attribut as string to an integer value.
	 * 
	 * @returns -1, if the type is not supported
	 * 
	 */
	public static int getType(String type) {
		if (type.equals("music")) {
			return 0;
		} else if (type.equals("video")) {
			return 1;
		} else if (type.equals("programm")) {
			return 2;
		}
		else if(type.equals("url")){
			return 3;
		}
		return -1;
	}

	/**
	 * Loads all media which is stored in the database
	 * 
	 * @return lists containing media from the database
	 */
	public abstract List<Medium> loadAllMedia();

	
	/**
	 * Loads all media which matches in differend attributes with the given parameter.
	 * 
	 * @param term for searching media
	 * @return List containing media which match the term
	 */
	public abstract List<Medium> loadMediaByTerm(String term);
	
	public Options loadOptions(){
		return Options.loadFromFile();
	}
}