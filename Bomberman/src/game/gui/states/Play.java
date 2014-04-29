package game.gui.states;

import game.controllers.BombController;
import game.controllers.BrickWallController;
import game.controllers.DoorController;
import game.controllers.ExplosionController;
import game.controllers.MapController;
import game.controllers.PlayerController;
import game.controllers.PowerUpController;
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
	private ElementPainter painter;
	private Camera cam;
	private int currentDifficulty = 0;
	private LevelFactory levelFactory;
	private PlayerController playerController;
	private DoorController doorController;
	private MapController mapController;
	private BombController bombController;
	private ExplosionController explosionController;
	private BrickWallController brickWallController;
	private PowerUpController powerUpController;
//	private Map untouchedMap;
	
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
		
		playerController = new PlayerController(new Player(1, 1, Direction.Down), sbg);
		
		Map map = levelFactory.generateLevel(0, playerController.getPlayer());
//		untouchedMap = new Map(map);
		cam = new Camera(gc, map.getWidth(), map.getHeight());
		
		setUpControllers(map, sbg);
		
		painter = new ElementPainter(sbg, cam, 	new Image("res/solidWall.png"),
				new Image("res/brickWall.png"),
				new Image("res/bomb.png"),
				new Image("res/door.png"), 
				new Image("res/explosion.png"), 
				new Image("res/playerwalk.png"), 
				new Image("res/powerUp.png"));
	}
	/**
	 * Render part of the state. This is where the graphics printed on the screen.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setBackground(new Color(59, 121, 1));
		painter.draw(g);
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
		powerUpController.update(delta);
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
		return Integer.toString(currentDifficulty).hashCode();
	}
	public BombController getBombController() {
		return bombController;
	}
	public ExplosionController getExplosionController() {
		return explosionController;
	}
	public BrickWallController getBrickWallController() {
		return brickWallController;
	}
	public int getCurrentDifficulty() {
		return currentDifficulty;
	}
	public void levelCompleted(StateBasedGame sbg) {
		currentDifficulty++;
		setUpControllers(levelFactory.generateLevel(currentDifficulty, playerController.getPlayer()), sbg);
	}
	private void setUpControllers(Map map, StateBasedGame sbg){
		
		doorController = new DoorController(levelFactory.getDoor(), sbg);
		
		mapController = new MapController(map, sbg);
		
		bombController = new BombController(sbg);
		
		explosionController = new ExplosionController(sbg);
		
		powerUpController = new PowerUpController(sbg);
		powerUpController.setPowerUpElements(levelFactory.getPowerUps());
		
		brickWallController = new BrickWallController(sbg);
		brickWallController.setBrickWalls(levelFactory.getBrickWalls());
		
		cam.setMapWidth(map.getWidth());
		cam.setMapHeight(map.getHeight());
	}
//	public void playerDied(StateBasedGame sbg){
//		Map map = new Map(untouchedMap);
//		setUpControllers(map, sbg);
//	}
}
