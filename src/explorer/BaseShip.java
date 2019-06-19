package explorer;

import java.awt.Point;

public class BaseShip extends Ship{

	private Map map;
	private Point currentLocation;
	
	public BaseShip(Map map) {
		this.map = map;
		currentLocation = map.getShipLocation();
	}
	
	/*
	 * checks if the ship can move in the positive x direction
	 * if possible, moves the ship and notifies its observers
	 * does not return a value
	 */
	public void goEast() {
		if(currentLocation.x < map.getDimension()-1 &&
				map.getMap()[currentLocation.x+1][currentLocation.y] != 2) {
			currentLocation.x++;
		}
		setChanged();
		notifyObservers();
		return;
	}
	
	/*
	 * checks if the ship can move in the negative x direction
	 * if possible, moves the ship and notifies its observers
	 */
	public void goWest() {
		if(currentLocation.x > 0 &&
				map.getMap()[currentLocation.x-1][currentLocation.y] != 2) {
			currentLocation.x--;
		}
		setChanged();
		notifyObservers();
		return;
	}
	
	/*
	 * checks if the ship can move in the negative y direction
	 * if possible, moves the ship and notifies its observers
	 */
	public void goNorth() {
		if(currentLocation.y > 0 &&
				map.getMap()[currentLocation.x][currentLocation.y-1] != 2) {
			currentLocation.y--;
		}
		setChanged();
		notifyObservers();
		return;
	}
	
	/*
	 * checks if the ship can move in the positive y direction
	 * if possible, moves the ship and notifies its observers
	 */
	public void goSouth() {
		if(currentLocation.y < map.getDimension()-1 &&
				map.getMap()[currentLocation.x][currentLocation.y+1] != 2) {
			currentLocation.y++;
		}
		setChanged();
		notifyObservers();
		return;
	}
	
	public void setLocation(Point location) {
		currentLocation = location;
	}
	
	//returns the ship's location
	public Point getShipLocation() {
		return currentLocation;
	}
}
