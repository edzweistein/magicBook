package tools.playlist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import tools.Utilities;


import com.melloware.jspiff.jaxp.XspfPlaylist;
import com.melloware.jspiff.jaxp.XspfPlaylistTrackList;
import com.melloware.jspiff.jaxp.XspfTrack;

public class PlaylistTool {

	/**
	 * Creates a playlist (in XSPF-Format ) which is readable for the VLC-Player
	 * http://melloware.com/products/jspiff/jaxp.html
	 * 
	 * @param files
	 *            The files that were dropped
	 * @param destinationFolder
	 *            the path for the playlist
	 * @param title
	 *            the title of the medium
	 * @param creator
	 *            the Interpret or director for this medium
	 * @throws IOException
	 * @require List of files should be in the correct order
	 */
	public static void createSpiffPlaylist(List<File> files, String destinationFolder,
			String title, String creator) throws Exception {

		final XspfPlaylist playlist = new XspfPlaylist();
		final XspfPlaylistTrackList tracks = new XspfPlaylistTrackList();

		playlist.setTitle(title);

		// set the right,natural order
		Utilities.sortFilesByName(files);

		/* Add Files to playlist */
		for (File file : files) {
			XspfTrack track = new XspfTrack();
			// add information for this track
			track.setLocation("file://" + file.getCanonicalPath());
			track.setCreator(creator);
			track.setAlbum(title);
			tracks.addTrack(track);
		}
		playlist.setPlaylistTrackList(tracks);
		/* write Playlist to File */

		final File file = new File(destinationFolder + "playlist.xspf");
		final OutputFormat format = OutputFormat.createPrettyPrint();

		final XMLWriter writer = new XMLWriter(new FileWriter(file), format);
		final Document doc = DocumentHelper.parseText(playlist
				.makeTextDocument());
		writer.write(doc);
		writer.close();

	}
}
