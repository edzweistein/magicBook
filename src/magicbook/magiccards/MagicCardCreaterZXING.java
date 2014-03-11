package magicbook.magiccards;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import magicbook.objects.Medium;
import tools.bufferedimage.BufferedImageTool;

public class MagicCardCreaterZXING implements MagicCardCreater{

	
	/**
	 * @parameter medium The medium for which the MagicCard should be saved
	 * @parameter filename Where (path+filename) the magiccard should be saved
	 * @require MagicBook.OPTIONS must be initalized
	 */
	public void saveMagicCardToFile(Medium medium, String filename) {
	
		//assert MagicBook.OPTIONS!=null : "Vorbedingung verletzt: MagicBook.OPTIONS!=null";
		BufferedImage magicCard = createMagicCard(medium.getMagicCardLayout());
		BufferedImageTool.saveImage(magicCard, filename);
		
	}
	
	/**
	 * Creates MagicCard with the given layout.
	 * 
	 * @param layout of the magiccard
	 * @return image representing the magicCard
	 */
	public BufferedImage createMagicCard(MagicCardLayout layout){
		BufferedImage magicCard = new BufferedImage(layout.getDimension().width, layout.getDimension().height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = (Graphics2D) magicCard.getGraphics();
		//antialiasing on
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//fill background with white
		g.setColor(Color.white);
		g.fillRect(0, 0, layout.getDimension().width, layout.getDimension().height);
		
		for(PrintItem item : layout.getItems()){
			item.drawOn(g);
		}
		return magicCard;
	}

	/**
	 * 
	 */
	@Override
	public boolean printMagicCard(Medium m) {
		/*TODO: java drucken rauskriegen  21.05.2012*/		
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean printMagicCard(List<Medium> list) {
		/*TODO: implementieren
		switch(list.size()){
		
		}
		for(Medium m : list){
			
		}*/
		return false;
	}
	
	

}
