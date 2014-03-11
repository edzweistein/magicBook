package magicbook.material;

import java.util.HashSet;


public abstract class Observable {

	HashSet<Observer> _observer = new HashSet<Observer>();
	
	/**Add an observer which will be informed about changes
	 * @param observer 
	 * @require observer != null
	 */
	public void addObserver(Observer observer){
		assert observer != null: "Precondition failed: observer != null";
		_observer.add(observer);
	}
	
	/**
	 * Says hey "Observer" something has changed!
	 */
	public void informAllObserversAboutChanges(){
		for(Observer observer : _observer){
			observer.reactOnChanges(this);
		}
	}
}
