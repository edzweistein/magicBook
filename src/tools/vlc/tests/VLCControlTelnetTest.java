package tools.vlc.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import magicbook.material.Options;

import org.junit.BeforeClass;
import org.junit.Test;

import tools.database.DataBaseSQLTool;
import tools.vlc.VLCControlTelnet;
import tools.vlc.VLCController;




public class VLCControlTelnetTest {

	static VLCController _vlcControl;
	static DataBaseSQLTool _database;
	
	@BeforeClass
	public static void setUp() {
		

		String opts = System.getProperty("os.name").toLowerCase();
		
		if (opts.startsWith("windows")) {
			Options options = Options.loadFromFile();
			String path = options.VLC_PATH_WINDOWS;
			System.out.println("Pfad zu VLC im Test: " +path);
			_vlcControl = new VLCControlTelnet(path, 4212);
		}
		else{
			_vlcControl = new VLCControlTelnet("",4212);//runs only on unix and with standard vlc port
		}
		_database = new DataBaseSQLTool();
		assertTrue(_vlcControl!=null);
		
		
	}
	
	public void testOpenVLC(){
		((VLCControlTelnet) _vlcControl).openVLC();
	}
	
	@Test
	public void testConnect(){
		testOpenVLC();
		assertTrue(_vlcControl!=null);
		try {
			((VLCControlTelnet) _vlcControl).connect();//test only runs on unix, and with the standard vlc port activated
		} catch (IOException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testSendCommand() {
		assertTrue(_vlcControl!=null);
		_vlcControl.playPause();
		//_vlcControl.toggleFullscreen();
	}
	
	@Test
	public void testVolume(){
		((VLCControlTelnet) _vlcControl).volume(150);
	}
	
	
	
	/*
	@Test
	public void testPlayMedium(){
		Medium m = _database.loadMediumForType("music", "1111111");
		assertTrue(m!=null);
		_vlcControl.playMedium(m);
	}
	*/
	
	
}