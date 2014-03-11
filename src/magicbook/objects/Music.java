package magicbook.objects;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

import magicbook.MagicBook;
import magicbook.magiccards.MagicCardLayout;
import magicbook.magiccards.MagicCardLayoutStandard;
import magicbook.material.Comment;
import tools.bufferedimage.BufferedImageTool;
import tools.vlc.VLCController;

/**
 * Defines music with common fields like title , interpret and year.
 * 
 * In order to calculate the hashcode the title and interpret attribut are
 * necessary.
 * 
 * This class is supported in the current version.
 * 
 * @author Jonas, Niels
 * 
 */
public class Music extends Medium {

	// public static final String TYPE = "music";

	private String _interpret;
	private int _year;

	// public static Type TYPE = Type.MUSIC;

	public Music() {
		super(Type.MUSIC);
	}

	/**
	 * Creates a new Medium with Music as its type.
	 * 
	 * @param title
	 * @param interpret
	 * @param year
	 * @param comments
	 * @param tags
	 * 
	 * @require title != null;
	 * @require interpret != null;
	 */
	public Music(String title, String interpret, int year,
			List<Comment> comments, Set<String> tags) {
		super(Type.MUSIC);
		assert title != null : "Vorbedingung verletzt : title != null";
		assert interpret != null : "Vorbedingung verletzt : interpret != null";

		_title = title;
		_interpret = interpret;
		_year = year;
		_comments = comments;
		_tags = tags;

		// TODO saveToFile() muss zum Speichern noch aufgerufen werden
		// saveToFile();
	}

	/**Constructor for Music
	 * @param title
	 * @param interpret
	 * @param year
	 */
	public Music(String title, String interpret, int year) {
		this(title, interpret, year, null, null);
	}

	/**Gets the interpret name
	 * @return
	 */
	public String getInterpret() {
		return _interpret;
	}

	/**Get the release year
	 * @return
	 */
	public int getYear() {
		return _year;
	}

	/*public void setYear(int year) {
		_year = year;
		saveToFile();
	}*/

	@Override
	public int getID() {

		String temp = "" + getTitle() + getInterpret();
		temp = temp.toLowerCase();
		// remove all whitespaces
		String finished = "";
		for (int i = 0; i < temp.length(); i++) {
			char c;
			if ((c = temp.charAt(i)) != ' ')
				finished += c;
		}
		return Math.abs(finished.hashCode());
	}

	@Override
	public void doMagic() {
		
		switch (MagicBook.OPTIONS.ACTION_MUSIC) {
		case 0:
			// opens the xpsf playlist in vlc
			VLCController vlc = MagicBook.getVLCController();
			
			if (vlc != null) {
				if(MagicBook.OPTIONS.RANDOM_PLAYING){
					vlc.playMediumRandomly(this);
				}
				else{
					vlc.playMedium(this);
				}
				
				//
				System.out
						.println("Yeayy! Please Mr. Dj put some music on!! This time its: "
								+ _title + " - " + _interpret + ":)");
			}else{
				System.out.println("Fehler beim Abspielen der Musik!");
			}
			break;
		case 1:
			//new InfoPopUpTool(this);
			break;
		}
	}

	

	

	@Override
	public MagicCardLayout getMagicCardLayout() {
		return new MagicCardLayoutStandard(this);
	}

	/**
	 * Returns a string representation of the path where the media files are
	 * stored (e.g. the music files, or the video file...)
	 * 
	 * @return the path to the media files
	 */
	public String getFolder() {
		return "//medium//music//" + _interpret.toLowerCase() + " - "
				+ _title.toLowerCase() + "//";
	}

	@Override
	public BufferedImage getCoverArt() {
		return BufferedImageTool.loadImage(System.getProperty("user.dir")
				+ getFolder() + "cover.jpg");
	}

	
	/**Returns the Music-Type
	 * @return
	 */
	public static Type getType() {
		return Type.MUSIC;
	}

	@Override
	public String getStringRepresentation() {
		String result = this.TYPE + " \n" + _title + " \n" + _interpret + " \n"
				+ _year + " \n";

		return result;

	}

	@Override
	public Type getMediaType() {
		return getType();
	}



}
