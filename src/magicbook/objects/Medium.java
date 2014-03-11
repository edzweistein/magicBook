package magicbook.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import magicbook.MagicBook;
import magicbook.magiccards.MagicCardLayout;
import magicbook.material.Comment;
import tools.Utilities;
import tools.database.DataBaseToolGen;
import tools.qrcode.QRCodeCreater;
import tools.qrcode.QRCodeCreaterZXING;

/**
 * @author jonas
 * 
 */
public abstract class Medium {

	protected List<Comment> _comments;
	protected Set<String> _tags;

	protected String _title;

	public Type TYPE;

	public Medium(Type type) {
		assert type != null : "Vorbedingung verletzt: type!=null";
		_comments = new ArrayList<Comment>();
		_tags = new HashSet<String>();
		TYPE = type;
	}

	/**
	 * Returns a list of comments which are available for this medium.
	 * 
	 * @return List of Comments users made for this Medium
	 */
	public List<Comment> getComments() {
		return _comments;
	}

	/**
	 * Adds the comment to this Medium
	 * 
	 * @param c
	 */
	public void addComment(Comment c) {
		_comments.add(c);
		saveToFile();
	}

	/**
	 * @return a Set of Tags users added to this Medium
	 */
	public Set<String> getTags() {
		return _tags;
	}

	/**
	 * Adds the tag to this medium
	 * 
	 * @param tag
	 *            the Tag the user entered
	 */
	public void addTag(String tag) {
		_tags.add(tag);
		saveToFile();
	}

	/**
	 * Returns the title / headline for this medium.
	 * 
	 * @return
	 */
	public String getTitle() {
		return _title;
	}

	/**
	 * @return a HashCode which identifies this Medium in the database and is
	 *         being saved in the QR-Code
	 * @ensure medium1.getID() != medium2.getID() for all mediums
	 */
	public abstract int getID();

	/**
	 * Does the Magic-Plays the music,movie or starts the program/website...the
	 * options are located in MagicBook.OPTIONS
	 */
	public abstract void doMagic();

	/**
	 * Returns the MagicCardLayout which describes how the magiccard for this
	 * medium should look like when printed
	 * 
	 * @return
	 */
	public abstract MagicCardLayout getMagicCardLayout();

	/**
	 * Creates an QR-code for this medium
	 * 
	 * @param width
	 *            width of the qr-code in pixel
	 * @param height
	 * @return
	 */
	public BufferedImage createQRCode(int width, int height) {

		QRCodeCreater qrcreater = new QRCodeCreaterZXING();
		return qrcreater.getQRCode(this, height, width);

	}

	/**
	 * @return True if saving worked
	 */
	public boolean saveToFile() {
		try {
			DataBaseToolGen database = MagicBook.getDatabase();
			if (database != null)
				database.save(this);
			return true;
		} catch (Exception e) {
			/* TODO: ERROR-anzeigen 10.05.2012 */
			JOptionPane
					.showMessageDialog(null,
							"Sorry no magic for you here, adding the medium to the database failed!");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Returns the CoverArt for this individual medium (if it has an individual
	 * coverart, otherwise a standard one is returned, or one might be created
	 * dynamically in the future.
	 * 
	 * @return
	 */
	abstract public BufferedImage getCoverArt();

	/**
	 * Returns the type of this mediums
	 * 
	 * @return
	 */
	public abstract Type getMediaType();

	/**
	 * Checks whether this file is of this medium type
	 * 
	 * @param file
	 *            the file which will be checked
	 * @return
	 * @require file!=null && file.isFile()
	 */
	public boolean isOfThisMediaType(File file) throws Exception {

		try {
			return TYPE.isOfThisType(file);
		} catch (NullPointerException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	

	/**
	 * Get the folder containing the files for this medium (without magicbook
	 * root directory)
	 * 
	 * @return
	 */
	public abstract String getFolder();

	/**
	 * Returns the files from this medium
	 * 
	 * @return
	 */
	public List<File> getFilesFromFolder() {
		List<File> files = new ArrayList<File>();
		File folder = new File(MagicBook.getMagicDirectory() + getFolder());
		if (folder != null && folder.isDirectory()) {
			File[] filesarray = folder.listFiles();
			if (filesarray != null && filesarray.length > 0) {
				for (File f : filesarray) {
					try {
						if (isOfThisMediaType(f)) {
							files.add(f);
						}
					} catch (Exception e) {	
						//e.printStackTrace();
					}
				}
				Utilities.sortFilesByName(files);
				return files;
			}
		}
		return null;
	}
	
	/**Returns all the names of the files that belong to this medium (which have been once dropped by the user,whithout the coverart)
	 * @return
	 */
	public List<String> getFilenamesFromFolder(){
		List<String> filenames = new ArrayList<String>();
		List<File> files = getFilesFromFolder();
		
		for(File f : files){
			filenames.add(f.getName());
		}
		return filenames;
	}

	/**
	 * Compare two Medium objects
	 * 
	 * @param m
	 *            other Medium object
	 * @return
	 */
	public boolean equals(Medium m) {
		return getID() == m.getID();
	}

	public abstract String getStringRepresentation();

}
