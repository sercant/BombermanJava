package game.gui.states;

import game.controllers.DoorController;
import game.controllers.MapController;
import game.controllers.PlayerController;
import game.gui.camera.Camera;
import game.gui.painter.ElementPainter;
import game.gui.test.Game;
import game.models.Direction;
import game.models.Door;
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
	private int tileCountY = 11;
	private int tileCountX = 19;
	private PlayerController playerController;
	private DoorController doorController;
	private MapController mapController;
	
	public Play(int state){
		this.ID = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
//		map = new Map(tileCountX, tileCountY);
		Player player = new Player(1, 1, Direction.Down);
		playerController = new PlayerController(player, sbg);
		
		Door door = new Door(7, 3);
		doorController = new DoorController(door, sbg);
		
		mapController = new MapController(new Map(tileCountX, tileCountY), sbg);
		mapController.init();
		mapController.addMapElement(player);
		mapController.addMapElement(door);
		
		cam = new Camera(gc, mapController.getMap().getWidth(), mapController.getMap().getHeight());
		
		painter = new ElementPainter(sbg, cam, new Image("res/solidWall.png"), new Image("res/brickWall.png"), null, new Image("res/door.png"), null, new Image("res/playerwalk.png"), null);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setBackground(new Color(59, 121, 1));
		painter.draw(g);
		g.drawString("X: " + cam.getCameraX() + " Y: " + cam.getCameraY(), 300, 10);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		playerController.update(delta);
		doorController.update(delta);
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
	public Map getMap(){
		return mapController.getMap();
	}

	public int getLevelCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
