package base;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import abstracts.Ship;
import interfaces.IOceanObserver;
import interfaces.IShotObserver;
import interfaces.IShotSubject;
import models.AircraftCarrier;
import models.Battleship;
import models.Cruiser;
import models.Seaplane;
import models.Submarine;

public final class Ocean implements IShotSubject, IOceanObserver {	
	private static final Ocean INSTANCE = new Ocean();
	
	private static final int X_SIZE = 10;
	private static final int Y_SIZE = 10;
	private static final String OCEAN_CHAR = "[ ]  ";
	private static final String SHIP_CHAR = "[X]  ";
	private static final String[] HEADER_HORIZONTAL = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private static final char[] HEADER_VERTICAL = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
	
	private static List<Ship> fleet;
	private static List<Coordinate> unavailablePositions;
	private static List<IShotObserver> shotObservables;
	private static Coordinate currentCoordinate;
	private static boolean isWarOver = false;
	private static Scanner reader;
	
	private Ocean() {
		fleet = new ArrayList<>();
		unavailablePositions = new ArrayList<>();
		shotObservables = new ArrayList<>();
		reader = new Scanner(System.in);
		initFleet();
	}
	
	private void initFleet() {
		unavailablePositions.clear();
		
		//Adding one AircraftCarrier ship to fleet
		AircraftCarrier ac = AircraftCarrier.createShip();
		fleet.add(ac);
		shotObservables.add(ac);
		ac.registerObserver(this);
		unavailablePositions.addAll(ac.getShipCoordinates());
		
		//Adding one Battleship ship to fleet
		Battleship bs = Battleship.createShip(unavailablePositions);
		fleet.add(bs);
		shotObservables.add(bs);
		bs.registerObserver(this);
		unavailablePositions.addAll(bs.getShipCoordinates());
		
		//Adding two Cruisers ship to fleet
		Cruiser cr1 = Cruiser.createShip(unavailablePositions);
		fleet.add(cr1);
		shotObservables.add(cr1);
		cr1.registerObserver(this);
		unavailablePositions.addAll(cr1.getShipCoordinates());
		
		Cruiser cr2 = Cruiser.createShip(unavailablePositions);
		fleet.add(cr2);
		shotObservables.add(cr2);
		cr2.registerObserver(this);
		unavailablePositions.addAll(cr2.getShipCoordinates());
		
		//Adding three Seaplanes ship to fleet
		Seaplane sp1 = Seaplane.createShip(unavailablePositions);
		fleet.add(sp1);
		shotObservables.add(sp1);
		sp1.registerObserver(this);
		unavailablePositions.addAll(sp1.getShipCoordinates());
		
		Seaplane sp2 = Seaplane.createShip(unavailablePositions);
		fleet.add(sp2);
		shotObservables.add(sp2);
		sp2.registerObserver(this);
		unavailablePositions.addAll(sp2.getShipCoordinates());
		
		Seaplane sp3 = Seaplane.createShip(unavailablePositions);
		fleet.add(sp3);
		shotObservables.add(sp3);
		sp3.registerObserver(this);
		unavailablePositions.addAll(sp3.getShipCoordinates());
		
		//Adding four Submarines ship to fleet
		Submarine sb1 = Submarine.createShip(unavailablePositions);
		fleet.add(sb1);
		shotObservables.add(sb1);
		sb1.registerObserver(this);
		unavailablePositions.addAll(sb1.getShipCoordinates());
		
		Submarine sb2 = Submarine.createShip(unavailablePositions);
		fleet.add(sb2);
		shotObservables.add(sb2);
		sb2.registerObserver(this);
		unavailablePositions.addAll(sb2.getShipCoordinates());
		
		Submarine sb3 = Submarine.createShip(unavailablePositions);
		fleet.add(sb3);
		shotObservables.add(sb3);
		sb3.registerObserver(this);
		unavailablePositions.addAll(sb3.getShipCoordinates());
		
		Submarine sb4 = Submarine.createShip(unavailablePositions);
		fleet.add(sb4);
		shotObservables.add(sb4);
		sb4.registerObserver(this);
		unavailablePositions.addAll(sb4.getShipCoordinates());
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
					Coordinate coordinate = new Coordinate(x + 1, y + 1);
					
					boolean isShipCoordinate = false;
					
					for (Ship ship : fleet) {
						if(ship.getReachedCoordinates().contains(coordinate))
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
	
	public void drawCleanOcean() throws Exception {
		System.out.println("***************************************************");
		System.out.println("           BEM-VINDO AO BATALHA NAVAL!");
		System.out.println("***************************************************\n\n");
		System.out.println("Este e o seu oceano de guerra, espero que esteja\n");
		System.out.println("preparado para conquistar esse grande territorio.\n\n");
		
		drawOcean();
		
		System.out.println("\n\nVamos ver se voce ainda e um atirador tao bom quanto as lendas dizem.\n");
		
		startWar();
	}
	
	public void startWar() throws Exception {
		while(!isWarOver) {
			System.out.println("Selecione uma letra e um numero (exemplo: A1, C3, ...): ");
			String userValue = reader.nextLine();
			
			if(userValue == null || userValue.trim().isEmpty())
				throw new Exception("\nEntrada de dados invalida, voce nao quer mais guerrear?\n");
			
			userValue = userValue.toUpperCase();
			
			switch (userValue.charAt(0)) {
				case 'A':
					currentCoordinate = new Coordinate(Integer.parseInt(userValue.substring(1, 2)), 1);
					notifyObservers();
					break;
				case 'B':
					currentCoordinate = new Coordinate(Integer.parseInt(userValue.substring(1, 2)), 2);
					notifyObservers();
					break;
				case 'C':
					currentCoordinate = new Coordinate(Integer.parseInt(userValue.substring(1, 2)), 3);
					notifyObservers();
					break;
				case 'D':
					currentCoordinate = new Coordinate(Integer.parseInt(userValue.substring(1, 2)), 4);
					notifyObservers();
					break;
				case 'E':
					currentCoordinate = new Coordinate(Integer.parseInt(userValue.substring(1, 2)), 5);
					notifyObservers();
					break;
				case 'F':
					currentCoordinate = new Coordinate(Integer.parseInt(userValue.substring(1, 2)), 6);
					notifyObservers();
					break;
				case 'G':
					currentCoordinate = new Coordinate(Integer.parseInt(userValue.substring(1, 2)), 7);
					notifyObservers();
					break;
				case 'H':
					currentCoordinate = new Coordinate(Integer.parseInt(userValue.substring(1, 2)), 8);
					notifyObservers();
					break;
				case 'I':
					currentCoordinate = new Coordinate(Integer.parseInt(userValue.substring(1, 2)), 9);
					notifyObservers();
					break;
				case 'J':
					currentCoordinate = new Coordinate(Integer.parseInt(userValue.substring(1, 2)), 10);
					notifyObservers();
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void registerObserver(IShotObserver observer) {
		if(!shotObservables.contains(observer))
			shotObservables.add(observer);
	}

	@Override
	public void removeObserver(IShotObserver observer) {
		if(shotObservables.contains(observer))
			shotObservables.remove(observer);
	}

	@Override
	public void notifyObservers() {
		System.out.println("\nCampeao os navios avisarao quando forem acertados, caso contrario e tiro na agua");
		for (IShotObserver observer : shotObservables) {
			observer.checkShot(currentCoordinate);
		}
	}

	@Override
	public void updateOcean() {
		int sum = 0;
		for (Ship ship : fleet) {
			if(ship.isDestroyed())
				sum++;
		}
		
		if(sum == fleet.size()) {
			isWarOver = true;
			System.out.println("\n\n***************************************************");
			System.out.println("  PARABENS VOCE E O IMPERADOR DESTE VASTO OCEANO!");
			System.out.println("***************************************************\n\n");
		}
			
		drawOceanWithShips();
	}
}
