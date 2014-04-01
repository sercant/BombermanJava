package game.controllers;

import game.controllers.interfaces.IPlayerController;
import game.gui.states.Play;
import game.gui.test.Game;
import game.models.Direction;
import game.models.Door;
import game.models.Map;
import game.models.Player;
import game.models.PowerUp;
import game.models.SolidWall;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerController implements IPlayerController {

	private Map map;
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
	public void update(Map map, int delta) {
		// TODO Auto-generated method stub
		int playerX = player.getX();
		int playerY = player.getY();
		this.map = map;
		int[][] colMap = map.getColMap();
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
		
		if(colMap[playerY][playerX] == Door.ID && map.getDoor().isOpen()){
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
		int[][] colMap = map.getColMap();
		int x = player.getX(), y = player.getY();
		
		if(!player.isMoving() && dir == Direction.Up && colMap[y-1][x] != SolidWall.ID){
			player.setMoving(true);
			player.setCurrentDir(Direction.Up);
			player.setY(y-1);
			if(colMap[y][x] == Player.ID)
				colMap[y][x] = 0;
			if(colMap[y-1][x] != Door.ID)
				colMap[y-1][x] = Player.ID;
			map.setColMap(colMap);
		}else if(!player.isMoving() && dir == Direction.Down && colMap[y+1][x] != SolidWall.ID){
			player.setMoving(true);
			player.setCurrentDir(Direction.Down);
			player.setY(y+1);
			if(colMap[y][x] == Player.ID)
				colMap[y][x] = 0;
			if(colMap[y+1][x] != Door.ID)
				colMap[y+1][x] = Player.ID;
			map.setColMap(colMap);
		}else if(!player.isMoving() && dir == Direction.Left && colMap[y][x-1] != SolidWall.ID){
			player.setMoving(true);
			player.setCurrentDir(Direction.Left);
			player.setX(x-1);
			if(colMap[y][x] == Player.ID)
				colMap[y][x] = 0;
			if(colMap[y][x-1] != Door.ID)
				colMap[y][x-1] = Player.ID;
			map.setColMap(colMap);
		}else if(!player.isMoving() && dir == Direction.Right && colMap[y][x+1] != SolidWall.ID){
			player.setMoving(true);
			player.setCurrentDir(Direction.Right);
			player.setX(x+1);
			if(colMap[y][x] == Player.ID)
				colMap[y][x] = 0;
			if(colMap[y][x+1] != Door.ID)
				colMap[y][x+1] = Player.ID;
			map.setColMap(colMap);
		}
	}

	public float getRealX() {
		return realX;
	}

	public float getRealY() {
		return realY;
	}
	
}
