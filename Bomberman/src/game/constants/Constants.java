package game.constants;

import org.newdawn.slick.Input;

public class Constants {
	
	public static final String GAME_NAME = "Bomberman";
	
	public static final float PLAYER_BASE_MOVE_SPEED = 0.6f;
	public static final int PLAYER_BASE_EXPLOSION_RANGE = 2;
	public static final int PLAYER_BASE_ACTIVE_BOMB_COUNT = 1;
	public static final int PLAYER_BASE_LIFE_COUNT = 3;
	public static final int PLAYER_BASE_BOMB_COUNT = 1;
	
	public static final int PLAYER_STEP_BOMB_COUNT = 1;
	public static final float PLAYER_STEP_MOVE_SPEED = .2f;
	public static final int PLAYER_STEP_EXPLOSION_RANGE = 1;
	
	public static final float PLAYER_MAX_MOVE_SPEED = 1.5f;
	public static final int PLAYER_MAX_BOMB_COUNT = 7;
	public static final int PLAYER_MAX_EXPLOSION_RANGE = 6;
	
	public static final int PLAYER_KEY_UP = Input.KEY_UP;
	public static final int PLAYER_KEY_DOWN = Input.KEY_DOWN;
	public static final int PLAYER_KEY_LEFT = Input.KEY_LEFT;
	public static final int PLAYER_KEY_RIGHT = Input.KEY_RIGHT;
	
	public static final int DURATION_TIME_FLAME = 1000;
	public static final int DURATION_TIME_BOMB = 3000;

	public static final int SCORE_BRICKWALL = 50;
	public static final int SCORE_POWERUP = 100;
	
	public static final int MAP_BASE_TILECOUNTX = 17;
	public static final int MAP_BASE_TILECOUNTY = 11;
	
	public static final int GAME_SCALE = 4;
	public static final int GAME_TILESIZE = 16 * GAME_SCALE;
	
	public static final String IMAGE_LOC_SOLIDWALL = "res/solidWall.png";
	public static final String IMAGE_LOC_BRICKWALL = "res/brickWall.png";
	public static final String IMAGE_LOC_BOMB = "res/bomb.png";
	public static final String IMAGE_LOC_DOOR = "res/door.png";
	public static final String IMAGE_LOC_EXPLOSION = "res/explosion.png";
	public static final String IMAGE_LOC_PLAYER = "res/playerwalk.png";
	public static final String IMAGE_LOC_POWERUP = "res/powerUp.png";
}