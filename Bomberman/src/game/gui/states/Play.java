package game.gui.states;

import game.entities.Direction;
import game.entities.Door;
import game.entities.Map;
import game.entities.SolidWall;
import game.entityImps.IPlayerIMP;
import game.gui.painter.ElementPainter;

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
	
	public Play(int state){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		map = new Map(32, 21);
		initMap();
		painter = new ElementPainter(map, new Image("res/solidWall.png"), null, null, new Image("res/solidWall.png"), null, new Image("res/solidWall.png"), null);
	}

	private void initMap() {
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
		
		map.setPlayer(new IPlayerIMP(1, 1, Direction.Down));
		map.setDoor(new Door(7, 1));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		painter.draw(delta, g);
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
	}

	@Override
	public int getID() {
		return 1;
	}
}
