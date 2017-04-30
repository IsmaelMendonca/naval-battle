package abstracts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import base.Coordinate;
import enums.ShipType;

public abstract class Ship {
	protected ShipType type;
	protected Coordinate startPosition;
	protected List<Coordinate> shipCoordinates;
	protected List<Coordinate> reachedCoordinates;
	protected boolean isDestroyed;
	
	protected static final int TOP = 0;
	protected static final int RIGHT = 1;
	protected static final int BOTTOM = 2;
	protected static final int LEFT = 3;
	
	public Ship(ShipType type, Coordinate startPosition) {
		super();
		this.type = type;
		this.startPosition = startPosition;
		this.reachedCoordinates = new ArrayList<>();
		this.shipCoordinates = new ArrayList<>();
		this.isDestroyed = false;
	}
	
	protected static Coordinate getStartCoordinate() {
		Coordinate startCoordinate = new Coordinate();
		startCoordinate.setPositionX(getRandomNumber(false));
		startCoordinate.setPositionY(getRandomNumber(false));
		return startCoordinate;
	}
	
	protected static int getRandomNumber(boolean isDirection) {
		Random generator = new Random();
		if(isDirection)
			return generator.nextInt(4);
		return generator.nextInt(10) + 1;
	}
	
	public ShipType getType() {
		return type;
	}
	public void setType(ShipType type) {
		this.type = type;
	}
	public Coordinate getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(Coordinate startPosition) {
		this.startPosition = startPosition;
	}
	public List<Coordinate> getReachedCoordinates() {
		return reachedCoordinates;
	}
	public void setReachedCoordinates(List<Coordinate> reachedCoordinates) {
		this.reachedCoordinates = reachedCoordinates;
	}
	public boolean isDestroyed() {
		return isDestroyed;
	}
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	public List<Coordinate> getShipCoordinates() {
		return shipCoordinates;
	}
	public void setShipCoordinates(List<Coordinate> shipCoordinates) {
		this.shipCoordinates = shipCoordinates;
	}
}
