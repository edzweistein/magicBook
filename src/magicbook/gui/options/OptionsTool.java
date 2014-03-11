package magicbook.gui.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import magicbook.MagicBook;
import magicbook.material.Options;

public class OptionsTool {
	private Options _options;
	private OptionsUI _ui;
	
	/**
	 * Constructor
	 * Creates a new Options object and loads option settings from file.
	 */
	public OptionsTool(){
		_ui = new OptionsUI();
		_options = Options.loadFromFile();
		if(_options!=null){
			loadOptions();
		}
		else{
			_options = new Options();
		}
		registerUIActions();
		
	}

	/**
	 * Registers listtener for the option window.
	 * 
	 */
	private void registerUIActions() {
		_ui.getSaveButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveOptions();
			}
		});
	}
	
	/**
	 * Saves option to separate file( data\options.xml)
	 */
	private void saveOptions(){
		_options.RANDOM_PLAYING=_ui.isRandomPlayingChecked();
		_options.USE_SECONDARY_CAMERA=_ui.isSecondaryCameraChecked();
		_options.VLC_PORT=_ui.getVLCPort();
		_options.VLC_PATH_WINDOWS=_ui.getVLCPath();
		
		_options.saveToFile();
		
		MagicBook.updateOptions(_options);
		_ui.closeWindow();
	}
	
	/**
	 * Loads option from separate file (data\options.xml
	 */
	private void loadOptions(){
		_ui.setVLCPath(_options.VLC_PATH_WINDOWS);
		_ui.setVLCPort(_options.VLC_PORT);
		if(_options.RANDOM_PLAYING)
			_ui.setCheckedRandomPlaying();
		if(_options.USE_SECONDARY_CAMERA)
			_ui.setCheckedSecondaryCamera();
		
	}
}
