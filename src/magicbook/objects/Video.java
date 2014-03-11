package magicbook.objects;

import java.awt.image.BufferedImage;

import magicbook.MagicBook;
import magicbook.magiccards.MagicCardLayout;
import magicbook.magiccards.MagicCardLayoutStandard;
import tools.bufferedimage.BufferedImageTool;
import tools.vlc.VLCController;

/**
 * This class represents videos and movies It is supported in the current
 * version.
 * 
 * @author edzweistein
 * 
 */
public class Video extends Medium {

	public Video() {
		super(Type.VIDEO);
	}

	/* TODO: exemplarvariablen hinzufügen 23.04.2012 */
	private String _title;
	private String _regie;
	private int _year;

	public Video(String title, String regie, int year) {
		super(Type.VIDEO);
		_title = title;
		_regie = regie;
		_year = year;
	}

	/**
	 * @return the Title of the video
	 */
	public String getTitle() {
		return _title;
	}

	/**
	 * @return the year the video was released
	 */
	public int getYear() {
		return _year;
	}

	public String getRegie() {
		return _regie;
	}

	@Override
	public int getID() {
		String temp = "" + getTitle() + getRegie();
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
		switch (MagicBook.OPTIONS.ACTION_VIDEO) {
		case 0:

			VLCController vlc = MagicBook.getVLCController();
			if (vlc != null) {
				if (MagicBook.OPTIONS.RANDOM_PLAYING) {
					vlc.playMediumRandomly(this);
				} else {
					vlc.playMedium(this);
				}
			}
			// TODO Fullscreen toggle
			// Thread.sleep(20);
			// vlc.toggleFullscreen();

			break;
		case 1:
			// new InfoPopUpTool(this);
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
		return "//medium//videos//" + _title.toLowerCase() + "//";
	}

	@Override
	public BufferedImage getCoverArt() {
		return BufferedImageTool.loadImage(MagicBook.getMagicDirectory()
				+ getFolder() + "cover.jpg");
	}

	public static Type getType() {
		return Type.VIDEO;
	}

	@Override
	public String getStringRepresentation() {
		String result = this.TYPE + " \n" + _title + " \n" + _regie + " \n"
				+ _year + " \n";

		return result;
	}

	@Override
	public Type getMediaType() {
		return getType();
	}

}
