package game.models;

import game.constants.Constants;

public class Enemy extends DynamicMapElement {

	private float moveSpeed;

	private boolean alive;

	private boolean moving;
	
	private int reward;

	public Enemy(int x, int y, ElementType type) {
		this(x, y, type, Constants.ENEMY_MOVE_SPEED, Constants.SCORE_ENEMY);
	}
	
	public Enemy(int x, int y, ElementType type, float moveSpeed, int reward){
		super(x, y, type);
		this.moveSpeed = moveSpeed;
		this.reward = reward;
		this.alive = true;
		this.moving = false;
	}
	
	public void kill() {
		this.alive = false;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public boolean isAlive() {
		return alive;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public int getReward() {
		return reward;
	}
}
