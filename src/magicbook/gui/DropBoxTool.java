package magicbook.gui;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import magicbook.material.Observable;
import magicbook.objects.Music;
import magicbook.objects.Pictures;
import magicbook.objects.Type;
import magicbook.objects.Video;
import net.iharder.dnd.FileDrop;

/**
 * This Tool provides a dropbox where the user can drop files or urls into, to
 * link them to mediums they create with the addmediumtool.
 * 
 * @author edzweistein
 * 
 */
public class DropBoxTool extends Observable {

	private static final int FILETYPE_ACCEPT_ALL = 0;
	private static final int FILETYPE_ACCEPT_ONLY_IMAGES = 1;
	private static final int FILETYPE_ACCEPT_ONLY_MUSIC = 2;
	private static final int FILETYPE_ACCEPT_ONLY_VIDEO = 3;
	private DropBoxUI _ui;
	private HashSet<File> _droppedFiles;
	private HashSet<File> _filesForMedium;

	// indicates whether CoverArtDropMode is active
	private boolean _coverArtMode = false;

	private int FILETYPE_ACCEPTED = 0;

	/**
	 * 
	 */
	public DropBoxTool() {
		_droppedFiles = new HashSet<File>();
		_ui = new DropBoxUI();

		/*
		 * TODO: filedrop akzeptiert kein uri links also nur datein aus dem
		 * dateisystem...was ist mit covers direkt aus dem internet?! 05.06.2012
		 */
		new FileDrop(null, _ui.getTextArea(), new FileDrop.Listener() {
			public void filesDropped(File[] files) {
				addFiles(files);
				updateUI();
				informAllObserversAboutChanges();

			}

		});
	}

	/**
	 * Update the ui.
	 * 
	 */
	protected void updateUI() {

		JTextArea textArea = _ui.getTextArea();
		if (!_droppedFiles.isEmpty()) {
			textArea.setText("");
			for (File f : _droppedFiles) {
				try {
					textArea.append(f.getCanonicalPath() + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Adds the files to the hashset of files (_droppedFiles),folders and
	 * subfolders are read and all containing files are add
	 * 
	 * @param files
	 */
	protected void addFiles(File[] files) {
		for (File file : files) {
			// Directory found,was dropped
			if (file.isDirectory()) {
				addFiles(file.listFiles());
			} else {// if file really is a file
				try {
					if (isExpectedFiletype(file)) {
						_droppedFiles.add(file);
					}
				} catch (Exception e) {
					/* TODO: maybe present the exception to the user 16.06.2012 */
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Checks whether the file is an expected file(e.g. if the file is an image,
	 * if the dropbox is waiting for a dropped coverart)
	 * 
	 * @param file
	 * @return
	 */
	private boolean isExpectedFiletype(File file) throws Exception {
		switch (FILETYPE_ACCEPTED) {
		case FILETYPE_ACCEPT_ALL:// all files are accepted
			return true;
		case FILETYPE_ACCEPT_ONLY_IMAGES:
			return new Pictures().isOfThisMediaType(file);
		case FILETYPE_ACCEPT_ONLY_MUSIC:
			return new Music().isOfThisMediaType(file);
		case FILETYPE_ACCEPT_ONLY_VIDEO:
			return new Video().isOfThisMediaType(file);
		}
		return false;
	}

	/**
	 * Changes the acceptance of the dropbox to only images
	 * 
	 */
	private void expectImageToBeDroppedNext() {
		FILETYPE_ACCEPTED = FILETYPE_ACCEPT_ONLY_IMAGES;
	}

	/**
	 * This method returns the type of the medium which was dropped, so that the
	 * ui can respond to this and change its "questions"
	 * 
	 * @return
	 * @throws Exception
	 */
	public Type getDroppedMediumType() throws Exception {
		// analyse the type of the first dropped file
		if (_coverArtMode) {
			if (!_filesForMedium.isEmpty()) {
				for (File f : _filesForMedium) {
					// only take a look at the first file
					return Type.getTypeForFile(f);
				}
			}
		} else {
			if (!_droppedFiles.isEmpty()) {
				for (File f : _droppedFiles) {
					// only take a look at the first file
					return Type.getTypeForFile(f);
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns whether files habe been dropped to the dropbox.
	 * 
	 * @return true, if files were dropped to the dropbox
	 */
	public boolean existsDroppedFiles()
	{
	    if(!_droppedFiles.isEmpty())
	    {
	        return true;
	    }
	    return false;
	}
	
	/**
	 * Appends all filepaths which are in the folder (or subfolders) to the
	 * textarea. No directories should be visible only files.
	 * 
	 * @param textarea
	 * @param folder
	 * @throws IOException
	 */
	private void appendFiles(JTextArea textarea, File folder)
			throws IOException {
		File[] files = folder.listFiles();/*
										 * TODO: filefilter bei listfiles
										 * benutzen, um dateien, die nicht
										 * abgespielt werden können zu filtern
										 * 05.06.2012
										 */
		for (File f : files) {
			if (f.isFile()) {
				_droppedFiles.add(f);
			} else if (f.isDirectory()) {
				appendFiles(textarea, f);
			}
		}
	}

	/**
	 * Returns the UI panel for this tool.
	 * 
	 * @return ui panel
	 */
	public JPanel getUIPanel() {
		return _ui.getPanel();
	}

	/**
	 * Returns the ui.
	 * 
	 * @return ui object
	 */
	public DropBoxUI getUI() {
		return _ui;
	}

	/**
	 * Returns all files which are relevant for this medium
	 * 
	 * @return
	 */
	public HashSet<File> getFiles() {
		return _filesForMedium;
	}

	/**
	 * Returns the coverart for this medium, should only be called
	 * 
	 * @return
	 */
	public File getCoverArt() {
		if (_coverArtMode) {
			for (File f : _droppedFiles) {
				return f;
			}
		}
		return null;
	}

	/**
	 * Changes the mode of the ui to ONLY enable a cover to be dropped
	 */
	public void setCoverArtDropMode() {
		// save all files which where dropped till here, and remove them from
		// _droppedFiles
		if (!_coverArtMode) {
			_coverArtMode = true;
			_filesForMedium = new HashSet<File>();
			for (File file : _droppedFiles) {
				_filesForMedium.add(file);
			}

			_droppedFiles.clear();
			_ui.setCoverArtDropMode();
			expectImageToBeDroppedNext();
		}
	}

	/**
	 * Changes the mode of the ui to enable media files to be dropped
	 * 
	 */
	public void setDropMediumMode() {
		_coverArtMode = false;
		_droppedFiles.clear();
		FILETYPE_ACCEPTED = FILETYPE_ACCEPT_ALL;
		_ui.setMediumDropMode();
	}

	/**
	 * Clears the set of droppedFiles and JTextArea of the ui.
	 * 
	 */
	public void clear() {
		_droppedFiles.clear();

		_ui.getTextArea().setText("");
	}
}
