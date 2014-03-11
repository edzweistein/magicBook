package magicbook.gui.addmedia;

import java.io.File;
import java.util.HashSet;
import java.util.List;

import magicbook.MagicBook;
import magicbook.objects.Music;

/**
 * This tool collects all the information needed to add a music medium to magicBook.
 * @author jonas
 *
 */
public class AddMusicTool extends MediumSpecificQuestionerTool {

	protected AddMusicUI _ui;

	/**Constructor 
	 * @param files The files that belong to this album or music playlist
	 * @param coverArt The cover picture for this music
	 */
	public AddMusicTool(HashSet<File> files, File coverArt) {
		super(files, coverArt);
		_ui = new AddMusicUI();
		registerUIActions();
	}

	/**
	 * This function contains all the work( saving music to database,
	 * copying files to the magicbook folder structure and finally creating a playlist and a magiccard) here.
	 */
	@Override
	protected void reactOnSaveButton() {

		String title = _ui.getTitleText();
		String interpret = _ui.getInterpretText();
		int year = _ui.getYear();

		List<File> newFiles = null;

		// Add information to database
		Music m = new Music(title, interpret, year);
		
		try {
		    //in der Datenbank sichern
			MagicBook.getDatabase().save(m);
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		String destinationFolderPath = MagicBook.getMagicDirectory()
				+ m.getFolder();
		
		System.out.println("Pfad zu der Musik: " + destinationFolderPath);

		try {
		 
		    Thread saveThread = new Thread(new SaveThread(m, destinationFolderPath));
		    saveThread.start();

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Music wurde gespeichert, AddMediumtool muss zurückgesetzt werden
//		informAllObserversAboutChanges();

		_ui.closeWindow();
	}

	@Override
	public void showWindow() {
		_ui.showWindow();

	}

	@Override
	public AddMediumSubToolUI getUI() {
		return _ui;
	}
}
