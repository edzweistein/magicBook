package magicbook.gui.options;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import magicbook.MagicBook;

public class OptionsUI {
	private JFrame _frame;
	private JButton _saveButton;
	private JTextField _textVLCPath,_textVLCPort;
	private JCheckBox _checkRandomPlaying,_checkSecondaryCamera;
	
	
	public OptionsUI(){
		createWindow();
	}
	
	/**
	 * Creates and makes a window visible to chance options.
	 * 
	 */
	private void createWindow() {
		_frame = new JFrame("magicBook - Options");
		
		Container panel = _frame.getContentPane();
		panel.setLayout(new BorderLayout());
		
		//VLC Options
		Container cvlc = new Container();
		cvlc.setLayout(new BorderLayout());
		JLabel lvlc = new JLabel("VLC Options");
		cvlc.add(lvlc,BorderLayout.NORTH);
		
		Container cvlc1 = new Container();
		cvlc1.setLayout(new BorderLayout());
		JLabel lvlcpath = new JLabel("if using Windows: Path to the vlc.exe:");
		cvlc1.add(lvlcpath,BorderLayout.NORTH);
		_textVLCPath = new JTextField("");
		cvlc1.add(_textVLCPath,BorderLayout.SOUTH);
		cvlc.add(cvlc1,BorderLayout.CENTER);
		
		Container cvlc2 = new Container();
		cvlc2.setLayout(new FlowLayout());
		JLabel lvlcport = new JLabel("VLC Telnet Port:");
		cvlc2.add(lvlcport);
		_textVLCPort = new JTextField(""+MagicBook.OPTIONS.VLC_STANDARD_TELNET_PORT);
		cvlc2.add(_textVLCPort);
		cvlc.add(cvlc2,BorderLayout.SOUTH);
		
		panel.add(cvlc,BorderLayout.NORTH);
		
		//Camera Options
		Container ccamera = new Container();
		ccamera.setLayout(new BorderLayout());
		JLabel lcamera = new JLabel("Camera Options");
		ccamera.add(lcamera,BorderLayout.NORTH);
		_checkSecondaryCamera = new JCheckBox("Use secondary camera");
		ccamera.add(_checkSecondaryCamera,BorderLayout.CENTER);
		
		panel.add(ccamera,BorderLayout.CENTER);
		
		//Playback Options
		Container cplayback = new Container();
		cplayback.setLayout(new BorderLayout());
		JLabel lplayback = new JLabel("Playback Options");
		cplayback.add(lplayback,BorderLayout.NORTH);
		_checkRandomPlaying = new JCheckBox("Do your magic randomly...Play files in random order");
		cplayback.add(_checkRandomPlaying,BorderLayout.CENTER);
		
		
		
		//saveButton
		_saveButton = new JButton("Save Options!");
		cplayback.add(_saveButton,BorderLayout.SOUTH);
		
		panel.add(cplayback,BorderLayout.SOUTH);
		
		_frame.setSize(400,500);
		_frame.setVisible(true);
		
	}
	
	/**
	 * Returns the 'save'-Button
	 * 
	 * @return save button
	 */
	public JButton getSaveButton(){
		return _saveButton;
	}
	
	/**
	 * Returns path to vlc installation typed in by user. (user input)
	 * @return path to vlc directory (where 'vlc.exe' is located)
	 */
	public String getVLCPath(){
		return _textVLCPath.getText();
	}
	
	/**
	 * Returns the port type in by user (user input).
	 * 
	 * @return port to communicate with vlc
	 */
	public int getVLCPort(){
		try{
			int port = Integer.parseInt(_textVLCPort.getText());
			return port;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(_frame, "Please enter a valid port number! :)");
			return MagicBook.OPTIONS.VLC_STANDARD_TELNET_PORT;
		}
	}
	
	/**
	 * Returns whether 'random-player' checkbox is checked.
	 * 
	 * @return true if checkbox is selected
	 */
	public boolean isRandomPlayingChecked(){
		return _checkRandomPlaying.isSelected();
	}
	
	/**
	 * Returns whether 'secondary-camera' option is checked.
	 * 
	 * @return true if checkbox is selected
	 */
	public boolean isSecondaryCameraChecked(){
		return _checkSecondaryCamera.isSelected();
	}
	
	/**
	 * Sets the'random-player' checkbox status to selected.
	 */
	public void setCheckedRandomPlaying(){
		_checkRandomPlaying.setSelected(true);
	}
	
	/**
	 * Sets the'secondary-camera' checkbox status to selected.
	 */
	public void setCheckedSecondaryCamera(){
		_checkSecondaryCamera.setSelected(true);
	}
	
	/**
	 * Sets the content of the vlc path textfield.
	 * 
	 * @param path to vlc 
	 */
	public void setVLCPath(String path){
		_textVLCPath.setText(path);
	}
	
	/**
	 * Sets the content of the vlc port textfield
	 * 
	 * @param port to communicate with vlc
	 */
	public void setVLCPort(int port){
		_textVLCPort.setText(""+port);
	}
	
	/**
	 * Closes the option window.
	 */
	public void closeWindow() {
		_frame.dispose();
	}
	
	
	
}
