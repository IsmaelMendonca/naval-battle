package models;

import java.util.List;

import abstracts.Ship;
import base.Coordinate;
import enums.ShipType;
import interfaces.IOceanObserver;
import interfaces.IShotObserver;

public class Cruiser extends Ship implements IShotObserver {
	public Cruiser(Coordinate startPosition) {
		super(ShipType.THREE_SIZE_SHIP, startPosition);
	}
	
	public static Cruiser createShip(List<Coordinate> unavailablePositions) {
		Coordinate startPosition = getStartCoordinate();
		int direction = getRandomNumber(true);
		boolean needsAnotherStartPosition = true;
		boolean couldWrite = false;
		Cruiser cr = new Cruiser(startPosition);
		
		while(needsAnotherStartPosition) {
			
			boolean canRight = startPosition.getPositionX() + 2 <= 10;
			boolean canLeft = startPosition.getPositionX() - 2 >= 1;
			boolean canBottom = startPosition.getPositionY() + 2 <= 10;
			boolean canTop =  startPosition.getPositionY() - 2 >= 1;
			
			if(direction == RIGHT) {
				if(canRight) {
					cr.getShipCoordinates().add(startPosition);
					cr.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()));
					cr.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+2, startPosition.getPositionY()));
					
					couldWrite |= true;
				} else {
					direction = LEFT;
				}
			} 
			if(direction == LEFT) {
				if(canLeft) {
					cr.getShipCoordinates().add(startPosition);
					cr.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()));
					cr.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-2, startPosition.getPositionY()));
					
					couldWrite |= true;
				} else {
					direction = TOP;
				}
			}
			if(direction == TOP) {
				if(canTop) {
					cr.getShipCoordinates().add(startPosition);
					cr.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()-1));
					cr.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()-2));
					
					couldWrite |= true;
				} else {
					direction = BOTTOM;
				}
			}
			if(direction == BOTTOM) {
				if(canBottom) {
					cr.getShipCoordinates().add(startPosition);
					cr.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()+1));
					cr.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()+2));
					
					couldWrite |= true;
				}
			}
			
			if(!couldWrite) {
				startPosition = getStartCoordinate();
				cr.setStartPosition(startPosition);
				cr.getShipCoordinates().clear();
				needsAnotherStartPosition = true;
				couldWrite = false;
				continue;
			}
			
			for (Coordinate coordinate : cr.getShipCoordinates()) {
				needsAnotherStartPosition = false;
				
				if(unavailablePositions.contains(coordinate)) {
					startPosition = getStartCoordinate();
					cr.setStartPosition(startPosition);
					cr.getShipCoordinates().clear();
					needsAnotherStartPosition = true;
					couldWrite = false;
					break;
				}
			}
		}
		return cr;
	}
	
	@Override
	public void checkShot(Coordinate coordinate) {
		if(shipCoordinates.contains(coordinate) && !reachedCoordinates.contains(coordinate)) {
			reachedCoordinates.add(coordinate);
			
			if(reachedCoordinates.size() == shipCoordinates.size()) {
				isDestroyed = true;
				System.out.println("\nVoce e realmente fantastico, voce destruiu um dos meus cruzadores de tres posicoes!");
			} else {
				System.out.println("\nParabens voce acertou um dos meus cruzadores de tres posicoes!");
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
