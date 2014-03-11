package magicbook.material;


/**
 * Represents an Observer Object. All Class which like to act like an( e.g. receive change notification)
 * should implement this interface
 * 
 * @author edzweistein
 *
 */
public interface Observer {

	/**
	 * Is called when something has changed in the Observable
	 */
	public void reactOnChanges(Observable observable);
}
