package models;

import abstracts.Ship;
import base.Coordinate;
import enums.ShipType;
import interfaces.IShip;

public class Submarine extends Ship implements IShip<Submarine> {
	public Submarine(Coordinate startPosition) {
		super(ShipType.TWO_SIZE_SHIP, startPosition);
	}
}
