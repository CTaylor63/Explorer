package explorer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import explorer.Map;
import explorer.Pirate;
import explorer.Ship;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Explorer extends Application{

	BorderPane root;
	AnchorPane gameRoot;
	final int dimension = 40;
	final int scale = 25;
	final int islandCount = 20;
	final int territorySize = 15;
	Text display;
	Text upgradeStatus;
	int pirateCount = 8;
	int[][] grid;
	Rectangle[][] rects;
	Map map;
	Territory territory;
	Image shipImage;
	Image pirateImage;
	Image island;
	ImageView shipImageView;
	ImageView[] pirateViews;
	ImageView[] sharkViews;
	ImageView paintView;
	ArrayList<Pirate> pirates = new ArrayList<Pirate>();
	ArrayList<Monster> sharks = new ArrayList<Monster>();
	Point[] islands = new Point[islandCount];
	Scene scene;
	Ship ship;
	boolean pauseEvents = false;
	
	
	/*
	 * sets up the scene, calling a function to set up a map
	 * calls functions for adding a ship and two pirates
	 * calls a listener for keyboard events
	 * does not return a value
	 */
	@Override
	public void start(Stage oceanStage) throws Exception {
		
		map = Map.getInstance(dimension,islandCount,false);
		grid = map.getMap();
		rects = new Rectangle[dimension][dimension];
		
		root = new BorderPane();
		
		gameRoot = new AnchorPane();
		drawMap();
		
		ship = new BaseShip(map);
		loadShipImage();
		
		addPirates();
		loadPirateImages();
		createTerritory();
		loadSharkViews();
		observeShip();
		
		loadPaintImage();
		
		root.setCenter(gameRoot);
		
		VBox controls = new VBox(8);
		
		Text title = new Text("Explorer");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
	    controls.getChildren().add(title);
	    
	    upgradeStatus = new Text("");
	    upgradeStatus.setFont(Font.font("Arial",FontWeight.NORMAL, 20));
	    controls.getChildren().add(upgradeStatus);
	    
	    display = new Text("");
	    display.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
	    controls.getChildren().add(display);
	    
	    Button btn = new Button("Reset");
	    controls.getChildren().add(btn);
	    
	    btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				reset();	
			}
			
		});
	    
	    BorderPane.setAlignment(controls,Pos.TOP_CENTER);
	    BorderPane.setMargin(controls, new Insets(40,40,40,0));
	    root.setRight(controls);
		
		scene = new Scene(root,scale*50,scale*40);
		oceanStage.setScene(scene);
		oceanStage.setTitle("OceanGame");
		oceanStage.show();

		startSailing();
	}
	
	
	/*
	 * creates a grid of size dimension by dimension
	 * uses the Map object to place islands and water
	 * adds the map to the root's children
	 * does not return a value
	 */
	private void drawMap() {
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				Rectangle rect = new Rectangle(x*scale, y*scale, scale, scale);
				rects[x][y] = rect;
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.PALETURQUOISE);
				gameRoot.getChildren().add(rect);
				if(grid[x][y] == 2) {
					island = new Image("island.jpg",scale,scale,true,true);
					ImageView islandView = new ImageView(island);
					islandView.setX(x*scale);
					islandView.setY(y*scale);
					gameRoot.getChildren().add(islandView);
				}
			}
		}
	}
	
	
	//create a territory object and fill it with a set number of monster objects
	private void createTerritory() {
		Random rand = new Random();
		int x = rand.nextInt(dimension-territorySize);
		int y = rand.nextInt(dimension-territorySize);
		Rectangle rect = new Rectangle(x*scale,y*scale,territorySize*scale,territorySize*scale);
		territory = new Territory(rect);
		for (int i = 0; i < 5; i++) {
			int xstart = rand.nextInt(dimension);
			int ystart = rand.nextInt(dimension);
			while (!rect.contains(new Point2D(xstart*scale,ystart*scale))) {
				xstart = rand.nextInt(dimension);
				ystart = rand.nextInt(dimension);
			}
			Monster shark = new Monster(map,scale,new Point(xstart,ystart));
			sharks.add(shark);
			territory.addChild(shark);
		}
	}
	
	
	/*
	 * create views for the monster objects
	 * adds the views to the root
	 */
	private void loadSharkViews() {
		ArrayList<ImageView> views = new ArrayList<ImageView>();
		for (Monster shark : sharks) {
			Image sharkImage = new Image("sharkfin.png",scale,scale,true,true);
			ImageView sharkImageView = new ImageView(sharkImage);
			sharkImageView.setX(shark.getLocation().x*scale);
			sharkImageView.setY(shark.getLocation().y*scale);
			gameRoot.getChildren().add(sharkImageView);
			
			views.add(sharkImageView);
		}
		sharkViews = new ImageView[views.size()];
		sharkViews = views.toArray(sharkViews);
	}
	
	
	
	/*
	 * creates an ImageView for a S object
	 * adds the ImageView to the root's children
	 * does not return a value
	 */
	private void loadShipImage() {
		shipImage = new Image("ship.png",scale,scale,true,true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(map.getShipLocation().x*scale);
		shipImageView.setY(map.getShipLocation().y*scale);
		
		gameRoot.getChildren().add(shipImageView);

	}
	
	//loads the paint icon for the upgrade
	private void loadPaintImage() {
		Image paintImage = new Image("Paint.png",scale,scale,true,true);
		paintView = new ImageView(paintImage);
		paintView.setX(map.getUpgrade().x*scale);
		paintView.setY(map.getUpgrade().y*scale);
		
		gameRoot.getChildren().add(paintView);
	}
	
	/*
	 * creates an ImageView for every PirateShip object in pirates
	 * adds the ImageView to the root's children
	 * when all ImageViews are created, converts the views ArrayList
	 *    to an array and sets pirateViews to this new array
	 * does not return a value
	 */
	private void loadPirateImages() {
		ArrayList<ImageView> views = new ArrayList<ImageView>();
		for (Pirate pirate : pirates) {
			pirateImage = new Image("pirateShip.png",scale,scale,true,true);
			ImageView pirateImageView = new ImageView(pirateImage);
			pirateImageView.setX(pirate.getLocation().x*scale);
			pirateImageView.setY(pirate.getLocation().y*scale);
			gameRoot.getChildren().add(pirateImageView);
			
			views.add(pirateImageView);
		}
		pirateViews = new ImageView[views.size()];
		pirateViews = views.toArray(pirateViews);
	}
	
	/*
	 * create a number of pirates and randomly set their movement behavior
	 * no return value
	 */
	private void addPirates() {
		String[] methods = {"Chase","Wander"};
		PirateFactory factory = new PirateFactory(ship,map);
		Random rand = new Random();
		for (int i = 0; i < pirateCount; i++) {
			int index = rand.nextInt(2);
			pirates.add( factory.create(methods[index]) );
		}
	}
	
	//adds every pirate ship as an observer of ship
	private void observeShip() {
		for (Pirate pirate : pirates) {
			ship.addObserver(pirate);
		}
		ship.addObserver(territory);
	}
	
	/*
	 * creates a keyboard listener for the arrow keys
	 * handles arrow key presses by calling the appropriate ship method
	 * updates the shipImageView to match the ship's new location
	 * calls a function to update the pirateViews ImageViews
	 * does not return a value
	 */
	private void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent ke) {
				if (pauseEvents) return;
				
				switch(ke.getCode()) {
				case RIGHT:
					ship.goEast();
					break;
				case LEFT:
					ship.goWest();
					break;
				case UP:
					ship.goNorth();
					break;
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
				}
				shipImageView.setX(map.getShipLocation().x*scale);
				shipImageView.setY(map.getShipLocation().y*scale);
				updateMap();
				int condition = checkLocation();
				switch (condition) { 
				case 1: { pauseEvents = true; victory();break;}
				case 2: { pauseEvents = true; updatePirateViews(); defeat();break;}
				default:{ updatePirateViews(); updateSharkViews();break;}
				}
			}
		});
	}
	
	/*
	 * updates the ImageViews for the pirates
	 * does not return a value
	 */
	private void updatePirateViews() {
		int index = 0;
		for (Pirate pirate : pirates) {
			pirateViews[index].setX(pirate.getLocation().x*scale);
			pirateViews[index].setY(pirate.getLocation().y*scale);
			index++;
		}
	}
	
	/*
	 * updates the ImageViews for the monsters
	 * does not return a value
	 */
	private void updateSharkViews() {
		int index = 0;
		for (Monster shark : sharks) {
			sharkViews[index].setX(shark.getLocation().x*scale);
			sharkViews[index].setY(shark.getLocation().y*scale);
			index++;
		}
	}
	
	//update the map with a painted square
	private void updateMap() {		
		Point location = ship.getShipLocation();
		if (map.getMap()[location.x][location.y] == 3 ) {
			rects[location.x][location.y].setFill(Color.DARKSEAGREEN);
		}
	}
	
	/*
	 * checks if the player ship is on the same tile as the treasure,
	 * 	  a pirate, or the upgrade
	 */
	private int checkLocation() {
		if (ship.getShipLocation().equals(map.getTreasure())) {
			return 1;
		}
		for (Pirate pirate : pirates) {
			if (pirate.getLocation().equals(ship.getShipLocation())) {
				return 2;
			}
		}
		for (Monster shark : sharks) {
			if (shark.getLocation().equals(ship.getShipLocation())) {
				return 2;
			}
		}
		if (ship.getShipLocation().equals(map.getUpgrade()) && ship instanceof BaseShip) {
			ship = new PaintUpgrade(map,(BaseShip)ship);
			upgradeStatus.setText("Paint Upgrade Acquired");
			paintView.setVisible(false);
		}
		return 0;
	}
	
	//set the display box to a victory label
	private void victory() {
		display.setText("VICTORY");
	}
	
	//set the display box to a defeat label
	private void defeat() {
		display.setText("DEFEAT");
	}
	
	
	//resets the variables and objects used by the game
	//begins a new game
	private void reset() {
		gameRoot.getChildren().clear();
		pirates = new ArrayList<Pirate>();
		sharks = new ArrayList<Monster>();
		display.setText("");
		upgradeStatus.setText("");
		pauseEvents = false;
		map = Map.getInstance(dimension, islandCount, true);
		
		grid = map.getMap();

		rects = new Rectangle[dimension][dimension];
		drawMap();
		
		ship = new BaseShip(map);
		loadShipImage();
		
		addPirates();
		loadPirateImages();
		createTerritory();
		loadSharkViews();
		observeShip();
		
		loadPaintImage();
		
		startSailing();
	}
	
	//begins the application
	public static void main(String[] args) {
		launch(args);
	}

}
