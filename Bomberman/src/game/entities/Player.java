package game.entities;

import org.newdawn.slick.geom.Vector2f;

public class Player {
	//for prev loc
	private Vector2f prevLoc;// these values might be changed to plain int
	//current loc
	private Vector2f currentLoc;
	//Player direction
	private Direction direction;
	//prev direction
	private Direction prevDirection;
	//life count
	private int lives;
	//bombCount
	private int bombCount;
	//active Bomb count
	private int activeBombCount;
	//explosion range
	private int explosionRange;
	//move speed
	private float moveSpeed;
	
	private boolean isAlive;
	
	private int score;
	
	

	public Player(Vector2f loc, Direction dir){
		this(loc, dir, 3, 1, 1, .1f);
	}
	
	public Player(Vector2f loc, Direction dir, int lifeCount, int bombCount, int explosionRange, float moveSpeed) {
		//This way is not appropriate make setters for this variables and checks
		currentLoc = loc;
		prevLoc = currentLoc;
		direction = dir;
		
		lives = lifeCount;
		
		this.bombCount = bombCount;
		
		this.explosionRange = explosionRange;
		
		this.moveSpeed = moveSpeed;
		
		isAlive = true;
		
		score = 0;
	}
	
	public void kill(){
		if(isAlive)
			lives--;
		if(lives < 0)
			isAlive = false;
	}
	public void addScore(int score){
		this.score += score;
	}
	public void powerUp(PowerUp power){
		switch (power) {
		case Speed:
			moveSpeed = moveSpeed < 5.f ? moveSpeed + .1f : moveSpeed;
			break;
		case BombCount:
			bombCount = bombCount < 7 ? bombCount + 1 : bombCount;
			break;
		case ExplosionRange:
			explosionRange = explosionRange < 8 ? explosionRange + 1 : explosionRange;
			break;
		default:
			break;
		}
	}
	
	public void setCurrentLoc(Vector2f currentLoc) {
		prevLoc = currentLoc;
		this.currentLoc = currentLoc;
	}

	public void setDirection(Direction direction) {
		prevDirection = direction;
		this.direction = direction;
	}

	public void setActiveBombCount(int activeBombCount) {
		this.activeBombCount = activeBombCount;
	}

	public Direction getPrevDirection() {
		return prevDirection;
	}

	public Vector2f getPrevLoc() {
		return prevLoc;
	}

	public Vector2f getCurrentLoc() {
		return currentLoc;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getLives() {
		return lives;
	}

	public int getBombCount() {
		return bombCount;
	}

	public int getActiveBombCount() {
		return activeBombCount;
	}

	public int getExplosionRange() {
		return explosionRange;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
