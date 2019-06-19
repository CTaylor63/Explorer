package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import explorer.BaseShip;
import explorer.Map;

public class BaseShipTest {
	
	Map map = Map.getInstance(40, 0, false);

	@Test
	public void testGoEast(){
		BaseShip ship = new BaseShip(map);
		ship.setLocation(new Point(10,10));
		int oldX = ship.getShipLocation().x;
		System.out.println(oldX);
		ship.goEast();
		int newX = ship.getShipLocation().x;
		System.out.println(newX);
		assertFalse(oldX == newX);
		assertTrue(oldX < newX);
	}
	
	@Test
	public void testGoWest(){
		BaseShip ship = new BaseShip(map);
		ship.setLocation(new Point(10,10));
		int oldX = ship.getShipLocation().x;
		System.out.println(oldX);
		ship.goWest();
		int newX = ship.getShipLocation().x;
		System.out.println(newX);
		assertFalse(oldX == newX);
		assertTrue(oldX > newX);
	}
	
	@Test
	public void testGoNorth(){
		BaseShip ship = new BaseShip(map);
		ship.setLocation(new Point(10,10));
		int oldY = ship.getShipLocation().y;
		System.out.println(oldY);
		ship.goNorth();
		int newY = ship.getShipLocation().y;
		System.out.println(newY);
		assertFalse(oldY == newY);
		assertTrue(oldY > newY);
	}
	
	@Test
	public void testGoSouth(){
		BaseShip ship = new BaseShip(map);
		ship.setLocation(new Point(10,10));
		int oldY = ship.getShipLocation().y;
		System.out.println(oldY);
		ship.goSouth();
		int newY = ship.getShipLocation().y;
		System.out.println(newY);
		assertFalse(oldY == newY);
		assertTrue(oldY < newY);
	}
	
}
