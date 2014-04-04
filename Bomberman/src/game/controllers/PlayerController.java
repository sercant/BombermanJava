package game.controllers;

import game.controllers.interfaces.IPlayerController;
import game.gui.states.Play;
import game.gui.test.Game;
import game.models.Direction;
import game.models.ElementType;
import game.models.Map;
import game.models.Player;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerController implements IPlayerController {

	private StateBasedGame game;
	private Player player;
	private float moveTimer;
	private float smoothShift;
	private float realX;
	private float realY;
	
	public PlayerController(Player player, StateBasedGame game) {
		this.player = player;
		resetMoveTimer();
		this.game = game;
		realX = player.getX();
		realY = player.getY();
		smoothShift = 0;
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		int playerX = player.getX();
		int playerY = player.getY();
		Map map = ((Play) game.getCurrentState()).getMap();
		
		if(player.isMoving()){
			((Play) game.getCurrentState()).getElementPainter().startPlayerAnim(player.getCurrentDir());
		}else
			((Play) game.getCurrentState()).getElementPainter().stopPlayerAnim(player.getCurrentDir());
		
		if(player.isMoving()){
			moveTimer -= delta;
			smoothShift = (float) delta / getMoveTime();
			
			if(realX > playerX)
				realX -= smoothShift;
			else if(realX < playerX)
				realX += smoothShift;
			if(realY > playerY)
				realY -= smoothShift;
			else if(realY < playerY)
				realY += smoothShift;
			
			if(moveTimer < 0){
				player.setMoving(false);
				resetMoveTimer();
				smoothShift = 0;
				realX = playerX;
				realY = playerY;
			}
		}
		
		if(map.getCellAt(playerX, playerY).isContains(ElementType.Door) && map.getDoor().isOpen()){
			try {
				game.getCurrentState().init(game.getContainer(), game);
				game.enterState(Game.menu);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void resetMoveTimer() {
		// TODO Auto-generated method stub
		moveTimer = getMoveTime();
	}
	
	private float getMoveTime(){
		return (float) 300.f / player.getMoveSpeed();
	}

	@Override
	public void move(Direction dir) {
		// TODO Auto-generated method stub
		int x = player.getX(), y = player.getY();
		Map map = ((Play) game.getCurrentState()).getMap();
		
		if(!player.isMoving() && dir == Direction.Up && !map.getCellAt(x, y-1).isContains(ElementType.SolidWall)){
			player.setMoving(true);
			player.setCurrentDir(Direction.Up);
			player.setY(y-1);
			map.getCellAt(x, y).deleteElement(player);
			map.getCellAt(x, y-1).addElement(player);	//Replace this two part
		}else if(!player.isMoving() && dir == Direction.Down && !map.getCellAt(x, y+1).isContains(ElementType.SolidWall)){
			player.setMoving(true);
			player.setCurrentDir(Direction.Down);
			player.setY(y+1);
			map.getCellAt(x, y).deleteElement(player);
			map.getCellAt(x, y+1).addElement(player);
		}else if(!player.isMoving() && dir == Direction.Left && !map.getCellAt(x-1, y).isContains(ElementType.SolidWall)){
			player.setMoving(true);
			player.setCurrentDir(Direction.Left);
			player.setX(x-1);
			map.getCellAt(x, y).deleteElement(player);
			map.getCellAt(x-1, y).addElement(player);
		}else if(!player.isMoving() && dir == Direction.Right && !map.getCellAt(x+1, y).isContains(ElementType.SolidWall)){
			player.setMoving(true);
			player.setCurrentDir(Direction.Right);
			player.setX(x+1);
			map.getCellAt(x, y).deleteElement(player);
			map.getCellAt(x+1, y).addElement(player);
		}
	}

	public float getRealX() {
		return realX;
	}

	public float getRealY() {
		return realY;
	}
	
}
