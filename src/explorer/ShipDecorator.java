package explorer;

public abstract class ShipDecorator extends Ship{
	
	/*
	 * checks if the ship can move in the positive x direction
	 * if possible, moves the ship and notifies its observers
	 * does not return a value
	 */
	public abstract void goEast();
	
	/*
	 * checks if the ship can move in the negative x direction
	 * if possible, moves the ship and notifies its observers
	 */
	public abstract void goWest();
	
	/*
	 * checks if the ship can move in the negative y direction
	 * if possible, moves the ship and notifies its observers
	 */
	public abstract void goNorth();
	
	/*
	 * checks if the ship can move in the positive y direction
	 * if possible, moves the ship and notifies its observers
	 */
	public abstract void goSouth();

}
