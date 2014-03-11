package tools.playlist.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import magicbook.MagicBook;

import org.junit.Test;

import tools.playlist.PlaylistTool;

public class PlaylistToolTest {

	/**
	 *Checks if a playlist file for two example songs can be created,
	 *but just checks whether it exists or not, not if it is working
	 *this has to been tested when working with magicBook (but seems to work fine :))
	 */
	@Test
	public void testCreatePlaylist() {
		String rootdirectory = MagicBook.getMagicDirectory()+"//tests//EAR//";
		List<File> files = new ArrayList<File>();
		files.add(new File(rootdirectory+"EAR.mp3"));
		files.add(new File(rootdirectory+"ego.mp3"));
		try {
			PlaylistTool.createSpiffPlaylist(files, rootdirectory , "Test Title", "Test creator");
			File playlist = new File(rootdirectory+"playlist.xspf");
			assertTrue(playlist.exists());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
