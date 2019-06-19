package explorer;

public class PirateFactory {	
	
	Ship ship;
	Map map;
	
	public PirateFactory(Ship ship, Map map) {
		this.ship = ship;
		this.map = map;
	}
	
	
	/*
	 * create a Pirate object with the specified movement strategy
	 */
	public Pirate create(String method) {
		if (method.equals("Chase")) {
			return new Pirate(map,ship.getShipLocation(),new Chase());
		}
		else if (method.equals("Wander")) {
			return new Pirate(map,ship.getShipLocation(),new Wander());
		}
		else return null;
	}
	
}
