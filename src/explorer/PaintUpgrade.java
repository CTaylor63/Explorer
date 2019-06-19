package explorer;

import java.awt.Point;

public class PaintUpgrade extends ShipDecorator{
	
	Ship ship;
	private Map map;

	public PaintUpgrade(Map map, Ship ship) {
		this.map = map;
		this.ship = ship;
	}

	//sets the value of the map at the ship's location to a value corresponding to paint
	//then calls the ship's move function
	@Override
	public void goEast() {
		Point loc = ship.getShipLocation();
		if (loc.x < map.getDimension()-1 && map.getMap()[loc.x+1][loc.y] != 2 ) 
			map.getMap()[loc.x+1][loc.y] = 3;
		ship.goEast();
	}

	//sets the value of the map at the ship's location to a value corresponding to paint
	//then calls the ship's move function
	@Override
	public void goWest() {
		Point loc = ship.getShipLocation();
		if (loc.x > 0 && map.getMap()[loc.x-1][loc.y] != 2 ) 
			map.getMap()[loc.x-1][loc.y] = 3;
		ship.goWest();	
	}

	//sets the value of the map at the ship's location to a value corresponding to paint
	//then calls the ship's move function
	@Override
	public void goNorth() {
		Point loc = ship.getShipLocation();
		if (loc.y > 0 && map.getMap()[loc.x][loc.y-1] != 2 ) 
			map.getMap()[loc.x][loc.y-1] = 3;
		ship.goNorth();
	}

	//sets the value of the map at the ship's location to a value corresponding to paint
	//then calls the ship's move function
	@Override
	public void goSouth() {
		Point loc = ship.getShipLocation();
		if (loc.y < map.getDimension()-1 && map.getMap()[loc.x][loc.y+1] != 2 ) 
			map.getMap()[loc.x][loc.y+1] = 3;
		ship.goSouth();
	}
	
	//returns the ship's location by calling its getShipLocation function
	public Point getShipLocation() {
		return ship.getShipLocation();
	}

}
