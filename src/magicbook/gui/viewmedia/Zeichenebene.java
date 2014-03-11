package magicbook.gui.viewmedia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JComponent;


//import java.awt.Point;
//import java.awt.Polygon;

/**
 * 
 * 
 * @author Jonas Schmid, Niels Petersen
 *
 */
public class Zeichenebene extends JComponent {

	private static final long serialVersionUID = 1L;
	//int breite, hoehe sind schon im JComponent als width und height vorhanden
	private Color _bgcolor;
	private BufferedImage _content;
	private Graphics _graphics;
	private List<Drawable> _drawQueue = new ArrayList<Drawable>();
	
	private Stack<Drawable> _undonedDrawQueue = new Stack<Drawable>();

	/**Constructor
	 * 
	 * @param width
	 * @param height
	 */
	public Zeichenebene(int width, int height) {

		_content = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		_graphics = getDrawingAreaGraphics();
		// white background

		_graphics.setColor(Color.white);
		_graphics.fillRect(0, 0, width, height);
		setSize(width, height);

		setBackground(Color.WHITE);
	}
	

	/** Adds an DrawCommand (Drawable) to the @_drawQueue and repaints the drawingarea so the just added DrawCommand will be executed as well
	 * @param d the DrawCommand
	 */
	public void draw(Drawable d) {
		_drawQueue.add(d);
		paintDrawingArea();
	}

	/**
	 * @return
	 */
	public int getBreite() {
		return getWidth();
	}

	/**
	 * @return
	 */
	public int getHoehe() {
		return getHeight();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	public Dimension getPreferredSize() {
		return new Dimension(getBreite(), getHoehe());
	}

	public List<Drawable> getDrawCommandList(){
		return _drawQueue;
	}
	/**
	 * @return
	 */
	public Color getBGColor() {
		return _bgcolor;
	}

	/**
	 * @param farbe
	 */
	public void setBGColor(Color farbe) {
		_bgcolor = farbe;
		setBackground(farbe);
	}

	/**Returns the Graphics from the DrawingArea object, so you can paint on it from somewhere else
	 * @return
	 */
	public Graphics getDrawingAreaGraphics() {
		return _content.getGraphics();
	}

	/**Returns the DrawingArea's content as an Image
	 * @return
	 */
	public Image getDrawing() {
		return _content;
	}

	/**Generates a new buffer and draws all commands from the @_drawQueue on this buffer,
	 * then repaints (= shows the buffer content on the drawingarea) 
	 * 
	 */
	public void paintDrawingArea(){
		_content = generateDrawingBuffer();
		repaint();
//		JOptionPane.showMessageDialog(null,"Anzahl Commands: "+_drawQueue.size());
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(_content, 0, 0, this);
		
	}

	/**Generates a new drawingbuffer, that means a new background image
	 * is created and all the drawcommands that are in the queue
	 * are drawn on top of this new image
	 * @return
	 */
	private BufferedImage generateDrawingBuffer() {

		int breite = getBreite();
		int hoehe = getHoehe();
		
		BufferedImage content = new BufferedImage(breite, hoehe,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = content.getGraphics();
		// Draw Background
		g.setColor(_bgcolor);
		g.fillRect(0, 0, breite, hoehe);
		//System.out.println("Anzahl der DrawCommands: "+_drawQueue.size());
		if (_drawQueue.size() > 0) {
			for (Drawable d : _drawQueue) {
				d.draw(g);
			}
		}
		return content;
	}

	public void undo()
	{
	    if(!_drawQueue.isEmpty())
	    {
    	    Drawable last = _drawQueue.remove(_drawQueue.size()-1);
    	    _undonedDrawQueue.add(last);
    	    
    		paintDrawingArea();
	    }
	}
	
	/**
	 * Redo the last undoned draw command, if available.
	 * 
	 * Wir innerhalb von DrawGUI nur aufgerufen, wenn die die Variable undonestatus true zurückliefert. 
	 * 
	 */
	public void redo()
	{
	    if(!_undonedDrawQueue.isEmpty())
	    {
    	    Drawable last = _undonedDrawQueue.pop();
    	    _drawQueue.add(last);
    	    paintDrawingArea();
	    }
	}
	
	public boolean isRedopossbile()
	{
		return _undonedDrawQueue.size() > 0;
	}
	
	/**Delete all the drawingcommands,
	 * clear the drawing area
	 * 
	 */
	public void clear() {
		_drawQueue.clear();
		paintDrawingArea();
	}


	/**
	 * Displays the image on the drawingarea, fits the drawing area to the size
	 * of the image
	 * 
	 * @param img
	 */
	public void showImage(BufferedImage img) {
		if (img != null) {
			_content = img;
			setSize(img.getWidth(), img.getHeight());
			
			repaint();
		}
	}

}
