package explorer;

import java.awt.Point;
import java.util.Random;

public class Wander implements SearchMethod {
	
	//search method for pirates, randomly move in a direction
	

	private Random rand = new Random();
	private Point location;
	private Map map;
	
	
	/*
	 * Randomly selects an initial direction to move in,
	 * then calls the corresponding move function
	 */
	public Point search(Ship ship, Point location, Map map) {
		int coinFlip = rand.nextInt(2);
		this.location = location;
		this.map = map;
		if (coinFlip == 0) {
			moveX(ship);
		}
		else {
			moveY(ship);
		}
		return this.location;
	}
	
	/*
	 * moves the pirate in the positive or negative direction if there
	 *   is not an island in the destination cell
	 * horizontal direction chosen at random
	 */
	private void moveX(Ship ship) {
		int pirateX = location.x;
		int pirateY = location.y;
		int coinFlip = rand.nextInt(2);
		if (coinFlip == 0 && pirateX+1 < map.getDimension() && map.getMap()[pirateX+1][pirateY] != 2) {
			location.x++;
		}
		else if (coinFlip == 1 && pirateX-1 > 0 && map.getMap()[pirateX-1][pirateY] != 2) {
			location.x--;
		}
		else return;
	}
	
	/*
	 * moves the pirate in the positive or negative direction if there
	 *   is not an island in the destination cell
	 * vertical direction chosen at random
	 */
	private void moveY(Ship ship) {
		int pirateX = location.x;
		int pirateY = location.y;
		int coinFlip = rand.nextInt(2);
		if (coinFlip == 0 && pirateY+1 < map.getDimension() && map.getMap()[pirateX][pirateY+1] != 2) {
			location.y++;
		}
		else if (coinFlip == 1 && pirateY-1 > 0 && map.getMap()[pirateX][pirateY-1] != 2) {
			location.y--;
		}
		else return;
	}
}
