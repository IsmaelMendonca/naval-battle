package models;

import abstracts.Ship;
import base.Coordinate;
import enums.ShipType;
import interfaces.IShip;

public class Cruiser extends Ship implements IShip<Cruiser> {
	public Cruiser(Coordinate startPosition) {
		super(ShipType.THREE_SIZE_SHIP, startPosition);
	}
}
