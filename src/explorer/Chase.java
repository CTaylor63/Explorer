package explorer;

import java.awt.Point;
import java.util.Random;


public class Chase implements SearchMethod {
	

	private Random rand = new Random();
	private Point location;
	private Map map;
	
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
		return location;
	}
	
	/*
	 * checks the pirate's position in relation to the ship's location
	 * moves the pirate in the positive or negative direction if there
	 *   is not an island in the destination cell
	 */
	private void moveX(Ship ship) {
		int shipX = ship.getShipLocation().x;
		int pirateX = location.x;
		int pirateY = location.y;
		if (pirateX == shipX) {
			return;
		}
		else if (pirateX < shipX) {
			if (map.getMap()[pirateX+1][pirateY] != 2) {
				location.x++;
			}
			else {
				return;
			}
		}
		else {
			if (map.getMap()[pirateX-1][pirateY] != 2) {
				location.x--;
			}
			else {
				return;
			}
		}
		
	}
	
	/*
	 * checks the pirate's position in relation to the ship's location
	 * moves the pirate in the positive or negative direction if there
	 *   is not an island in the destination cell
	 */
	private void moveY(Ship ship) {
		int shipY = ship.getShipLocation().y;
		int pirateX = location.x;
		int pirateY = location.y;
		if (pirateY == shipY) {
			return;
		}
		else if (pirateY < shipY) {
			if (map.getMap()[pirateX][pirateY+1] != 2) {
				location.y++;
			}
			else {
				return;
			}
		}
		else {
			if (map.getMap()[pirateX][pirateY-1] != 2) {
				location.y--;
			}
			else {
				return;
			}
		}
	}

}
