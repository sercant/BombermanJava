package game.controllers;

import game.controllers.interfaces.IPlayerController;
import game.gui.main.Game;
import game.gui.states.GameOver;
import game.gui.states.Play;
import game.models.Cell;
import game.models.Direction;
import game.models.Door;
import game.models.ElementType;
import game.models.Player;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerController implements IPlayerController {

	private StateBasedGame game;
	private Player player;
	private float moveTimer;
	private float smoothShift;

	
	public PlayerController(Player player, StateBasedGame game) {
		this.player = player;
		resetMoveTimer();
		this.game = game;
		smoothShift = 0;
	}

	@Override
	public void update(int delta) {
		int playerX = player.getX();
		int playerY = player.getY();
		MapController mapController = ((Play) game.getCurrentState()).getMapController();
		
		Input input = game.getContainer().getInput();
		
		if(input.isKeyDown(Input.KEY_UP)){
			movePlayer(Direction.Up);
		}if (input.isKeyDown(Input.KEY_DOWN)) {
			movePlayer(Direction.Down);
		}if (input.isKeyDown(Input.KEY_LEFT)) {
			movePlayer(Direction.Left);
		}if (input.isKeyDown(Input.KEY_RIGHT)) {
			movePlayer(Direction.Right);
		}
		
		if(player.isMoving()){
			((Play) game.getCurrentState()).getElementPainter().startPlayerAnim(player.getCurrentDir());
		}else
			((Play) game.getCurrentState()).getElementPainter().stopPlayerAnim(player.getCurrentDir());
		
		if(player.isMoving()){
			moveTimer -= delta;
			smoothShift = (float) delta / getMoveTime();
			float realX = player.getRealX();
			float realY = player.getRealY();
			
			if(realX > playerX)
				player.setRealX(realX -= smoothShift);
			else if(realX < playerX)
				player.setRealX(realX += smoothShift);
			if(realY > playerY)
				player.setRealY(realY -= smoothShift);
			else if(realY < playerY)
				player.setRealY(realY += smoothShift);
			
			if(moveTimer < 0){
				player.setMoving(false);
				resetMoveTimer();
				smoothShift = 0;
				player.setRealX(realX = playerX);
				player.setRealY(realY = playerY);
				mapController.getCellAt(player.getPrevX(), player.getPrevY()).deleteElement(player);
				mapController.getCellAt(player.getX(), player.getY()).addElement(player);
				player.setPrevX(player.getX());
				player.setPrevY(player.getY());
			}
		}
		Cell cell = mapController.getCellAt(player.getX(), player.getY());
		if(cell.isContains(ElementType.Player) && cell.isContains(ElementType.Door) && ((Door) cell.getElement(ElementType.Door)).isOpen()){
			try {
				game.initStatesList(game.getContainer());
				((GameOver) game.getState(Game.gameOver)).setScore(player.getScore());
				((GameOver) game.getState(Game.gameOver)).setLevelCode(((Play)game.getCurrentState()).getLevelCode());
				game.enterState(Game.gameOver);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

	private void resetMoveTimer() {
		moveTimer = getMoveTime();
	}
	
	private float getMoveTime(){
		return (float) 300.f / player.getMoveSpeed();
	}

	@Override
	public void movePlayer(Direction dir) {
		int x = player.getX(), y = player.getY();
		MapController mapController = ((Play) game.getCurrentState()).getMapController();
		
		if(!player.isMoving() && dir == Direction.Up && !mapController.getCellAt(x, y-1).isContains(ElementType.SolidWall)){
			player.setMoving(true);
			player.setCurrentDir(Direction.Up);
			player.setY(y-1);
		}else if(!player.isMoving() && dir == Direction.Down && !mapController.getCellAt(x, y+1).isContains(ElementType.SolidWall)){
			player.setMoving(true);
			player.setCurrentDir(Direction.Down);
			player.setY(y+1);
		}else if(!player.isMoving() && dir == Direction.Left && !mapController.getCellAt(x-1, y).isContains(ElementType.SolidWall)){
			player.setMoving(true);
			player.setCurrentDir(Direction.Left);
			player.setX(x-1);
		}else if(!player.isMoving() && dir == Direction.Right && !mapController.getCellAt(x+1, y).isContains(ElementType.SolidWall)){
			player.setMoving(true);
			player.setCurrentDir(Direction.Right);
			player.setX(x+1);
		}
	}

	public float getRealX() {
		return player.getRealX();
	}

	public float getRealY() {
		return player.getRealY();
	}
	
}
