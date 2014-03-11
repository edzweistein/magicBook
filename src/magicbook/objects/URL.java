package magicbook.objects;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.net.URI;

import magicbook.MagicBook;
import magicbook.magiccards.MagicCardLayout;

/**This class represents URL links e.g. Websites
 * 
 * WORK IN PROGRESS
 * 
 * @author edzweistein
 *
 */
public class URL extends Medium {

	private String _url;

	
	/**Constructor
	 * 
	 */
	public URL() {
		super(Type.URL);
	}
	
	public URL(String url,String title){
		super(Type.URL);
		this._title=title;
		this._url=url;
	}

	@Override
	public int getID() {
		return Math.abs(_url.hashCode());
	}

	@Override
	public void doMagic() {
		
		switch(MagicBook.OPTIONS.ACTION_URL){
		case 0:
				openURL();
				break;
		case 1: 
				//new InfoPopUpTool(this);
				break;
		}

	}

	/**Opens this URL in the standard browser on this pc
	 * 
	 */
	private void openURL() {
		if (_url != null) {
			try {
				URI uri = new URI(_url);
				Desktop.getDesktop().browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		return Type.URL;
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
                        _title + " \n" +
                        _url + " \n";
        return result;
    }

	@Override
	public Type getMediaType() {
		return getType();
	}

	/**Returns the url
	 * 
	 * @return url as string
	 */
	public String getURL() {
		return _url;
	}

	

}
