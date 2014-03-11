package magicbook.objects;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

/**
 * @author edzweistein
 * 
 */
public enum Type {
	MUSIC(new String[] { ".mp3", ".ogg", ".m4a", ".wav", ".wma" }), 
	VIDEO(new String[] { ".mkv", ".mp4", ".mpeg", ".avi", ".mov", ".mpg",
					".divx", ".flv", ".wmv" }), 
	PROGRAM(new String[] { ".jar",
			".exe", ".sh" }), 
	PICTURES(new String[] { ".jpg", ".png", ".bmp",
			".jpeg" }), 
	REALLIFEOBJECT, 
	URL;

	private String[] _fileEndings;
	/**
	 * Constructor
	 */
	private Type() {
		_fileEndings = null;
	}

	/**
	 * The fileendings in an string array.
	 * 
	 * @param fileendings
	 */
	private Type(String[] fileendings) {
		assert fileendings != null : "Vorbedingung verletzt: fileendings!=null";
		_fileEndings = fileendings;
	}

	/**Get the file endings
	 * @return
	 */
	public String[] getFileEndings() {
		return _fileEndings;
	}

	/**
	 * Checks whether the file is of this type of media. It does this by
	 * comparing the ending of the file with the allowed fileending of this type
	 * of media.
	 * 
	 * @param file
	 *            The file that is going to be checked
	 * @return
	 * @throws IOException
	 *             If it was not possible to get the filepath
	 */
	public boolean isOfThisType(File file) throws IOException {
		String path;
		try {
			path = file.getCanonicalPath();
		} catch (IOException e) {
			throw e;
		}
		if (_fileEndings != null && _fileEndings.length > 0) {
			for (String possibleEnding : _fileEndings) {
				if (path.toLowerCase().endsWith(possibleEnding.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	/**Returns the type of this file
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static Type getTypeForFile(File file) throws IOException {
		String path;
		try {
			path = file.getCanonicalPath();
			EnumSet<Type> types = EnumSet.of(Type.MUSIC, Type.VIDEO,
					Type.PICTURES, Type.PROGRAM, Type.REALLIFEOBJECT, Type.URL);

			for (Type type : types) {
				String[] fileEndings = type.getFileEndings();
				if (fileEndings != null && fileEndings.length > 0) {
					for (String possibleEnding : fileEndings) {
						if (path.toLowerCase().endsWith(
								possibleEnding.toLowerCase())) {
							return type;
						}
					}
				}
			}

		} catch (IOException e) {
			throw e;
		}
		return null;
	}

	
}
