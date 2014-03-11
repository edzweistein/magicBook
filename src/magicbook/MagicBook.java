package magicbook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import magicbook.material.Observable;
import magicbook.material.Observer;
import magicbook.material.Options;
import magicbook.objects.Medium;
import tools.database.DataBaseSQLTool;
import tools.database.DataBaseToolGen;
import tools.udp.UDPException;
import tools.udp.UDPTool;
import tools.udp.UDPToolJava;
import tools.vlc.VLCControlTelnet;
import tools.vlc.VLCController;
import tools.webcam.CameraException;
import tools.webcam.WebcamJavaCV;
import tools.webcam.WebcamTool;

/**
 * @author jonas alias edzweistein 			:) 
 * 
 * 		   !!! Peace, Love, Happiness and Unity to everyone out there!!! :)
 * 
 *         This is the main-class of the project "magicBook", which provides a
 *         program that runs in the background and checks for QR-Codes from an
 *         Androidphone or the Webcam and executes the linked action for the
 *         recognized medium e.g. plays the movie or shows its information,tags
 *         and user rating, or playbacks music,....:)
 */
public class MagicBook implements Observer {

	public static Options OPTIONS;
	private static Medium _seen, _lastseen;
	private static WebcamTool _webcam;
	private static UDPTool _udp;
	// private static MagicBookUI _ui;
	private static boolean _firstTimeComes = true;
	private static VLCController _vlc;
	private static DataBaseToolGen _database;

	private static MagicBookTool _magicBookTool;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MagicBook();
	}

	public MagicBook() {
		/* Loads the Options from the file */
		OPTIONS = Options.loadFromFile();
		if (OPTIONS == null) {// no config file found
			OPTIONS = new Options();
		}

		initInformationSources();

		_magicBookTool = new MagicBookTool();

		System.out.println("" + _database.loadAllMedia().size()
				+ " mediums in database");

		// Main Loop
		Timer loop = new Timer(1000 / OPTIONS.WEBCAM_FRAMERATE,
				new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						// System.out.println("Checking for medium...");
						Medium m = checkForMedium();
						magic(m);

					}

				});

		loop.start();

	}

	/**
	 * Returns the VLCController that communicates with VLC
	 * 
	 * @return
	 */
	public static VLCController getVLCController() {
		return _vlc;
	}

	/**
	 * Returns the DataBaseToolGen (a database connection)
	 * 
	 * @return
	 */
	public static DataBaseToolGen getDatabase() {
		return _database;
	}

	/**
	 * Sets the new Options
	 * 
	 * @param options
	 *            the new Options
	 */
	public static void updateOptions(Options options) {

		// Camera Options
		if (OPTIONS.USE_SECONDARY_CAMERA != options.USE_SECONDARY_CAMERA) {
			try {
				if (_webcam != null) {
					_webcam.closeConnection();
				}
				_webcam = new WebcamJavaCV(options.USE_SECONDARY_CAMERA);
			} catch (CameraException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// VLC Options
		if (!OPTIONS.VLC_PATH_WINDOWS.equals(options.VLC_PATH_WINDOWS)
				|| OPTIONS.VLC_PORT != options.VLC_PORT) {
			//_vlc.updateVLCConnection(options.VLC_PATH_WINDOWS, options.VLC_PORT);
			//restart magicBook
			_magicBookTool.closeWindow();
			restartApplication();
		}
		OPTIONS = options;
	}
	
	/**
	 * Restarts magicBook.
	 * e.g. after options have been changed
	 * 
	 */
	private static void restartApplication()
	{
	  //final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	  File currentJar;
	  try {
		currentJar = new File(MagicBook.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		/* is it a jar file? */
		  if(!currentJar.getName().endsWith(".jar"))
		    return;

		  /* Build command: java -jar application.jar */
		  final ArrayList<String> command = new ArrayList<String>();
		  //command.add(javaBin);
		  command.add("java");
		  command.add("-jar");
		  command.add(currentJar.getPath());

		  final ProcessBuilder builder = new ProcessBuilder(command);
		  builder.start();
		  System.exit(0);
	  } catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  
	}

	/**
	 * Does the magic :)
	 * 
	 */
	private static void magic(Medium m) {

		if (m != null && (isFirstTimeSeeingAMedium() || isAnotherMedium(m))) {
			/*
			 * if there has been a medium found right now, and it is the first
			 * time there has been a medium seen or it is another medium,not the
			 * last one again
			 */

			_seen = m;
			System.out.println("Medium found: ID:" + _seen.getID());
			_seen.doMagic();
			_lastseen = _seen;
		}

	}

	/**
	 * Initializes the webcam,the udp connection to the android phone, the
	 * database connection and the vlc telnet connection
	 * 
	 */
	private void initInformationSources() {
		try {
			// start vlc and create a telnet connection
			_vlc = new VLCControlTelnet();

			if (_vlc == null)
				JOptionPane.showMessageDialog(null,"Error while connecting to VLC via Telnet! Please change the Options");

			// start database connection
			_database = new DataBaseSQLTool();

			// start webcam connection
			try {
				_webcam = new WebcamJavaCV(OPTIONS.USE_SECONDARY_CAMERA);
			} catch (CameraException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Error while connecting to Webcam! Please change the Options");

			}
			_udp = new UDPToolJava();
			UDPToolJava _udpj = (UDPToolJava) _udp;
			_udpj.addObserver(this);
		}
		/*
		 * catch (CameraException e) { // webcam exception e.printStackTrace();
		 * }
		 */catch (SocketException e) {
			// something went wrong with the udp socket
			e.printStackTrace();
		}
	}

	/**
	 * Checks whether it is the first time seeing a medium or not
	 * 
	 * @return
	 */
	private static boolean isFirstTimeSeeingAMedium() {
		if (_firstTimeComes) {
			if (_lastseen == null) {// && _seen != null){
				_firstTimeComes = false;
				// System.out.println("firsttime:true");
				return true;
			}
		}
		// System.out.println("firsttime:false");
		return false;

	}

	/**
	 * Checks whether the software has seen a new, another medium in front of
	 * the webcam
	 * 
	 * @return
	 */
	private static boolean isAnotherMedium(Medium m) {
		// return _lastseen != null && !_lastseen.equals(_seen);
		if (_lastseen != null && !_lastseen.equals(m)) {
			// System.out.println("anothermedium:true");
			return true;
		} else {
			// System.out.println("anothermedium:false");
			return false;
		}
	}

	/**
	 * Checks the different sources of information for new recognized qr codes
	 * 
	 * @return Medium if one was recognized, otherwise null
	 */
	public static Medium checkForMedium() {

		try {
			Medium m = null;
			// Check webcam for QR-codes -> media (mediums)
			if (_webcam != null) {
				m = _webcam.getMedium();
				if (m != null) {
					return m;
				}
			}
			// Checking udp port for sent data is now done by the observer model
			// -> media,commands

			// m = _udp.getMedium();

			return m;
		} catch (CameraException ce) {
			/* TODO: fehlerbehandlung */
			// ce.printStackTrace();
		}
		return null;
	}

	/**
	 * Return the top directory magicbook is running in! This is where all the
	 * magic comes from! :)
	 * 
	 * @return
	 */
	public static String getMagicDirectory() {
		return System.getProperty("user.dir");
	}

	@Override
	public void reactOnChanges(Observable observable) {
		if (observable instanceof UDPTool) {
			// UDPTool
			try {

				Medium m = ((UDPTool) observable).getMedium();
				magic(m);

			} catch (UDPException e) {
				e.printStackTrace();
			}
		}
	}

}
