package game.gui.states;

import game.entities.Direction;
import game.entities.Door;
import game.entities.Map;
import game.entities.SolidWall;
import game.entityImps.IPlayerIMP;
import game.gui.camera.Camera;
import game.gui.painter.ElementPainter;
import game.gui.test.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
	
	private Map map;
	private ElementPainter painter;
	private int delta;
	private Camera cam;
	
	public Play(int state){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		map = new Map(32, 21);
		initMap(sbg);
		cam = new Camera(gc, map);
		painter = new ElementPainter(map, cam, new Image("res/solidWall.png"), new Image("res/brickWall.png"), null, new Image("res/solidWall.png"), null, new Image("res/player.png"), null);
	}

	private void initMap(StateBasedGame sbg) {
		// TODO Auto-generated method stub
		int tileCountY = 21;
		int tileCountX = 31;
		for (int i = 0; i < tileCountY; i++)
		{
			for (int j = 0; j < tileCountX; j++)
			{
				if(		i == 0 || i == tileCountY-1 
					||	j == 0 || j == tileCountX - 1 
					||	(i % 2 == 0 && j % 2 == 0))
					map.addSolidWall(new SolidWall(j, i));
			}
		}
		
		map.setPlayer(new IPlayerIMP(1, 1, Direction.Down, sbg));
		map.setDoor(new Door(7, 1));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setBackground(new Color(59, 121, 1));
		painter.draw(delta, g);
		g.drawString("X: " + cam.getCameraX() + " Y: " + cam.getCameraY(), 300, 10);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		this.delta = delta;
		Input input = gc.getInput();
		
		IPlayerIMP player = (IPlayerIMP) map.getPlayer();
		
		if(input.isKeyDown(Input.KEY_UP)){
			player.move(Direction.Up);
		}if (input.isKeyDown(Input.KEY_DOWN)) {
			player.move(Direction.Down);
		}if (input.isKeyDown(Input.KEY_LEFT)) {
			player.move(Direction.Left);
		}if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.move(Direction.Right);
		}if(input.isKeyDown(Input.KEY_SPACE)){
			map.getDoor().open();
		}
		player.update(map, delta);
		cam.centerOn(map.getPlayer().getX() * Game.TILESIZE, map.getPlayer().getY() * Game.TILESIZE);
	}

	@Override
	public int getID() {
		return 1;
	}
}
