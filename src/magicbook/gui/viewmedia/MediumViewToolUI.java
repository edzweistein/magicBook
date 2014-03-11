package magicbook.gui.viewmedia;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import magicbook.objects.Medium;
import tools.ToolUI;

public class MediumViewToolUI extends ToolUI {

	private JTextArea _mediaViewArea;
	private Zeichenebene _mediaCoverArea;
	private Timer _coverArtLoop;
	private JScrollPane _mediaViewScrollPane;

	public MediumViewToolUI() {
		_mainPanel = createPanel();
	}

	@Override
	public JPanel createPanel() {
		JPanel mpanel = new JPanel();

		mpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		mpanel.setAlignmentY(Component.TOP_ALIGNMENT);

		BoxLayout layout = new BoxLayout(mpanel, BoxLayout.LINE_AXIS);
		// FlowLayout layout = new FlowLayout();
		mpanel.setLayout(layout);

		Component mediaViewComponent = createViewArea();
		
		mpanel.add(mediaViewComponent);

		_mediaCoverArea = new Zeichenebene(200, 200);

		mpanel.add(_mediaCoverArea);

		return mpanel;
	}

	/**
	 * Display the cover for this selected list of media
	 * 
	 * @param m
	 */
	public void showCoverArt(Medium m) {
		BufferedImage cover = m.getCoverArt();
		showCoverArt(cover);

	}
	
	private void showCoverArt(BufferedImage cover){
		_mediaCoverArea.clear();
		if (cover != null) {
			_mediaCoverArea.draw(new ImageDrawCommand(0, 0, 200, 200, cover));
		}
	}

	public JTextArea getMediaViewArea() {
		return _mediaViewArea;
	}

	/**
	 * Starts the animation for the media cover. 
	 * Shows another cover after a period of time.
	 * 
	 * @param selected media
	 */
	public void startCoverAnimation(List<Medium> mediums) {
		if (mediums != null && mediums.size()>0) {
			// just one selected medium
			if (mediums.size() == 1) {
				showCoverArt(mediums.get(0));
			} else {
				// more than one medium selected-> start animation
				//TODO: Bilder vor dem loop alle laden damit diese nicht immer neu aus dem dateisystem geladen werden müssen!
				final List<BufferedImage> covers = new ArrayList<BufferedImage>();
				for(Medium m : mediums){
					covers.add(m.getCoverArt());
				}
				_coverArtLoop = new Timer(2000, new ActionListener() {
					int count = 0;

					public void actionPerformed(ActionEvent evt) {
					
						showCoverArt(covers.get(
								count % covers.size()));
						count++;
					}

				});
				
				_coverArtLoop.start();
			}
		}
	}

	/**
	 * Stos the cover animation.
	 */
	public void stopCoverAnimation() {
		// stops the last cover animation
		if (_coverArtLoop != null) {
			_coverArtLoop.stop();
		}
		_mediaCoverArea.clear();
	}

	/**
	 * Creates the view area.
	 * 
	 * @return scrollable pane 
	 */
	private JScrollPane createViewArea() {
		_mediaViewScrollPane = new JScrollPane();
		_mediaViewScrollPane.setBorder(BorderFactory.createTitledBorder(null,
				"Selected media", TitledBorder.TOP,
				TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.BOLD,
						14)));
		_mediaViewScrollPane.setBackground(Color.WHITE);
		_mediaViewScrollPane.getHorizontalScrollBar().setBackground(Color.gray);
		_mediaViewScrollPane.getVerticalScrollBar().setBackground(Color.gray);

		_mediaViewArea = new JTextArea();
		_mediaViewArea.setBackground(Color.WHITE);
		_mediaViewArea.setEditable(false);
		_mediaViewArea.setFont(new Font("SansSerif", Font.BOLD, 14));
		//_mediaViewArea.setPreferredSize(new Dimension(300, 400));
		
		_mediaViewScrollPane.setViewportView(_mediaViewArea);
		_mediaViewScrollPane.setPreferredSize(new Dimension(300,400));

		return _mediaViewScrollPane;
	}
	
	/**Update the scrollPane, so it focuses again
	 * 
	 */
	public void updateScrollPane(){
		_mediaViewScrollPane.getVerticalScrollBar().setValue(0);
		//mediaViewScrollPane.validate();
	}

}
