package magicbook.objects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import magicbook.MagicBook;
import magicbook.magiccards.MagicCardLayout;

/**This class represents programs /software
 * 
 * WORK IN PROGRESS 
 * 
 * 
 * @author edzweistein
 *
 */
public class Program extends Medium {

	private String _programPath;
	private static String _OS;
	


	public Program(String programPath){
		super(Type.PROGRAM);
		assert programPath != null : "Vorbedingung verletzt: programPath != null";
		_programPath = programPath;
		_OS = System.getProperty("os.name");
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void doMagic() {
		switch(MagicBook.OPTIONS.ACTION_PROGRAM){
		case 0:
				//if this machine is running making bill gates happy?!
			if(isRunningWindows()){
				openProgramWindows();
			}else if(isRunningLinux()){
				openProgramLinux();
			}
				break;
		case 1:
				//new InfoPopUpTool(this);
				break;
		}

	}

	private void openProgramLinux() {
		/*TODO: implementieren  22.05.2012*/
		
	}

	private boolean openProgramWindows() {
		try {
			Runtime.getRuntime().exec(
					"rundll32 url.dll,FileProtocolHandler " + _programPath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	} 

	@Override
	public MagicCardLayout getMagicCardLayout() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean isRunningWindows(){
		return _OS.startsWith("Windows");
	}
	
	private boolean isRunningLinux(){
		return _OS.equals("Linux");
	}

	@Override
	public BufferedImage getCoverArt() {
		return null;
	}

	/**
	 * Returns the type for this program.
	 * @return Type.PROGRAM
	 */
	public static Type getType() {
		return Type.PROGRAM;
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
                        _OS + " \n" +
                        _programPath + "\n";
        return result;
    }

	@Override
	public Type getMediaType() {
		return getType();
	}

	
	

}
