package magicbook.gui.addmedia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import magicbook.gui.DropBoxTool;
import magicbook.material.Observable;
import magicbook.material.Observer;
import magicbook.objects.Type;

public class AddMediumTool extends Observable {

	private AddMediumUI _ui;
	private DropBoxTool _dropboxTool;
	private int _nextButtonWasClicked = 0;
	// url matching
	private Pattern _urlPattern;
	
	private MediumSpecificQuestionerTool _mediumspecquesttool;

	

	/**
	 * Constructor
	 */
	public AddMediumTool() {
		
	    _dropboxTool = new DropBoxTool();
//		_dropboxTool.addObserver(this);
		_ui = new AddMediumUI(_dropboxTool.getUI());
		registerUIActions();
		
//		_ui.showWindow();

		// url matching
		String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";// url
																									// regex
																									// pattern
		_urlPattern = Pattern.compile(regex);
	}


	/**
	 * Adds listeners to the ui elements, especially to the buttons.
	 */
	private void registerUIActions() {
		_ui.getNextButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				reactOnNextButton();
			}

		});

		_ui.getURLTextField().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				reactOnURLTextField(e);
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				
				
			}

		});
		
		_ui.getResetButton().addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                if(_nextButtonWasClicked == 0)
                {
                    reset();
                    _ui.getCancelButton().setEnabled(false);
                }
                else if(_nextButtonWasClicked >= 1)
                {
                    resetContentDropBox();
                }
            }
        });
		
		
		_ui.getCancelButton().addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                reset();
                _ui.getCancelButton().setEnabled(false);
            }
        });
		
		registerDropBoxListener();
	}

	/**
	 * Changes the mode of the ui to only enable a cover (=pictures) to be dropped
	 */
	public void setCoverArtDropMode() {
		try {
			JButton nextButton = _ui.getNextButton();
			nextButton.setText("Add " + _dropboxTool.getDroppedMediumType());
			nextButton.setEnabled(false);

			_ui.getResetButton().setEnabled(false);
			
			_dropboxTool.setCoverArtDropMode();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	/**Resets (deletes) the present content in the Dropbox and disables the Reset-Button
	 * 
	 */
	public void reset()
	{
	    _dropboxTool.setDropMediumMode();
        _nextButtonWasClicked = 0;
       
        _ui.getResetButton().setEnabled(false);
        
        //resets the UI of this tool
        _ui.resetUI();
	}
	
	
	/**Returns the UI-Panel
	 * @return
	 */
	public JPanel getUIPanel()
	{
	    return _ui.getUIPanel();
	}

	/**
	 * Returns the MediumSpecificQuestionerTool
	 * 
	 * @return
	 */
	public MediumSpecificQuestionerTool getMediumSpecQuestTool()
	{
	    return _mediumspecquesttool;
	}
	

	/**
	 * Registers the listener for the dropbox.
	 * e.g. if some media files have been added to textarea or the dropbox
	 * 
	 */
	private void registerDropBoxListener()
	{
	    _dropboxTool.addObserver(new Observer()
        {
            
            @Override
            public void reactOnChanges(Observable observable)
            {
                DropBoxTool dropbox = (DropBoxTool) observable;
                try {
                    _ui.setHeading(dropbox.getDroppedMediumType()
                            + " will be added");
                    if(_dropboxTool.existsDroppedFiles())
                    {
                        _ui.getNextButton().setEnabled(true);
                        _ui.getResetButton().setEnabled(true);
                    }
                    _ui.getCancelButton().setEnabled(true);
                } catch (Exception e) {
                    /* TODO: maybe present exception to user 16.06.2012 */
                    e.printStackTrace();
                }
            }
        });
	}
	
	/**
	 * the reaction which appears when the next button in the ui is clicked or
	 * pressed
	 */
	private void reactOnNextButton() {
		_nextButtonWasClicked++;
		// nextButton will be enabled after something was dropped, so this will
		// be called after something was dropped
		if (_nextButtonWasClicked == 1) {
			setCoverArtDropMode();
		} else if (_nextButtonWasClicked >= 2) {
			// now finally we have gotten the files which belong to this medium
			// AND we got the coverart!
			// lets go save this and ask for further medium specific
			// questions (e.g. music album, interpret or game name...) in new windows
			Type droppedMediumType;
			try {
				droppedMediumType = _dropboxTool.getDroppedMediumType();
				if(droppedMediumType != null){
					
				
				if (droppedMediumType.equals(Type.MUSIC)) {
					AddMusicTool addMusicTool = new AddMusicTool(_dropboxTool.getFiles(),
							_dropboxTool.getCoverArt());
					_mediumspecquesttool = addMusicTool;
				
					informAllObserversAboutChanges();
					
				} else if (droppedMediumType.equals(Type.VIDEO)) {
					System.out.println("Video hinzufügen");
					AddVideoTool addVideoTool = new AddVideoTool(_dropboxTool.getFiles(),
							_dropboxTool.getCoverArt());
					_mediumspecquesttool = addVideoTool;

					informAllObserversAboutChanges();
					
				} else if (droppedMediumType.equals(Type.PICTURES)) {
					System.out.println("Bilder hinzufügen");
				} else if (droppedMediumType.equals(Type.PROGRAM)) {
					System.out.println("Program/Spiel hinzufügen");
				}else{
					//cant recognize filetype
					JOptionPane.showMessageDialog(null,"Woops...magicBook can't recognize this filetype.");
				}
				}else{
					//URL was entered, otherwise we wouldnt be here
					//String title = JOptionPane.showInputDialog(null, "What's the title for this URL?");
					//URL url = new URL
				}

			} catch (Exception e) {
				/* TODO: maybe present the exception to the user 16.06.2012 */
				e.printStackTrace();
			}

//			_ui.closeWindow();
		}
		

	}

	/**Reacts if something has changed in the url textfield
	 * @param e
	 */
	private void reactOnURLTextField(KeyEvent e) {
		/*
		 * TODO: noch mit einberechnen,dass man auch oben schon was gedroppt
		 * haben kann 17.06.2012
		 */
		if (isURL(_ui.getURLText())) {
			_ui.getNextButton().setEnabled(true);
			
		}

	}
	
	/**Checks whether the string matches the _urlPattern
	 * @param urlText string which is getting checked
	 * @return
	 */
	private boolean isURL(String urlText) {
		Matcher matcher = _urlPattern.matcher(urlText);
		return matcher.matches();

	}
	
	/**
	 * Resets the content of the dropbox.
	 * e.g. remove files which the user dropped to the dropbox
	 * 
	 */
	private void resetContentDropBox()
	{
	    _dropboxTool.clear();
	    _ui.getResetButton().setEnabled(false);
	}

	/********* for test purposes *******/
	
	public int getNextButtonCount()
	{
	    return _nextButtonWasClicked;
	}
	
	public AddMediumUI getUI()
	{
	    return _ui;
	}
}
