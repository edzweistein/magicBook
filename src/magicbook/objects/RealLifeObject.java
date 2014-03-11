package magicbook.objects;
import java.awt.image.BufferedImage;

import magicbook.magiccards.MagicCardLayout;


/**This class represents real world objects that can trigger some magic events
 * 
 * WORK IN PROGRESS
 * 
 * @author edzweistein
 *
 */
public class RealLifeObject extends Medium{
	
	
	public RealLifeObject() {
		/*TODO: hier wird ein fehler passieren!!! 15.06.2012 */
		super(Type.REALLIFEOBJECT);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void doMagic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MagicCardLayout getMagicCardLayout() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BufferedImage getCoverArt() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static Type getType() {
		return Type.REALLIFEOBJECT;
	}

	@Override
	public String getFolder() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public String getStringRepresentation()
    {
        String result = this.TYPE + " \n" +
                        _title + " \n";
        return result;
    }

	@Override
	public Type getMediaType() {
		return getType();
	}

}
