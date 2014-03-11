package magicbook;
import java.util.List;

import magicbook.gui.MagicBookUI;
import magicbook.gui.addmedia.AddMediumTool;
import magicbook.gui.addmedia.MediumSpecificQuestionerTool;
import magicbook.gui.viewmedia.MediumListTool;
import magicbook.gui.viewmedia.MediumViewTool;
import magicbook.material.Observable;
import magicbook.material.Observer;
import magicbook.objects.Medium;

/**
 * This class starts the UI and registers interaction with the UI.
 * 
 * @author Niels
 *
 */
public class MagicBookTool implements Observer
{
    MagicBookUI _ui ;
    
    private AddMediumTool _addmediumtool;
    private MediumViewTool _mediumviewtool;
    private MediumListTool _mediumlisttool;
    
    private MediumSpecificQuestionerTool _mediumspecificquestionertool;
    
    public MagicBookTool()
    {
        _addmediumtool = new AddMediumTool();
        _addmediumtool.addObserver(this);
        _mediumviewtool = new MediumViewTool();
        _mediumlisttool = new MediumListTool();
        
        registerUIActions();
    }
    
    /**
     * Close the main application window / frame.
     */
    public void closeWindow(){
    	_ui.getFrame().dispose();
    	
    }
    
    @Override
    public void reactOnChanges(Observable observable)
    {
        if(observable instanceof AddMediumTool)
        {
            AddMediumTool addmediumtool = (AddMediumTool) observable;
            _mediumspecificquestionertool = addmediumtool.getMediumSpecQuestTool();
            
            _mediumspecificquestionertool.showWindow();
            _mediumspecificquestionertool.addObserver(this);
        }
        else if(observable instanceof MediumSpecificQuestionerTool)
        {
            //resets the AddMediumTool, to enable adding more media
            _addmediumtool.reset();
            
            //updates the medialist in table, due to possible changes in database
            _mediumlisttool.updateMediaInTable();
        }
        
    }
    
    /**
     * Initializes the UI for magicBook-application.
     */
    private void registerUIActions()
    {
        _ui = new MagicBookUI(_addmediumtool.getUIPanel(), 
                _mediumviewtool.getUIPanel(), _mediumlisttool.getUIPanel());
        
        registerMediaViewAction();
    }
    

    /**
     * Registers action when user select media in medialist table.
     */
    private void registerMediaViewAction()
    {
        _mediumlisttool.addObserver(new Observer()
        {
            @Override
            public void reactOnChanges(Observable observable)
            {
               showSelectedMedia();                
            }
        });
    }
    
    /**
     * Sets the media for the 'View media' panel depending on user selection.
     * 
     */
    private void showSelectedMedia()
    {
        List<Medium> selectedmedia = _mediumlisttool.getSelectedMedia();
        
        _mediumviewtool.setMedia(selectedmedia);
    }
}
