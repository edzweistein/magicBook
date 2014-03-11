package magicbook.magiccards;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

/**A MagicCardLayout describes how the magiccard should look like.
 * This can be done by setting size of the magiccard and adding PrintItems (which can be e.g. images like the qr-code or the coverart or text).
 * @author jonas
 * 
 */
public abstract class MagicCardLayout {

	private Dimension _size;
	private ArrayList<PrintItem> _items;

	/**
	 * @param size
	 * @param items
	 */
	public MagicCardLayout(Dimension size, ArrayList<PrintItem> items) {
		_size = size;
		for (PrintItem item : items) {
			if (fitsIntoLayout(item)) {
				_items.add(item);
			}
		}
	}

	/**
	 * @param size size of this Layout
	 */
	public MagicCardLayout(Dimension size) {
		_size = size;
		_items = new ArrayList<PrintItem>();
	}

	/**
	 * Returns the dimension of the magiccard.
	 * 
	 * @return
	 */
	public Dimension getDimension() {
		return _size;
	}

	/**
	 * Returns the quantity of printitems.
	 * 
	 * @return
	 */
	public ArrayList<PrintItem> getItems() {
		return _items;
	}

	/**
	 * Adds an printitem to the list.
	 * 
	 * @param item to add
	 * @return true if item successfully added
	 */
	public boolean addPrintItem(PrintItem item) {
		if (fitsIntoLayout(item)) {
			_items.add(item);
			return true;
		}
		return false;
	}

	/**Check if the item is fully in the layout (not outside or crossing the borders). Might get changed
	 * 
	 * @param item
	 * @return
	 */
	/*TODO: change criteria for "fitting"  21.05.2012*/
	private boolean fitsIntoLayout(PrintItem item) {
		/* TODO: testen 21.05.2012 */
		Point start = item.getStartPoint();
		Dimension itemsize = item.getSize();
		return (start.x + itemsize.width <= _size.width)
				&& (start.y + itemsize.height <= _size.height);

	}
}
