package tools.vlc;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import magicbook.MagicBook;
import magicbook.objects.Medium;

import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TelnetNotificationHandler;

import tools.Utilities;

public class VLCControlTelnet extends TelnetClient implements Runnable,
		TelnetNotificationHandler, VLCController {

	/**
	 * Exposes control of VLC media player (videolan.org) from java. VLC must be
	 * set up to open a telnet control on localhost:4212. This is done by
	 * starting vlc with the following parameters: vlc --intf qt --extraintf
	 * Telnet
	 */
	public static String VLC_PATH;
	public static int VLC_PORT;
	public static final String VLC_PASSWORD="admin";
	public static String PAUSE = "pause", PLAY = "play", STOP = "stop",
			NEXT = "next", PREV = "prev", VOLUP = "volup 1",
			VOLDOWN = "voldown 1";
	public static final String CLIENT_MESSAGE = "ClientMessage";

	static final Logger log = Logger.getLogger("VLCControl");
	// private CharBuffer cbuf = CharBuffer.allocate(1024);
	private static VLCControlTelnet _staticInstance = null; // used to
															// communicate among
															// instances the
															// active client
	private PropertyChangeSupport _support = new PropertyChangeSupport(this); // listeners
																				// get

	public VLCControlTelnet() {
		// use path and port from option file
		if (MagicBook.OPTIONS != null) {
			VLC_PATH = MagicBook.OPTIONS.VLC_PATH_WINDOWS;
			VLC_PORT = MagicBook.OPTIONS.VLC_PORT;
		}else{
			VLC_PATH="";
			VLC_PORT=4212;
		}
		openVLC();
	}

	public VLCControlTelnet(String path, int port) {
		// use this path and port
		VLC_PATH = path;
		VLC_PORT = port;
		openVLC();
	}

	@Override
	public void disconnect() throws IOException {
		sendCommand("quit");
		super.disconnect();
	}

	/**
	 * Start VLC with the telnet interface opened.
	 * 
	 */
	public void openVLC() {
		try {
			System.out.println("Starting VLC...");
			// String befehl = "--intf qt --extraintf Telnet";//start vlc with
			// telnet port 4212 open
			String os = System.getProperty("os.name").toLowerCase();
			if (os.startsWith("windows")) {

				String[] cmdarray = { VLC_PATH + "vlc.exe", "--intf", "qt",
						"--extraintf", "Telnet" };
				Runtime.getRuntime().exec(cmdarray);
			} else {
				List<String> command = new ArrayList<String>();

				command.add("vlc");
				command.add("--intf");
				command.add("qt");
				command.add("--extraintf");
				command.add("Telnet");

				// ProcessBuilder pbtest = new ProcessBuilder(pfad);
				ProcessBuilder pb = new ProcessBuilder(command);

				pb.start();
			}

			/*
			 * InputStream errors = process.getErrorStream(); BufferedReader
			 * bufferedReader = new BufferedReader(new
			 * InputStreamReader(errors)); String line = null; while ((line =
			 * bufferedReader.readLine()) != null) { System.out.println(line); }
			 * bufferedReader.close(); errors.close();
			 */

		} catch (IOException io) {
			System.out.println("Error while starting VLC!");
			JOptionPane
					.showMessageDialog(
							null,
							"Error while starting VLC. If you use Windows please make sure that the path to the vlc.exe is right!");
			io.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Connect to the Telnet interface of VLC on localhost with the VLC_PORT
	 * 
	 * @throws IOException
	 */
	public void connect() throws IOException {

		_staticInstance = this; // used by reader to get input stream
		try {
			_staticInstance.connect("localhost", VLC_PORT);
			Thread thread = new Thread(this); // starts the
																// thread to get
																// the text sent
																// back from VLC
			thread.start();
			sendCommand(VLC_PASSWORD);// send password
			_staticInstance.registerNotifHandler(this); // notifications call
														// back to logger
			Runtime.getRuntime().addShutdownHook(new Thread() { // shutdown hook
																// here makes
																// sure to
																// disconnect
																// cleanly, as
																// long as we
																// are not
																// terminated

						@Override
						public void run() {
							try {
								if (isConnected()) {
									disconnect();
								}
							} catch (IOException ex) {
								// log.warning(ex.toString());
							}
						}
					});
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(null,
							"Error while connecting to VLC via Telnet! Please change the Options");
			log.warning("couldn't connect to VLC - you may need to start VLC with command line \"vlc --intf qt --extraintf Telnet \"");
			throw new IOException(e);
		}
	}

	/**
	 * Sends a string command. Commands do not need to be terminated with a
	 * newline.
	 * <p>
	 * 
	 * <pre>
	 * 	    +----[ Remote control commands ]
	 * 	    | add XYZ  . . . . . . . . . . . . . . . . . . . . add XYZ to playlist
	 * 	    | enqueue XYZ  . . . . . . . . . . . . . . . . . queue XYZ to playlist
	 * 	    | playlist . . . . . . . . . . . . . .show items currently in playlist
	 * 	    | search [string]  . .  search for items in playlist (or reset search)
	 * 	    | sort key . . . . . . . . . . . . . . . . . . . . . sort the playlist
	 * 	    | sd [sd]  . . . . . . . . . . . . . show services discovery or toggle
	 * 	    | play . . . . . . . . . . . . . . . . . . . . . . . . . . play stream
	 * 	    | stop . . . . . . . . . . . . . . . . . . . . . . . . . . stop stream
	 * 	    | next . . . . . . . . . . . . . . . . . . . . . .  next playlist item
	 * 	    | prev . . . . . . . . . . . . . . . . . . . .  previous playlist item
	 * 	    | goto . . . . . . . . . . . . . . . . . . . . . .  goto item at index
	 * 	    | repeat [on|off]  . . . . . . . . . . . . . .  toggle playlist repeat
	 * 	    | loop [on|off]  . . . . . . . . . . . . . . . .  toggle playlist loop
	 * 	    | random [on|off]  . . . . . . . . . . . . . .  toggle playlist random
	 * 	    | clear  . . . . . . . . . . . . . . . . . . . . . .clear the playlist
	 * 	    | status . . . . . . . . . . . . . . . . . . . current playlist status
	 * 	    | title [X]  . . . . . . . . . . . . . . set/get title in current item
	 * 	    | title_n  . . . . . . . . . . . . . . . .  next title in current item
	 * 	    | title_p  . . . . . . . . . . . . . .  previous title in current item
	 * 	    | chapter [X]  . . . . . . . . . . . . set/get chapter in current item
	 * 	    | chapter_n  . . . . . . . . . . . . . .  next chapter in current item
	 * 	    | chapter_p  . . . . . . . . . . . .  previous chapter in current item
	 * 	    |
	 * 	    | seek X . . . . . . . . . . . seek in seconds, for instance `seek 12'
	 * 	    | pause  . . . . . . . . . . . . . . . . . . . . . . . .  toggle pause
	 * 	    | fastforward  . . . . . . . . . . . . . . . . . . set to maximum rate
	 * 	    | rewind . . . . . . . . . . . . . . . . . . . . . set to minimum rate
	 * 	    | faster . . . . . . . . . . . . . . . . . .  faster playing of stream
	 * 	    | slower . . . . . . . . . . . . . . . . . .  slower playing of stream
	 * 	    | normal . . . . . . . . . . . . . . . . . .  normal playing of stream
	 * 	    | rate [playback rate] . . . . . . . . . .  set playback rate to value
	 * 	    | frame  . . . . . . . . . . . . . . . . . . . . . play frame by frame
	 * 	    | fullscreen, f, F [on|off]  . . . . . . . . . . . . toggle fullscreen
	 * 	    | info . . . . . . . . . . . . . .information about the current stream
	 * 	    | stats  . . . . . . . . . . . . . . . .  show statistical information
	 * 	    | get_time . . . . . . . . . .seconds elapsed since stream's beginning
	 * 	    | is_playing . . . . . . . . . . . .  1 if a stream plays, 0 otherwise
	 * 	    | get_title  . . . . . . . . . . . . . the title of the current stream
	 * 	    | get_length . . . . . . . . . . . .  the length of the current stream
	 * 	    |
	 * 	    | volume [X] . . . . . . . . . . . . . . . . . .  set/get audio volume
	 * 	    | volup [X]  . . . . . . . . . . . . . . . .raise audio volume X steps
	 * 	    | voldown [X]  . . . . . . . . . . . . . .  lower audio volume X steps
	 * 	    | adev [X] . . . . . . . . . . . . . . . . . . . .set/get audio device
	 * 	    | achan [X]  . . . . . . . . . . . . . . . . . .set/get audio channels
	 * 	    | atrack [X] . . . . . . . . . . . . . . . . . . . set/get audio track
	 * 	    | vtrack [X] . . . . . . . . . . . . . . . . . . . set/get video track
	 * 	    | vratio [X] . . . . . . . . . . . . . . . .set/get video aspect ratio
	 * 	    | vcrop, crop [X]  . . . . . . . . . . . . . . . .  set/get video crop
	 * 	    | vzoom, zoom [X]  . . . . . . . . . . . . . . . .  set/get video zoom
	 * 	    | snapshot . . . . . . . . . . . . . . . . . . . . take video snapshot
	 * 	    | strack [X] . . . . . . . . . . . . . . . . . set/get subtitles track
	 * 	    | hotkey, key [hotkey name]  . . . . . . . . . . simulate hotkey press
	 * 	    | menu [on|off|up|down|left|right|select]  . . . . . . . . . .use menu
	 * 	    |
	 * 	    | set [var [value]]  . . . . . . . . . . . . . . . . . set/get env var
	 * 	    | save_env . . . . . . . . . . . .  save env vars (for future clients)
	 * 	    | alias [cmd]  . . . . . . . . . . . . . . . . set/get command aliases
	 * 	    | description  . . . . . . . . . . . . . . . . . .describe this module
	 * 	    | license  . . . . . . . . . . . . . . . . print VLC's license message
	 * 	    | help, ? [pattern]  . . . . . . . . . . . . . . . . . .a help message
	 * 	    | longhelp [pattern] . . . . . . . . . . . . . . a longer help message
	 * 	    | logout . . . . . . . . . . . . . .  exit (if in a socket connection)
	 * 	    | quit . . . . . . . .  quit VLC (or logout if in a socket connection)
	 * 	    | shutdown . . . . . . . . . . . . . . . . . . . . . . . .shutdown VLC
	 * 	    +----[ end of help ]
	 * </pre>
	 */
	public String sendCommand(String s) throws IOException {
		if (!isConnected()) {
			connect();
		}
		if (s == null) {
			return null;
		}
		if (!s.endsWith("\n")) {
			s = s + "\n";
		}
		getOutputStream().write(s.getBytes());
		getOutputStream().flush();
		return s;
	}

	/***
	 * Reader thread. Reads lines from the TelnetClient and echoes them on the
	 * logger. PropertyChangeListeners are called with CLIENT_MESSAGE and String
	 * sent from VLC.
	 ***/
	@Override
	public void run() {
		/*
		 * InputStream instr = staticInstance.getInputStream();
		 * 
		 * byte[] buff = new byte[1024]; int ret_read = 0;
		 * 
		 * try { do { ret_read = instr.read(buff); if (ret_read > 0) { /*String
		 * s = new String(buff, 0, ret_read);
		 * 
		 * log.info(s); staticInstance.getSupport().firePropertyChange(
		 * CLIENT_MESSAGE, null, s); // listener on static // instance that //
		 * actually is connected // gets the message } } while (ret_read >= 0);
		 * } catch (Exception e) { /* log.log(Level.WARNING,
		 * "Reader ending - Exception while reading socket:{0}",
		 * e.getMessage());
		 * 
		 * }
		 */
	}

	/***
	 * Callback method called when TelnetClient receives an option negotiation
	 * command.
	 * <p>
	 * 
	 * @param negotiation_code
	 *            - type of negotiation command received (RECEIVED_DO,
	 *            RECEIVED_DONT, RECEIVED_WILL, RECEIVED_WONT)
	 *            <p>
	 * @param option_code
	 *            - code of the option negotiated
	 *            <p>
	 ***/
	@Override
	public void receivedNegotiation(int negotiation_code, int option_code) {
		/*
		 * String command = null; if (negotiation_code ==
		 * TelnetNotificationHandler.RECEIVED_DO) { command = "DO"; } else if
		 * (negotiation_code == TelnetNotificationHandler.RECEIVED_DONT) {
		 * command = "DONT"; } else if (negotiation_code ==
		 * TelnetNotificationHandler.RECEIVED_WILL) { command = "WILL"; } else
		 * if (negotiation_code == TelnetNotificationHandler.RECEIVED_WONT) {
		 * command = "WONT"; }
		 * 
		 * log.log(Level.INFO, "Received {0} for option code {1}", new Object[]
		 * { command, option_code });
		 */
	}

	/**
	 * @return the support. Listeners can get the stuff sent back from VLC with
	 *         CLIENT_MESSAGE events.
	 */
	public PropertyChangeSupport getSupport() {
		return _support;
	}

	@Override
	public void playMedium(Medium m) {
		playFiles(m.getFilesFromFolder());
	}

	@Override
	public void playPause() {
		try {
			sendCommand(PAUSE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void next() {
		try {
			sendCommand(NEXT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void previous() {
		try {
			sendCommand(PREV);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toggleFullscreen() {
		try {
			sendCommand("fullscreen on");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the Volume
	 * 
	 * @param vol
	 */
	public void volume(int vol) {
		if (vol > 0) {
			try {
				sendCommand("volume " + vol);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void playFiles(List<File> files) {
		try {
			if (files != null) {
				// add first file
				File first = files.get(0);
				String command = "add file:///" + first.getCanonicalPath();
				sendCommand(command);

				files.remove(first);
				// enqueue all the others
				for (File f : files) {
					command = "enqueue file:///" + f.getCanonicalPath();
					sendCommand(command);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void playMediumRandomly(Medium m) {
		List<File> files = m.getFilesFromFolder();
		files = Utilities.sortRandomly(files);
		playFiles(files);
	}

	@Override
	public void updateVLCConnection(String path, int port) {
		VLC_PATH = path;
		VLC_PORT = port;
		try {
			if (isConnected()) {
				disconnect();
			}
			connect();// also opens vlc
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public boolean isTrackPlaying(){ String command = "get_time"; String
	 * command2 = "get_length"; try { sendCommand(command); } catch (IOException
	 * e) { e.printStackTrace(); } }
	 */
}
