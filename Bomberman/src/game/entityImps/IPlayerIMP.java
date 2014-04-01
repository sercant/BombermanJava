package game.entityImps;

import game.entities.Direction;
import game.entities.Door;
import game.entities.Map;
import game.entities.Player;
import game.entities.PowerUp;
import game.entities.SolidWall;
import game.gui.states.Play;
import game.gui.test.Game;
import game.interfaces.IPlayer;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class IPlayerIMP extends Player implements IPlayer {

	private Map map;
	private float moveTimer;
	private float smoothShift;
	private StateBasedGame game;
	private float realX;
	private float realY;
	
	public IPlayerIMP(int x, int y, Direction dir, StateBasedGame game) {
		super(x, y, dir);
		// TODO Auto-generated constructor stub
		resetMoveTimer();
		this.game = game;
		realX = x;
		realY = y;
		smoothShift = 0;
	}

	public IPlayerIMP(int x, int y, Direction dir, int lifeCount,
			int bombCount, int explosionRange, float moveSpeed) {
		super(x, y, dir, lifeCount, bombCount, explosionRange, moveSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Map map, int delta) {
		// TODO Auto-generated method stub
		this.map = map;
		int[][] colMap = map.getColMap();
		if(isMoving()){
			((Play) game.getCurrentState()).getElementPainter().startPlayerAnim(getCurrentDir());
		}else
			((Play) game.getCurrentState()).getElementPainter().stopPlayerAnim(getCurrentDir());
		
		if(isMoving()){
			moveTimer -= delta;
			smoothShift =(float) delta / ((float)300.f / getMoveSpeed());
			
			if(realX > getX())
				realX -= smoothShift;
			else if(realX < getX())
				realX += smoothShift;
			if(realY > getY())
				realY -= smoothShift;
			else if(realY < getY())
				realY += smoothShift;
			
			if(moveTimer < 0){
				setMoving(false);
				resetMoveTimer();
				smoothShift = 0;
				realX = getX();
				realY = getY();
			}
		}
		
		if(colMap[getY()][getX()] == Door.ID && map.getDoor().isOpen()){
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
		moveTimer = 300.f / getMoveSpeed();
	}

	@Override
	public void move(Direction dir) {
		// TODO Auto-generated method stub
		int[][] colMap = map.getColMap();
		int x = this.getX(), y = this.getY();
		
		if(!isMoving() && dir == Direction.Up && colMap[y-1][x] != SolidWall.ID){
			setMoving(true);
			setCurrentDir(Direction.Up);
			setY(y-1);
			if(colMap[y][x] == Player.ID)
				colMap[y][x] = 0;
			if(colMap[y-1][x] != Door.ID)
				colMap[y-1][x] = Player.ID;
			map.setColMap(colMap);
		}else if(!isMoving() && dir == Direction.Down && colMap[y+1][x] != SolidWall.ID){
			setMoving(true);
			setCurrentDir(Direction.Down);
			setY(y+1);
			if(colMap[y][x] == Player.ID)
				colMap[y][x] = 0;
			if(colMap[y+1][x] != Door.ID)
				colMap[y+1][x] = Player.ID;
			map.setColMap(colMap);
		}else if(!isMoving() && dir == Direction.Left && colMap[y][x-1] != SolidWall.ID){
			setMoving(true);
			setCurrentDir(Direction.Left);
			setX(x-1);
			if(colMap[y][x] == Player.ID)
				colMap[y][x] = 0;
			if(colMap[y][x-1] != Door.ID)
				colMap[y][x-1] = Player.ID;
			map.setColMap(colMap);
		}else if(!isMoving() && dir == Direction.Right && colMap[y][x+1] != SolidWall.ID){
			setMoving(true);
			setCurrentDir(Direction.Right);
			setX(x+1);
			if(colMap[y][x] == Player.ID)
				colMap[y][x] = 0;
			if(colMap[y][x+1] != Door.ID)
				colMap[y][x+1] = Player.ID;
			map.setColMap(colMap);
		}
	}
	
	@Override
	public void powerUp(PowerUp power){
		super.powerUp(power);
		if(power == PowerUp.Speed){
			resetMoveTimer();
		}
	}

	public float getRealX() {
		return realX;
	}

	public float getRealY() {
		return realY;
	}
	
}
