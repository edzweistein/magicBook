package magicbook.gui.addmedia;

import java.io.File;
import java.util.HashSet;
import java.util.List;



import magicbook.MagicBook;
import magicbook.objects.Video;

public class AddVideoTool extends MediumSpecificQuestionerTool {

	private AddVideoUI _ui;

	/**
	 * Creates a new window to add meta data to given video files.
	 * 
	 * @param files that belong to the video oder playlist
	 * @param coverArt he cover picture for this music
	 */
	public AddVideoTool(HashSet<File> files, File coverArt) {
		super(files, coverArt);
		_ui = new AddVideoUI();
		registerUIActions();
	}

	@Override
	public void showWindow() {
		_ui.showWindow();
	}

	@Override
	protected void reactOnSaveButton() {
		String title = _ui.getTitleText();
		String regie = _ui.getRegie();
		int year = _ui.getYear();


		List<File> newFiles = null;

		// Add information to database
		Video m = new Video(title, regie, year);

		try {
			MagicBook.getDatabase().save(m);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String destinationFolderPath = MagicBook.getMagicDirectory()
				+ m.getFolder();

		try {
		    
		    Thread saveThread = new Thread(new SaveThread(m, destinationFolderPath));
            saveThread.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

//		informAllObserversAboutChanges();

		_ui.closeWindow();
	}

	@Override
	public AddMediumSubToolUI getUI() {
		return _ui;
	}
}
