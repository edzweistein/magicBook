package magicbook.gui.viewmedia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class ImageDrawCommand implements Drawable{

	private int _x,_y,_breite,_hoehe;
	private BufferedImage _image;
	public ImageDrawCommand(int x,int y,int breite,int hoehe,BufferedImage img){
		this._x=x;
		this._y=y;
		this._breite=breite;
		this._hoehe=hoehe;
		this._image=img;
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(_image, _x, _y, _breite, _hoehe, null);
	}

}
