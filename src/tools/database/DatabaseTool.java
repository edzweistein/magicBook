package tools.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import magicbook.objects.Medium;

import com.thoughtworks.xstream.XStream;

/**
 * The database is made of this structure data//{Medium.TYPE}//{mediumID}.xml
 * 
 * @author jonas
 * 
 */
public class DatabaseTool extends DataBaseToolGen{

	
	//TODO Command Handling auslagern


	/**
	 * Saves an Medium to a file in the magic-folder structure :)
	 * 
	 * @param m
	 *            the Medium which should be saved
	 * 
	 */
	public static void saveToFile(Medium m) throws IOException {

		String fileLocation = System.getProperty("user.dir") + "//data//"
				+ m.getMediaType().toString().toLowerCase() + "//";

		fileLocation += +m.getID() + ".xml";
		//System.out.println(fileLocation);

		/* Write Medium to file */
		XStream xs = new XStream();

		File f = new File(fileLocation);
		f.createNewFile();
		FileOutputStream fs = new FileOutputStream(f);
		xs.toXML(m, fs);
		fs.close();

		

	}

	/**
	 * 
	 * @param file
	 *            The path to the file which the medium should be loaded from
	 * @return
	 */
	private static Medium loadMediumFromFile(String file) {
		try {
			InputStream inputStream = new FileInputStream(file);
			XStream xs = new XStream();
			Medium m = (Medium) xs.fromXML(inputStream);
			inputStream.close();
			return m;

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param qrID
	 *            the ID which is being chosen (by QR-Code)
	 * @return the Medium, if the medium does not exist null is going to get
	 *         returned
	 */
	public static Medium loadMediumFromFile(String type, String qrID) {
		
		String fileLocation = "data//" + type + "//" + qrID +".xml";
		//System.out.println("fileLocation:"+qrID.length()+" " +fileLocation);
		return loadMediumFromFile(fileLocation);
	}

	/**
	 * @param qrCode
	 *            the String which was read in one QR-Code
	 * @return the Medium, if the medium does not exist null is going to get
	 *         returned
	 */
	public static Medium loadMediumFromFileQR(String qrCode) {

		if (isCorrectQRCode(qrCode)) {
			if (isCommand(qrCode)) { // && !qrCode.equals(_lastCommand)) {
				System.out.println("Command will be executed: " + qrCode);
				// _lastCommand = qrCode;
				executeCommand(qrCode);
				return null;
			} else if (!isCommand(qrCode)) {
				// System.out.println("Medium To be loaded: " + qrCode);
				String[] s = preprocessString(qrCode);
				//int id = Integer.parseInt(s[1].substring(0, 10));
				BigInteger id = new BigInteger(s[1]);
				String type = s[0].substring(0, s[0].length()).toLowerCase();
				return loadMediumFromFile(type, id.toString());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	
	



	/**
	 * @param type
	 *            should be filled with Medium.getMediaTyp()
	 * @return
	 */
	@Override
	public ArrayList<Medium> loadAllMedia() {

		/* TODO: prüfen ob der Typ type existiert 25.04.2012 */
		/* TODO: prüfen ob type.tostring funktioniert 16.06.2012 */
		String folder = "data//" + "music" + "//";
		String[] files = getXmlFilesFromFolder(folder);
		ArrayList<Medium> list = new ArrayList<Medium>();

		if (files != null) {
			for (String s : files) {
				list.add(loadMediumFromFile(folder + s));
			}
		}

		/* TODO: null pointer aussortieren? 25.04.2012 */
		return list;
	}

	private static String[] getXmlFilesFromFolder(String folderPath) {
		File dir = new File(folderPath);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {

				return name.endsWith(".xml");
			}
		};
		return dir.list(filter);

	}

	@Override
	public void save(Medium medium) throws IOException {
		try {
			saveToFile(medium);
		} catch (IOException e) {
			throw e;
			//e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		// TODO implement this
		
	}

	@Override
	public Medium loadMediumForType(String type, String ID) {
		return loadMediumFromFile(type,ID);
	}

    @Override
    public List<Medium> loadMediaByTerm(String term)
  
    {
        // TODO implement this
        return null;
    }
}