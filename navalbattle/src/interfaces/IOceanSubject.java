package interfaces;

public interface IOceanSubject {
	public void registerObserver(IOceanObserver observer);
	public void removeObserver(IOceanObserver observer);
	public void notifyObservers();
}
