package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import explorer.BaseShip;
import explorer.Map;
import explorer.PaintUpgrade;


public class PaintTest {
	
	Map map = Map.getInstance(40, 0, false);
	BaseShip ship = new BaseShip(map);

	@Test
	public void testGoEast(){
		ship.setLocation(new Point(10,10));
		PaintUpgrade paint = new PaintUpgrade(map,ship);
		int oldValue = map.getMap()[ship.getShipLocation().x][ship.getShipLocation().y];
		System.out.println(oldValue);
		paint.goEast();
		int newValue = map.getMap()[ship.getShipLocation().x][ship.getShipLocation().y];
		System.out.println(newValue);
		assertFalse(oldValue == newValue);
		assertTrue(oldValue == 0);
		assertTrue(newValue == 3);
	}
	
	@Test
	public void testGoWest(){
		ship.setLocation(new Point(10,10));
		PaintUpgrade paint = new PaintUpgrade(map,ship);
		int oldValue = map.getMap()[ship.getShipLocation().x][ship.getShipLocation().y];
		System.out.println(oldValue);
		paint.goWest();
		int newValue = map.getMap()[ship.getShipLocation().x][ship.getShipLocation().y];
		System.out.println(newValue);
		assertFalse(oldValue == newValue);
		assertTrue(oldValue == 0);
		assertTrue(newValue == 3);
	}
	
	@Test
	public void testGoNorth(){
		ship.setLocation(new Point(10,10));
		PaintUpgrade paint = new PaintUpgrade(map,ship);
		int oldValue = map.getMap()[ship.getShipLocation().x][ship.getShipLocation().y];
		System.out.println(oldValue);
		paint.goNorth();
		int newValue = map.getMap()[ship.getShipLocation().x][ship.getShipLocation().y];
		System.out.println(newValue);
		assertFalse(oldValue == newValue);
		assertTrue(oldValue == 0);
		assertTrue(newValue == 3);
	}
	
	@Test
	public void testGoSouth(){
		ship.setLocation(new Point(10,10));
		PaintUpgrade paint = new PaintUpgrade(map,ship);
		int oldValue = map.getMap()[ship.getShipLocation().x][ship.getShipLocation().y];
		System.out.println(oldValue);
		paint.goSouth();
		int newValue = map.getMap()[ship.getShipLocation().x][ship.getShipLocation().y];
		System.out.println(newValue);
		assertFalse(oldValue == newValue);
		assertTrue(oldValue == 0);
		assertTrue(newValue == 3);
	}
}
