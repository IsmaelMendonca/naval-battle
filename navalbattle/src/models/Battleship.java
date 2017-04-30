package models;

import abstracts.Ship;
import base.Coordinate;
import enums.ShipType;
import interfaces.IShip;

public class Battleship extends Ship implements IShip<Battleship> {
	public Battleship(Coordinate startPosition) {
		super(ShipType.FOUR_SIZE_SHIP, startPosition);
	}
	
	public static Battleship createShip() {
		
	}
}
