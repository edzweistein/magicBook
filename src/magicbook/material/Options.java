package magicbook.material;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import magicbook.objects.Music;
import magicbook.objects.Pictures;
import magicbook.objects.Program;
import magicbook.objects.RealLifeObject;
import magicbook.objects.URL;
import magicbook.objects.Video;

import com.thoughtworks.xstream.XStream;

/**
 * Contains all sort of options. 
 * @author jonas
 * 
 */
public class Options {
	public static final String FILE = "data//options.xml";

	public int ACTION_MUSIC;
	public int ACTION_VIDEO;
	public int ACTION_URL;
	public int ACTION_PROGRAM;
	public int ACTION_PICTURES;
	public int ACTION_GAMES;
	public int ACTION_REALLIFEOBJECT;
	public int STARTUP_MODE;
	public boolean POPUP_WITHMAGIC;
	public final int WEBCAM_FRAMERATE = 15;
	public final int UDPCHECKRATE = 10;
	public boolean PORTABLE_MODE; 
	public String VLC_PATH_WINDOWS="C:\\Programme\\VLC\\";
	public final int VLC_STANDARD_TELNET_PORT=4212;
	public int VLC_PORT=VLC_STANDARD_TELNET_PORT;
	public boolean RANDOM_PLAYING;
	public boolean USE_SECONDARY_CAMERA;
	
	
	
	public static String LANGUAGE;

	/**
	 * Loads the Options from the FILE
	 * 
	 * @return
	 */

	public static Options loadFromFile() {
		try {
			InputStream inputStream = new FileInputStream(FILE);
			XStream xs = new XStream();
			return (Options) xs.fromXML(inputStream);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Saves this options to the FILE
	 */
	public boolean saveToFile() {
		XStream xs = new XStream();

		try {
			File f = new File(FILE);
			f.createNewFile();
			FileOutputStream fs = new FileOutputStream(f);
			xs.toXML(this, fs);
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
	}

	/**
	 * @param type
	 *            the type of Medium
	 * @return the ACTION option for the specific type , -1 if this medium is
	 *         not one of the included ones
	 */
	public int getActionForType(String type) {
		if (type.equals(Music.getType())) {
			return ACTION_MUSIC;
		} else if (type.equals(Video.getType())) {
			return ACTION_VIDEO;
		} else if (type.equals(Pictures.getType())) {
			return ACTION_PICTURES;
		} else if (type.equals(Program.getType())) {
			return ACTION_PROGRAM;
		} else if (type.equals(URL.getType())) {
			return ACTION_URL;
		} else if (type.equals(RealLifeObject.getType())) {
			return ACTION_REALLIFEOBJECT;
		} else {
			return -1;
		}
	}

}
