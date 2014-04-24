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
		if(input.isKeyDown(Input.KEY_LCONTROL)){
			placeBomb();
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
		if(player.isMoving()){
			return;
		}
		
		int x = player.getX(), y = player.getY();
		MapController mapController = ((Play) game.getCurrentState()).getMapController();
		if(mapController.getCellAt(x, y).isContains(ElementType.Explosion)){
			return;
		}
		
		int toX = x, toY = y;
		
		switch (dir) {
		case Up:
			toY--;
			break;
		case Down:
			toY++;
			break;
		case Left:
			toX--;
			break;
		case Right:
			toX++;
			break;
		default:
			break;
		}
		
		Cell cell = mapController.getCellAt(toX, toY);
		if(		cell.isContains(ElementType.SolidWall)
				||cell.isContains(ElementType.BrickWall)){
			return;
		}
		//finally
		player.setMoving(true);
		player.setCurrentDir(dir);
		player.setX(toX);
		player.setY(toY);

	}
	private void placeBomb(){
		if(player.isMoving()){
			return;
		}
		MapController mapController = ((Play) game.getCurrentState()).getMapController();
		Cell cell = mapController.getCellAt(player.getX(), player.getY());
		if(!cell.isContains(ElementType.Bomb)){
			BombController bc = ((Play) game.getCurrentState()).getBombController();
			bc.spawnBomb(player.getX(), player.getY(), player);
//			if(player.isMoving()){//this part may change later
//				bc.spawnBomb(player.getPrevX(), player.getPrevY(), player);
//			}else{
//				bc.spawnBomb(player.getX(), player.getY(), player);
//			}
		}
	}
	public float getRealX() {
		return player.getRealX();
	}

	public float getRealY() {
		return player.getRealY();
	}

	public void addScore(int score) {
		player.addScore(score);
	}
	
}
