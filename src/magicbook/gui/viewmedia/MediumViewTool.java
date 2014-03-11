package magicbook.gui.viewmedia;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import magicbook.objects.Medium;

public class MediumViewTool {
	MediumViewToolUI _ui;

	Medium _selected;


	public MediumViewTool() {
		_ui = new MediumViewToolUI();
	}

	/**
	 * Returns the UI panel for this tool
	 * 
	 * @return ui panel
	 */
	public JPanel getUIPanel() {
		return _ui.getUIPanel();
	}

	/**
	 * Set list of media to display in the textarea of this tool.
	 * 
	 * @param selectedmedia
	 */
	public void setMedia(final List<Medium> selectedmedia) {
		JTextArea textArea = _ui.getMediaViewArea();

		// clears the whole textarea
		textArea.setText("");

		

		if (selectedmedia != null && !selectedmedia.isEmpty()) {
			for (Medium medium : selectedmedia) {
				textArea.append(medium.getStringRepresentation());
				textArea.append("Files:\n");
				List<String> filenames = medium.getFilenamesFromFolder();
				for(String s : filenames){
					textArea.append(s+"\n");
				}
				textArea.append("-------------------------- \n");
			}
			_ui.updateScrollPane();
			//stop running animation
			_ui.stopCoverAnimation();
			//start new animation
			_ui.startCoverAnimation(selectedmedia);
		} else {
			textArea.append("No media selected");
			_ui.updateScrollPane();
			_ui.stopCoverAnimation();
		}
	}
}
