package interfaces;

public interface IShotSubject {
	public void registerObserver(IShotObserver observer);
	public void removeObserver(IShotObserver observer);
	public void notifyObservers();
}
