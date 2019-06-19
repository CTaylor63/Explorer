package explorer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.shape.Rectangle;

public class Territory implements MonsterComponent, Observer {
	
	private List<MonsterComponent> components = new ArrayList<MonsterComponent>();
	private Rectangle border;

	public Territory(Rectangle rect) {
		this.border = rect;
	}
	
	/*
	 * calls the move function of every component within 
	 * the components variable
	 */
	@Override
	public void move() {
		Iterator<MonsterComponent> iterator = components.iterator();
		while (iterator.hasNext()) {
			MonsterComponent component = iterator.next();
			component.move();
		}
	}
	
	//add a MonsterComponent object to the components of the territory if it is not already present
	public void addChild(MonsterComponent component) {
		if (components.contains(component)) {
			return;
		}
		if (component instanceof Monster) {
			Monster monster = (Monster)component;
			monster.setRect(border);
		}
		components.add(component);
	}
	
	//removes a child from components if it exists
	public void removeChild(MonsterComponent component) {
		if (components.contains(component)) {
			components.remove(component);
		}
	}

	//observes the ship for movement and calls the move function when updated
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Ship) {
			move();
		}
		
	}


}
