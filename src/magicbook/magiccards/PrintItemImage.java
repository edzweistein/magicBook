package magicbook.magiccards;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class PrintItemImage extends PrintItem {
	
	private BufferedImage _image;
	
	public PrintItemImage(Point startPoint,Dimension size,BufferedImage img){
		super(startPoint,size);
		_image=img;
		
	}

	@Override
	public void drawOn(Graphics2D g){
		/*TODO: testen!*/
		if(_image==null){
			System.out.println("error while drawing coverart!");
		}
		g.drawImage(_image,_startPoint.x,_startPoint.y,_size.width,_size.height,null);
		
	}

}