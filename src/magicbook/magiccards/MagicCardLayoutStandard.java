package magicbook.magiccards;

import java.awt.Dimension;
import java.awt.Point;

import magicbook.objects.Medium;

public class MagicCardLayoutStandard extends MagicCardLayout {

	public MagicCardLayoutStandard(Medium m) {
		
		super(new Dimension(700, 500));
		//add coverart
		addPrintItem(new PrintItemImage(new Point(0,0), new Dimension(500,500),m.getCoverArt()));
		//add qr-code
		addPrintItem(new PrintItemImage(new Point(500,10),new Dimension(200,200),m.createQRCode(200, 200)));
		//add title
		//addPrintItem(new PrintItemText(new Point(500,210), new Dimension(100,100), ""+m.getMediaType()+": "+m.getID(), Font.decode("Arial-Bold-12")));
		
	}

}
