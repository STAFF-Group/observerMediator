import java.util.List;
import java.util.ArrayList;

public abstract class Observable {

	private List<Observer> observers = new ArrayList<Observer>();
	private String info;
	
	public void addObserver(Observer o) {
		this.observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}
	
	public void updateInfo(String info) {
		this.info = info;
		this.observers.forEach(observer -> observer.update(info));
	}

}
