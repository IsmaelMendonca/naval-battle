package models;

import abstracts.Ship;
import base.Coordinate;
import enums.ShipType;
import interfaces.IOceanObserver;
import interfaces.IShotObserver;

public class AircraftCarrier extends Ship implements IShotObserver {
	public AircraftCarrier(Coordinate startPosition) {
		super(ShipType.FIVE_SIZE_SHIP, startPosition);
	}

	public static AircraftCarrier createShip() {
		int direction = getRandomNumber(true);
		Coordinate startPosition = getStartCoordinate();
		AircraftCarrier ac = null;
		
		if(direction == TOP) {
			if(startPosition.getPositionY() - 2 < 1) {
				direction = RIGHT;
				
				if(startPosition.getPositionX() + 2  > 10) {
					direction = BOTTOM;
					
					if(startPosition.getPositionY() + 2 > 10) {
						direction = LEFT;
						
						if(startPosition.getPositionX() - 2 < 1) {
							return createShip();
						} else {
							ac = generateLeftShip(startPosition);
						}
					} else {
						ac = generateBottomShip(startPosition);
					}
				} else {
					ac = generateRightShip(startPosition);
				}
			} else {
				ac = generateTopShip(startPosition);
			}
		}
		
		if(direction == RIGHT) {	
			if(startPosition.getPositionX() + 2  > 10) {
				direction = BOTTOM;
				
				if(startPosition.getPositionY() + 2 > 10) {
					direction = LEFT;
					
					if(startPosition.getPositionX() - 2 < 1) {
						direction = TOP;
						
						if(startPosition.getPositionY() - 2 < 1) {
							return createShip();
						} else {
							ac = generateTopShip(startPosition);
						}
					} else {
						ac = generateLeftShip(startPosition);
					}
				} else {
					ac = generateBottomShip(startPosition);
				}
			} else {
				ac = generateRightShip(startPosition);
			}
		}
		
		if(direction == BOTTOM) {
			if(startPosition.getPositionY() + 2 > 10) {
				direction = LEFT;
				
				if(startPosition.getPositionX() - 2 < 1) {
					direction = TOP;
					
					if(startPosition.getPositionY() - 2 < 1) {
						direction = RIGHT;
						
						if(startPosition.getPositionX() + 2  > 10) {
							return createShip();
						} else {
							ac = generateRightShip(startPosition);
						}
					} else {
						ac = generateTopShip(startPosition);
					}
				} else {
					ac = generateLeftShip(startPosition);
				}
			} else {
				ac = generateBottomShip(startPosition);
			}	
		}
		
		if(direction == LEFT) {
				if(startPosition.getPositionX() - 2 < 1) {
					direction = TOP;
					
					if(startPosition.getPositionY() - 2 < 1) {
						direction = RIGHT;
						
						if(startPosition.getPositionX() + 2  > 10) {
							direction = BOTTOM;
							
							if(startPosition.getPositionY() + 2 > 10) {
								return createShip();
							} else {
								ac = generateBottomShip(startPosition);
							}	
						} else {
							ac = generateRightShip(startPosition);
						}
					} else {
						ac = generateTopShip(startPosition);
					}
				} else {
					ac = generateLeftShip(startPosition);
				}
		}
		
		return ac;
	}

	private static AircraftCarrier generateTopShip(Coordinate startPosition) {
		AircraftCarrier ac;
		ac = new AircraftCarrier(startPosition);
		
		if(startPosition.getPositionX() - 2 < 1) {
			ac.getShipCoordinates().add(startPosition);
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()-1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()-2));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+2, startPosition.getPositionY()));
		} else {	
			ac.getShipCoordinates().add(startPosition);
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()-1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()-2));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-2, startPosition.getPositionY()));
		}
		
		return ac;
	}
	
	private static AircraftCarrier generateRightShip(Coordinate startPosition) {
		AircraftCarrier ac;
		ac = new AircraftCarrier(startPosition);
		
		if(startPosition.getPositionY() - 2 < 1) {
			ac.getShipCoordinates().add(startPosition);
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()+1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()+2));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()+1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+2, startPosition.getPositionY()+1));	
		} else {	
			ac.getShipCoordinates().add(startPosition);
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()-1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()-2));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()-1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+2, startPosition.getPositionY()-1));
		}
		
		return ac;
	}
	
	private static AircraftCarrier generateBottomShip(Coordinate startPosition) {
		AircraftCarrier ac;
		ac = new AircraftCarrier(startPosition);
		
		if(startPosition.getPositionX() - 2 < 1) {
			ac.getShipCoordinates().add(startPosition);
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()+1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+1, startPosition.getPositionY()+2));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()+2, startPosition.getPositionY()));
		} else {	
			ac.getShipCoordinates().add(startPosition);
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()+1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()+2));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-2, startPosition.getPositionY()));
		}
		
		return ac;
	}
	
	private static AircraftCarrier generateLeftShip(Coordinate startPosition) {
		AircraftCarrier ac;
		ac = new AircraftCarrier(startPosition);
		
		if(startPosition.getPositionY() - 2 < 1) {
			ac.getShipCoordinates().add(startPosition);
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()+1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()+2));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()+1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-2, startPosition.getPositionY()+1));	
		} else {	
			ac.getShipCoordinates().add(startPosition);
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()-1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX(), startPosition.getPositionY()-2));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-1, startPosition.getPositionY()-1));
			ac.getShipCoordinates().add(new Coordinate(startPosition.getPositionX()-2, startPosition.getPositionY()-1));
		}
		
		return ac;
	}
	
	@Override
	public void checkShot(Coordinate coordinate) {
		if(shipCoordinates.contains(coordinate) && !reachedCoordinates.contains(coordinate)) {
			reachedCoordinates.add(coordinate);
		
			if(reachedCoordinates.size() == shipCoordinates.size()) {
				isDestroyed = true;
				System.out.println("\nMas que pancada! Voce e o mehor atirador que conheco! acabou de destruir o meu unico porta avioes de cinco posicoes!");
			} else {
				System.out.println("\nParabens voce acertou o meu porta avioes de cinco posicoes, continue assim!");
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
