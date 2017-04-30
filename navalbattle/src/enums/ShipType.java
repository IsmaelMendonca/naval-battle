package enums;

public enum ShipType {
	FIVE_SIZE_SHIP(5, true), 
	FOUR_SIZE_SHIP(4, false), 
	THREE_SIZE_SHIP(3, false), 
	TWO_SIZE_SHIP(2, false), 
	ONE_SIZE_SHIP(1, false);
	
	private ShipType(int size, boolean isTShip) {
		this.size = size;
		this.isTShip = isTShip;
	}
	
	private final int size;
	private final boolean isTShip;
	
	public int getSize() {
		return size;
	}
		
	public boolean isTShip() {
		return isTShip;
	}
}
