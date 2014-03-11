package magicbook.magiccards;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class PrintItemText extends PrintItem {

	private String _text;
	private Font _font;
	public PrintItemText(Point startPoint, Dimension size,String text,Font font) {
		super(startPoint, size);
		_text = text;
		_font = font;
	}
	
	/**
	 * Returns the text  of tis printitem.
	 * 
	 * @return text as string
	 */
	public String getText(){
		return _text;
	}
	/**
	 * 
	 */
	@Override
	public void drawOn(Graphics2D g) {
		/*TODO: testen!  22.05.2012*/
		g.setFont(_font);
		g.drawString(_text, _startPoint.x, _startPoint.y);
	}

}
