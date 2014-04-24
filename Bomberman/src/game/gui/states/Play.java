package game.gui.states;

import game.controllers.BombController;
import game.controllers.BrickWallController;
import game.controllers.DoorController;
import game.controllers.ExplosionController;
import game.controllers.MapController;
import game.controllers.PlayerController;
import game.factories.LevelFactory;
import game.gui.camera.Camera;
import game.gui.main.Game;
import game.gui.painter.ElementPainter;
import game.models.Direction;
import game.models.Map;
import game.models.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
	private int ID;
//	private Map map;
	private ElementPainter painter;
	private Camera cam;
//	private int tileCountY = 11;
//	private int tileCountX = 17;
	private int currentDifficulty = 0;
	private LevelFactory levelFactory;
	private PlayerController playerController;
	private DoorController doorController;
	private MapController mapController;
	private BombController bombController;
	private ExplosionController explosionController;
	private BrickWallController brickWallController;
	
	public Play(int state){
		this.ID = state;
	}
	/**
	 * Initializes the state.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		levelFactory = new LevelFactory();
		Map map = levelFactory.generateLevel(0, new Player(1, 1, Direction.Down));
		//Player player = new Player(1, 1, Direction.Down);
		playerController = new PlayerController(levelFactory.getPlayer(), sbg);
		
		//Door door = new Door(3, 3);
		doorController = new DoorController(levelFactory.getDoor(), sbg);
		
		mapController = new MapController(map/*new Map(tileCountX, tileCountY)*/, sbg);
		//mapController.init();
//		try{
//			mapController.addMapElement(player);
//			mapController.addMapElement(door);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		bombController = new BombController(sbg);
		
		explosionController = new ExplosionController(sbg);
		
		brickWallController = new BrickWallController(sbg);
		brickWallController.setBrickWalls(levelFactory.getBrickWalls());
		
		cam = new Camera(gc, mapController.getMapWidth(), mapController.getMapHeight());
		
		painter = new ElementPainter(sbg, cam, 	new Image("res/solidWall.png"),
												new Image("res/brickWall.png"),
												new Image("res/bomb.png"),
												new Image("res/door.png"), 
												new Image("res/explosion.png"), 
												new Image("res/playerwalk.png"), 
												null);
	}
	/**
	 * Render part of the state. This is where the graphics printed on the screen.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setBackground(new Color(59, 121, 1));
		painter.draw(g);
		g.drawString("X: " + cam.getCameraX() + " Y: " + cam.getCameraY(), 300, 10);
	}
	/**
	 * Update part of the state. This is where all the changes made.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		playerController.update(delta);
		doorController.update(delta);      
		bombController.update(delta);
		explosionController.update(delta);
		brickWallController.update(delta);
		cam.centerOn(playerController.getRealX() * Game.TILESIZE, playerController.getRealY() * Game.TILESIZE);
	}

	@Override
	public int getID() {
		return ID;
	}
	
	public ElementPainter getElementPainter(){
		return painter;
	}
	public PlayerController getPlayerController(){
		return playerController;
	}
	public MapController getMapController(){
		return mapController;
	}

	public int getLevelCode() {///WILL BE IMPLEMENTED LATER
		// TODO Auto-generated method stub
		return 0;
	}
	public BombController getBombController() {
		// TODO Auto-generated method stub
		return bombController;
	}
	public ExplosionController getExplosionController() {
		return explosionController;
	}
	public BrickWallController getBrickWallController() {
		return brickWallController;
	}
	
}
