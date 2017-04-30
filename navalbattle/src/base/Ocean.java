package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import abstracts.Ship;
import models.AircraftCarrier;

public final class Ocean {	
	private static final int X_SIZE = 10;
	private static final int Y_SIZE = 10;
	private static List<Coordinate> map;
	private static char teste = '\u2592';
	private static final String OCEAN_CHAR = "[ ]  ";
	private static final String SHIP_CHAR = "[X]  ";
	private static String[] HEADER_HORIZONTAL = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private static char[] HEADER_VERTICAL = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
	private static final Ocean INSTANCE = new Ocean();
	private static List<Ship> fleet;
	
	private Ocean() {
		map = new ArrayList<>();
		fleet = new ArrayList<>();
		initMap();
		initFleet();
	}
	
	private void initMap() {
		for (int x = 0; x < X_SIZE; x++) {
			for (int y = 0; y < Y_SIZE; y++) {
				map.add(new Coordinate(x, y));
			}
		}
	}
	
	private void initFleet() {
		fleet.add(AircraftCarrier.createShip());
		
	}
	
	public static Ocean getInstance() {
		return INSTANCE;
	}
	
	public static void drawOcean() {
		for (int y = 0; y <= Y_SIZE; y++) {
			if(y != Y_SIZE)
				System.out.print(HEADER_VERTICAL[y] + "  ");
			
			for (int x = 0; x <= X_SIZE; x++) {
				if(y == Y_SIZE) {
					if(x != 0)
						System.out.print(HEADER_HORIZONTAL[x-1] + "    ");
					else {
						System.out.print("    ");
					}
						
				}
				else if(x != X_SIZE) {
					System.out.print(OCEAN_CHAR);
				}
					
			}
			System.out.println("\n");
		}
	}
	
	public static void drawOceanWithShips() {
		for (int y = 0; y <= Y_SIZE; y++) {
			if(y != Y_SIZE)
				System.out.print(HEADER_VERTICAL[y] + "  ");
			
			for (int x = 0; x <= X_SIZE; x++) {
				if(y == Y_SIZE) {
					if(x != 0)
						System.out.print(HEADER_HORIZONTAL[x-1] + "    ");
					else {
						System.out.print("    ");
					}
				}
				else if(x != X_SIZE) {
					Coordinate currentCoordinate = new Coordinate(x + 1, y + 1);
					
					boolean isShipCoordinate = false;
					
					for (Ship ship : fleet) {
						if(ship.getShipCoordinates().contains(currentCoordinate))
							isShipCoordinate = true;
					}
					
					if(isShipCoordinate)
						System.out.print(SHIP_CHAR);
					else
						System.out.print(OCEAN_CHAR);
				}
					
			}
			System.out.println("\n");
		}
	}
	
	public static void drawCleanOcean() {
		System.out.println("***************************************************");
		System.out.println("           BEM-VINDO AO BATALHA NAVAL!");
		System.out.println("***************************************************\n\n");
		
//		drawOcean();
		drawOceanWithShips();
	}
}
