package models;

import java.util.List;

import abstracts.Ship;
import base.Coordinate;
import enums.ShipType;
import interfaces.IOceanObserver;
import interfaces.IShotObserver;

public class Submarine extends Ship implements IShotObserver {
	public Submarine(Coordinate startPosition) {
		super(ShipType.TWO_SIZE_SHIP, startPosition);
	}
	
	public static Submarine createShip(List<Coordinate> unavailablePositions) {
		Coordinate startPosition = getStartCoordinate();
		boolean needsAnotherStartPosition = true;
		Submarine sb = new Submarine(startPosition);
		
		while(needsAnotherStartPosition) {
			sb.getShipCoordinates().add(startPosition);
			
			if(unavailablePositions.contains(startPosition)) {
				startPosition = getStartCoordinate();
				sb.setStartPosition(startPosition);
				sb.getShipCoordinates().clear();
				needsAnotherStartPosition = true;
				continue;
			}
			
			needsAnotherStartPosition = false;
		}
		
		return sb;
	}

	@Override
	public void checkShot(Coordinate coordinate) {
		if(shipCoordinates.contains(coordinate) && !reachedCoordinates.contains(coordinate)) {
			reachedCoordinates.add(coordinate);
			isDestroyed = true;
			System.out.println("\nParabens voce destruiu um dos meus submarino de uma posicao!");
			notifyObservers();
		}
	}

	@Override
	public void registerObserver(IOceanObserver observer) {
		if(!oceanObservers.contains(observer))
			oceanObservers.add(observer);
	}

	@Override
	public void removeObserver(IOceanObserver observer) {
		if(oceanObservers.contains(observer))
			oceanObservers.add(observer);
	}

	@Override
	public void notifyObservers() {
		for (IOceanObserver observer : oceanObservers) {
			observer.updateOcean();
		}
	}
}
