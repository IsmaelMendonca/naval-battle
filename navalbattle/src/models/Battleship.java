package models;

import java.util.List;

import abstracts.Ship;
import base.Coordinate;
import enums.ShipType;
import interfaces.IOceanObserver;
import interfaces.IShotObserver;

public class Battleship extends Ship implements IShotObserver {
	public Battleship(Coordinate startPosition) {
		super(ShipType.FOUR_SIZE_SHIP, startPosition);
	}
	
	public static Battleship createShip(List<Coordinate> unavailablePositions) {
		Coordinate startPosition = getStartCoordinate();
		int direction = getRandomNumber(true);
		boolean needsAnotherStartPosition = true;
		boolean couldWrite = false;
		Battleship bs = new Battleship(startPosition);
		
		while(needsAnotherStartPosition) {
			
			boolean canRight = startPosition.getPositionX() + 3 <= 10;
			boolean canLeft = startPosition.getPositionX() - 3 >= 1;
			boolean canBottom = startPosition.getPositionY() + 3 <= 10;
			boolean canTop =  startPosition.getPositionY() - 3 >= 1;
			
			if(direction == RIGHT) {
				if(canRight) {
					bs.getShipCoordinates().add(startPosition);
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()));
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+2, startPosition.getPositionY()));
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+3, startPosition.getPositionY()));
					
					couldWrite |= true;
				} else {
					direction = LEFT;
				}
			} 
			if(direction == LEFT) {
				if(canLeft) {
					bs.getShipCoordinates().add(startPosition);
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()));
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-2, startPosition.getPositionY()));
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-3, startPosition.getPositionY()));
					
					couldWrite |= true;
				} else {
					direction = TOP;
				}
			}
			if(direction == TOP) {
				if(canTop) {
					bs.getShipCoordinates().add(startPosition);
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()-1));
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()-2));
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()-3));
					
					couldWrite |= true;
				} else {
					direction = BOTTOM;
				}
			}
			if(direction == BOTTOM) {
				if(canBottom) {
					bs.getShipCoordinates().add(startPosition);
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()+1));
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()+2));
					bs.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()+3));
					
					couldWrite |= true;
				}
			}
			
			if(!couldWrite) {
				startPosition = getStartCoordinate();
				bs.setStartPosition(startPosition);
				bs.getShipCoordinates().clear();
				needsAnotherStartPosition = true;
				couldWrite = false;
				continue;
			}
			
			for (Coordinate coordinate : bs.getShipCoordinates()) {
				needsAnotherStartPosition = false;
				
				if(unavailablePositions.contains(coordinate)) {
					startPosition = getStartCoordinate();
					bs.setStartPosition(startPosition);
					bs.getShipCoordinates().clear();
					needsAnotherStartPosition = true;
					couldWrite = false;
					break;
				}
			}
		}
		return bs;
	}
	
	@Override
	public void checkShot(Coordinate coordinate) {
		if(shipCoordinates.contains(coordinate) && !reachedCoordinates.contains(coordinate)) {
			reachedCoordinates.add(coordinate);
			
			if(reachedCoordinates.size() == shipCoordinates.size()) {
				isDestroyed = true;
				System.out.println("\nVoce esta quase dominando este oceano, voce destruiu o meu melhor e unico navio de quatro posicoes, o navio de guerra!");
			} else {
				System.out.println("\nParabens voce acertou o meu navio de guerra de quatro posicoes!");
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
