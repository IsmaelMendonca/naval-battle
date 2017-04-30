package models;

import abstracts.Ship;
import base.Coordinate;
import enums.ShipType;
import interfaces.IShip;

public class Seaplane extends Ship implements IShip<Seaplane>{
	public Seaplane(Coordinate startPosition) {
		super(ShipType.ONE_SIZE_SHIP, startPosition);
	}
}
