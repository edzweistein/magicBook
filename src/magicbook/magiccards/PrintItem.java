package magicbook.magiccards;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class PrintItem {

	protected Dimension _size;
	protected Point _startPoint;

	/**
	 * @param startPoint The Point containing the x and y coordinate of the upper left corner of this printItem
	 * @param size
	 */
	public PrintItem(Point startPoint, Dimension size) {
		_startPoint = startPoint;
		_size = size;
	}
	
	/**
	 * Draws the Printitem.
	 * 
	 * @param g 
	 */
	public abstract void drawOn(Graphics2D g);
	
	/**Getter for the size
	 * 
	 * @return size in pixel x pixel
	 */
	public Dimension getSize(){
		return _size;
	}
	
	/**Getter for the startpoint of this printitem
	 *
	 *@return start point
	 */
	public Point getStartPoint(){
		return _startPoint;
	}

}
