package magicbook.gui.addmedia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import magicbook.magiccards.MagicCardCreater;
import magicbook.magiccards.MagicCardCreaterZXING;
import magicbook.material.Observable;
import magicbook.objects.Medium;
import magicbook.objects.Music;
import magicbook.objects.Video;
import tools.bufferedimage.BufferedImageTool;
import tools.copyfiles.CopyBotTool;


/**
 * This abstract class provides information every "medium specific add-tool"
 * should have, because they will get the set of files and the coverart which
 * were collected by the addmediumtool.
 * 
 * @author edzweistein
 * 
 */
public abstract class MediumSpecificQuestionerTool extends Observable {

	protected HashSet<File> _files;
	protected File _coverArt;
	
	AddMediumSubToolUI _ui;


	/**
	 * Constructor
	 * 
	 * @param files
	 *            The set of files which were collected by the AddMediumTool
	 * @param coverArt
	 *            the coverart for this medium which was collected by the
	 *            AddMediumTool
	 */
	public MediumSpecificQuestionerTool(HashSet<File> files, File coverArt) {
		assert files != null : "Vorbedingung verletzt: files!=null";
		assert coverArt != null : "Vorbedingung verletzt: coverArt!=null";

		_files = files;
		_coverArt = coverArt;
	}

	/**
	 * This method copies the original files to the directory at directoryPath .
	 * 
	 * @param files The original files
	 * @param directoryPath where the files will be copied
	 * @return a List containing the copied files
	 * @throws Exception
	 * @require directoryPath != null
	 */
	protected List<File> copyFilesToDirectory(Set<File> files,
			String directoryPath) throws Exception {
		assert directoryPath != null : "Vorbedingung verletzt:  directoryPath!=null";

		return CopyBotTool.copyFiles(files, directoryPath);
	}

	/**
	 * Register listener for the save button in order to invoke the save
	 * procedure.
	 * 
	 */
	public void registerUIActions() {
		
		getUI().getSaveButton().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					reactOnSaveButton();
				}

			});
		}
	

	/**
	 * Opens the ui of this tool
	 */
	public void showWindow(){
	    _ui.showWindow();
	}

	/** Copies the media files and the choosen cover images in the correspondent directory.
	 * 
	 * 
	 * @param destinationFolderPath, path in the filesystem for media files
	 * @return list of files containing the recently saved files
	 * @throws Exception
	 */
	protected List<File> saveMediaFiles(String destinationFolderPath) throws Exception
	{
	  
    	    // copy files
            List<File> newFiles = copyFilesToDirectory(_files, destinationFolderPath);
            // copy coverart
            BufferedImageTool.saveImage(BufferedImageTool.loadImage(_coverArt),
                                destinationFolderPath + "cover");
            return newFiles;
	}
	
	/**
	 * Initiates the saving process for recently added media files.
	 * 
	 */
	protected abstract void reactOnSaveButton();
	
	/**
	 * Returns the ui of this AddMediumSubTool
	 * 
	 * @return ui of this tool
	 */
	public abstract AddMediumSubToolUI getUI();

	/**
	 * Saving process for media files.
	 * Outsourced to an seperate thread in order to avoid blockage of the main (ui) thread.
	 * 
	 * @author Niels
	 *
	 */
	class SaveThread implements Runnable
	{
	    private List<File> newfiles;
	    private String destinationPath ;
	    private Medium medium;
	    
	    public SaveThread(Medium m, String path)
	    {
	        destinationPath = path;
	        medium = m;
	    }
	    
        @Override
        public void run()
        {
            try
            {

                newfiles = saveMediaFiles(destinationPath);
                
                switch (medium.TYPE)
                {
                    case MUSIC:
                        String mtitle = medium.getTitle();
                        String interpret =  ((Music)medium).getInterpret();
                        int myear = ((Music) medium).getYear();
                                
                        //PlaylistTool.createSpiffPlaylist(newfiles, destinationPath,"magicBook music: "+mtitle+" - "+interpret+" - "+myear, interpret);
                        
                        MagicCardCreater mmagiccardCreater = new MagicCardCreaterZXING();
                        mmagiccardCreater.saveMagicCardToFile(medium, destinationPath
                                + mtitle + "-" + interpret + "_magiccard");
                        break;
                    case VIDEO:
                        String vtitle = medium.getTitle();
                        String regisseur = ((Video)medium).getRegie();
                        int vyear = ((Video) medium).getYear();
                        
                        //PlaylistTool.createSpiffPlaylist(newfiles, destinationPath, "magicBook video: "+ vtitle +" - "+regisseur+" - "+vyear, regisseur);
                        
                        MagicCardCreater vmagiccardCreater = new MagicCardCreaterZXING();
                        vmagiccardCreater.saveMagicCardToFile(medium, destinationPath
                                + vtitle + "-" + regisseur+ "_magiccard");
                        
                        break;
                    default:
                        break;
                }
                
                informAllObserversAboutChanges();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
	    
	}
}
