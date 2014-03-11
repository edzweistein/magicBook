package magicbook.objects;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import magicbook.magiccards.MagicCardLayout;

/**This class represents picture collections.
 * 
 * 
 * WORK IN PROGRESS
 * 
 * 
 * @author edzweistein
 *
 */
public class Pictures extends Medium {

	public Pictures() {
		super(Type.PICTURES);

	}

	@Override
	public int getID() {

		return 0;
	}

	@Override
	public void doMagic() {
		try {
			openFolder(new File(getFolder()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param path
	 * @throws IOException
	 */
	private void openFolder(File path) throws IOException {
		assert path != null : "Vorbedingung verletzt: path!=null";
		Desktop desktop = null;
		// Before more Desktop API is used, first check
		// whether the API is supported by this particular
		// virtual machine (VM) on this particular host.
		if (Desktop.isDesktopSupported()) {
			desktop = Desktop.getDesktop();

			try {
				desktop.open(path);
			} catch (IOException e) {
				throw e;
			}
		}
	}

	@Override
	public MagicCardLayout getMagicCardLayout() {
		return null;
	}

	@Override
	public BufferedImage getCoverArt() {
		return null;
	}

	/**
	 * Returns the type of this picture.
	 * 
	 * @return Type.PICTURES
	 */
	public static Type getType() {
		return Type.PICTURES;
	}

	@Override
	public String getFolder() {
		/* TODO: implement this */
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
