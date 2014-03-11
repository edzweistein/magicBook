package magicbook.magiccards;

import java.util.List;

import magicbook.objects.Medium;

public interface MagicCardCreater {

	/**Saves a magiccard image file for the given medium to the path
	 * @param m
	 * @param filename The filename (+ path) where the image should be saved
	 */
	public void saveMagicCardToFile(Medium m,String filename);
	
	/**Prints the magicCard for this medium
	 * @param m
	 * @return
	 */
	public boolean printMagicCard(Medium m);
	
	/**Prints the magicCard for a list of mediums
	 * @param list
	 * @return
	 */
	public boolean printMagicCard(List<Medium> list);
	// example for printing http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/2d/printing/examples/HelloWorldPrinter.java
	
}
