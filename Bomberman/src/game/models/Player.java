package game.models;


public class Player extends DynamicMapElement{

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
	
	private boolean alive;
	
	private boolean killed;
	
	private boolean moving;
	
	private int score;
	
	private Direction currentDir;
	/**
	 * Simple constructor method.
	 * @param x	X coordinate of the player.
	 * @param y Y coordinate of the player.
	 * @param dir Direction of the player.
	 */
	public Player(int x, int y, Direction dir){
		this( x, y, dir, 3, 1, 5, .6f);
	}
	/**
	 * Detailed constructor method.
	 * @param x	X coordinate of the player.
	 * @param y Y coordinate of the player.
	 * @param dir Direction of the player.
	 * @param lifeCount Number of lives of player.
	 * @param bombCount	Bomb count of the player.
	 * @param explosionRange Explosion range of bombs planted by this player.
	 * @param moveSpeed	Movement speed of the player.
	 */
	public Player(int x, int y, Direction dir, int lifeCount, int bombCount, int explosionRange, float moveSpeed) {

		super(x, y, ElementType.Player);
		
		lives = lifeCount;
		
		this.bombCount = bombCount;
		
		this.explosionRange = explosionRange;
		
		this.moveSpeed = moveSpeed;
		
		alive = true;
		
		moving = false;
		
		score = 0;
		
		currentDir = dir;
		
		killed = false;
	}
	/**
	 * Kills player. Player loses 1 life.
	 */
	public void kill(){
		killed = true;
		if(alive)
			lives--;
		if(lives < 0)
			alive = false;
	}
	/**
	 * Adds score points to player's current score.
	 * @param score Score point amount to be added.
	 */
	public void addScore(int score){
		this.score += score;
	}
	/**
	 * Increases stats of the player by the given power up type.
	 * @param power Which power up is acquired.
	 */
	public void powerUp(PowerUpType power){
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

	public void setActiveBombCount(int activeBombCount) {
		this.activeBombCount = activeBombCount;
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
		return alive;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void setBombCount(int bombCount) {
		this.bombCount = bombCount;
	}

	public void setExplosionRange(int explosionRange) {
		this.explosionRange = explosionRange;
	}

	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public Direction getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(Direction currentDir) {
		this.currentDir = currentDir;
	}
	public boolean isKilled() {
		return killed;
	}
	public void setKilled(boolean killed) {
		this.killed = killed;
	}
}
