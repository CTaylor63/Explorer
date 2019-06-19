package explorer;

import java.awt.Point;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class Monster implements MonsterComponent {

	Rectangle rect;
	Map map;
	int scale;
	Point location;
	Random rand = new Random();
	
	public Monster(Map map,int scale, Point startPos) {
		this.map = map;
		this.scale = scale;
		location = startPos;
	}
	
	/*
	 * Randomly selects an initial direction to move in,
	 * then calls the corresponding move function
	 */
	@Override
	public void move() {
		int coinFlip = rand.nextInt(2);
		if (coinFlip == 0) {
			moveX();
		}
		else {
			moveY();
		}
	}
	
	/*
	 * moves the monster in the positive or negative direction if there
	 *   is not an island in the destination cell
	 * horizontal direction chosen at random
	 */
	private void moveX() {
		int x = location.x;
		int y = location.y;
		int coinFlip = rand.nextInt(2);
		if (coinFlip == 0 && rect.contains(new Point2D((x+1)*scale,y*scale)) && map.getMap()[x+1][y] != 2) {
			location.x++;
		}
		else if (coinFlip == 1 && rect.contains(new Point2D((x-1)*scale,y*scale)) && map.getMap()[x-1][y] != 2) {
			location.x--;
		}
		else return;
	}
	
	/*
	 * moves the monster in the positive or negative direction if there
	 *   is not an island in the destination cell
	 * vertical direction chosen at random
	 */
	private void moveY() {
		int x = location.x;
		int y = location.y;
		int coinFlip = rand.nextInt(2);
		if (coinFlip == 0 && rect.contains(new Point2D(x*scale,(y+1)*scale)) && map.getMap()[x][y+1] != 2) {
			location.y++;
		}
		else if (coinFlip == 1 && rect.contains(new Point2D(x*scale,(y-1)*scale)) && map.getMap()[x][y-1] != 2) {
			location.y--;
		}
		else return;
	}
	
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	
	//returns the monster's current location
	public Point getLocation() {
		return location;
	}
	
}
