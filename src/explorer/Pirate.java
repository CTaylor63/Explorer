package explorer;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Pirate implements Observer {
	
	private SearchMethod method;
	private Point location;
	private Map map;
	private Point shipStart;
	private Random rand = new Random();
	
	/*
	 * initializer, requires an Map and a Point
	 * sets the map and the ship's starting point
	 * calls a function to set the location of the pirate
	 */
	public Pirate(Map map, Point shipStart, SearchMethod method) {
		this.map = map;
		this.shipStart = shipStart;
		location = setLocation();
		setSearchMethod(method);
	}
	
	/*
	 * randomly set the location of the pirate ship at the start
	 * will not spawn on an island or the player ship
	 */
	private Point setLocation() {
		boolean placedPirate = false;
		int dimension = map.getDimension();
		int x = 0;
		int y = 0;
		while(!placedPirate) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			if (map.getMap()[x][y] != 2 &&
					(x != shipStart.getX() && y != shipStart.getY())){
				placedPirate = true;
			}
		}
		return new Point(x,y);
	}
	
	// sets the Pirate's search method
	public void setSearchMethod(SearchMethod method) {
		this.method = method;
	}

	/*
	 * calls the move function for its move strategy
	 * no return value
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Ship) {
			location = method.search((Ship) o, location, map);
		}
	}
	
	//returns the pirate's current location
	public Point getLocation() {
		return location;
	}

}
