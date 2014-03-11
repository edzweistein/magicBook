package tools;

import javax.swing.JPanel;

/**
 * Abstract class for tool-ui context.
 * 
 * @author Niels Petersen
 *
 */
public abstract class ToolUI
{
    protected JPanel _mainPanel;
    
    
    /**
     * Returns the main UI-Panel of this tool.
     * 
     * @return main panel of this tool
     */
    public JPanel getUIPanel()
    {
        return _mainPanel;
    }
    
    /**
     * Builds and returns the UI-Panel of this tool.
     * 
     * @return panel of this tool
     */
    public abstract JPanel createPanel();
}
