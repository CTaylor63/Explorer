package explorer;

import java.awt.Point;
import java.util.Random;

public class Map {
	
	private static Map map;
	
	private int[][] ocean;
	Random rand = new Random();
	private int dimension;
	private int numIsles;
	Point shipLocation;
	Point treasure;
	Point upgrade;
	
	/* initializer, requires two arguments: 
	 *   the length of the map
	 *	 the number of islands
	 * calls functions to create a map, place islands, the ship, the upgrade, and the treasure
	 */
	private Map(int dimension, int islands) {
		this.dimension = dimension;
		this.numIsles = islands;
		generate();
		distributeIsles();
		treasure = placeTreasure();
		upgrade = placeUpgrade();
		shipLocation = placeShip();
	}
	
	public static Map getInstance(int dimension, int islands, boolean reset) {
		if (map == null || reset == true) map = new Map(dimension, islands);
		return map;
	}
	
	//creates an empty map of size dimension^2
	private void generate() {
		ocean = new int[dimension][dimension];
	}
	
	// places a treasure in a random unoccupied spot on the map
	private Point placeTreasure() {
		int x = 0;
		int y = 0;
		while(true) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			if (ocean[x][y] == 0){
				ocean[x][y] = 1;
				break;
			}
		}
		return new Point(x,y);
	}
	
	//places an upgrade object in a random unoccupied spot on the map
	private Point placeUpgrade() {
		int x = 0;
		int y = 0;
		while(true) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			if (ocean[x][y] == 0){
				ocean[x][y] = 4;
				break;
			}
		}
		return new Point(x,y);
	}
	
	/*
	 * selects two random numbers and checks if that cell
	 *    on the map is already filled
	 * If the cell is empty, places a ship there and returns 
	 *    the cell as a point
	 */
	private Point placeShip() {
		boolean placedShip = false;
		int x = 0;
		int y = 0;
		while(!placedShip) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			if (ocean[x][y] == 0){
				placedShip = true;
			}
		}
		return new Point(x,y);
	}
	
	/*
	 * selects two random numbers and checks if that cell
	 *     is already filled
	 * If the cell is empty, the cell is filled with a true value
	 * Continues until the number of islands to place is zero
	 */
	private void distributeIsles() {
		int islesNotPlaced = numIsles;
		while(islesNotPlaced > 0) {
			int x = rand.nextInt(dimension);
			int y = rand.nextInt(dimension);
			if (ocean[x][y] == 0) {
				ocean[x][y] = 2;
				islesNotPlaced--;
			}
		}
	}
	
	//returns the map
	public int[][] getMap() {
		return ocean;
	}
	
	//returns the ship's location
	public Point getShipLocation() {
		return shipLocation;
	}
	
	//returns the treasure's location
	public Point getTreasure() {
		return treasure;
	}
	
	//returns the upgrade's location
	public Point getUpgrade() {
		return upgrade;
	}
	
	//returns the dimension of the map
	public int getDimension() {
		return dimension;
	}

}
