package models;

import java.util.List;

import abstracts.Ship;
import base.Coordinate;
import enums.ShipType;
import interfaces.IOceanObserver;
import interfaces.IShotObserver;

public class Seaplane extends Ship implements IShotObserver {
	public Seaplane(Coordinate startPosition) {
		super(ShipType.ONE_SIZE_SHIP, startPosition);
	}
	
	public static Seaplane createShip(List<Coordinate> unavailablePositions) {
		Coordinate startPosition = getStartCoordinate();
		int direction = getRandomNumber(true);
		boolean needsAnotherStartPosition = true;
		boolean couldWrite = false;
		Seaplane sp = new Seaplane(startPosition);
		
		while(needsAnotherStartPosition) {
			
			boolean canRight = startPosition.getPositionX() + 1 <= 10;
			boolean canLeft = startPosition.getPositionX() - 1 >= 1;
			boolean canBottom = startPosition.getPositionY() + 1 <= 10;
			boolean canTop =  startPosition.getPositionY() - 1 >= 1;
			
			if(direction == RIGHT) {
				if(canRight) {
					sp.getShipCoordinates().add(startPosition);
					sp.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()));
					
					couldWrite |= true;
				} else {
					direction = LEFT;
				}
			} 
			if(direction == LEFT) {
				if(canLeft) {
					sp.getShipCoordinates().add(startPosition);
					sp.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()));
					
					couldWrite |= true;
				} else {
					direction = TOP;
				}
			}
			if(direction == TOP) {
				if(canTop) {
					sp.getShipCoordinates().add(startPosition);
					sp.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()-1));
					
					couldWrite |= true;
				} else {
					direction = BOTTOM;
				}
			}
			if(direction == BOTTOM) {
				if(canBottom) {
					sp.getShipCoordinates().add(startPosition);
					sp.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()+1));
					
					couldWrite |= true;
				}
			}
			
			if(!couldWrite) {
				startPosition = getStartCoordinate();
				sp.setStartPosition(startPosition);
				sp.getShipCoordinates().clear();
				needsAnotherStartPosition = true;
				couldWrite = false;
				continue;
			}
			
			for (Coordinate coordinate : sp.getShipCoordinates()) {
				needsAnotherStartPosition = false;
				
				if(unavailablePositions.contains(coordinate)) {
					startPosition = getStartCoordinate();
					sp.setStartPosition(startPosition);
					sp.getShipCoordinates().clear();
					needsAnotherStartPosition = true;
					couldWrite = false;
					break;
				}
			}
		}
		return sp;
	}
	
	@Override
	public void checkShot(Coordinate coordinate) {
		if(shipCoordinates.contains(coordinate) && !reachedCoordinates.contains(coordinate)) {
			reachedCoordinates.add(coordinate);
			if(reachedCoordinates.size() == shipCoordinates.size()) {
				isDestroyed = true;
				System.out.println("\nParabens voce destruiu um dos meus hidroavioes de duas posicoes!");
			} else {
				System.out.println("\nParabens voce acertou um dos meus hidroavioes de duas posicoes!");
			}
			
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
