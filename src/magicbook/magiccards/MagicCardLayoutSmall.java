package magicbook.magiccards;

import java.awt.Dimension;
import java.awt.Point;

import magicbook.objects.Medium;

public class MagicCardLayoutSmall extends MagicCardLayout {

	public MagicCardLayoutSmall(Medium m) {
		super(new Dimension(350, 200));
		// add coverart
		addPrintItem(new PrintItemImage(new Point(0, 0),
				new Dimension(200, 200), m.getCoverArt()));
		// add qr-code
		addPrintItem(new PrintItemImage(new Point(210, 0), new Dimension(100,
				100), m.createQRCode(100, 100)));
	}
}
