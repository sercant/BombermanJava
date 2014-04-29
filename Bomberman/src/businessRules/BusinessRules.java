package businessRules;

import org.newdawn.slick.Input;

public class BusinessRules {
	
	public static final String gameName;
	public static final float moveSpeed;
	public static final float explosionRange;
	public static final int detonationTime;
	public static final int activeBombCount;
	public static final int flameDuration;
	public static final int lifeCount;
	public static final int bombCount;
	public static final int KEY_UP;
	public static final int KEY_DOWN;
	public static final int KEY_LEFT;
	public static final int KEY_RIGHT;
	public static final int BrickWallScore;
	public static final int PowerUpScore;
	public static final int SCALE;
	public static final int TILESIZE;
	
	

static{
	
	bombCount = 4;
	explosionRange = 4;
	detonationTime = 3000;
	activeBombCount = 1;
	flameDuration = 1000;
	moveSpeed = 0.6f;
	lifeCount = 3;
	KEY_UP = Input.KEY_UP;
	KEY_DOWN = Input.KEY_DOWN;
	KEY_LEFT = Input.KEY_LEFT;
	KEY_RIGHT = Input.KEY_RIGHT;
	BrickWallScore = 50;
	PowerUpScore = 100;
	SCALE = 4;
	TILESIZE = 16 * SCALE;
	gameName = "Bomberman";
}


public static float getExplosionrange() {
	return explosionRange;
}

public static int getFlameduration() {
	return flameDuration;
}

public static int getLifecount() {
	return lifeCount;
}

public static float getMovespeed() {
	return moveSpeed;
}

public static int getBombcount(){
	return bombCount;
}

public static float getExplosionRange(){
	return explosionRange;
}

public static int getDetonationtime(){
	return detonationTime;
}

public static int getActivebombcount(){
	return activeBombCount;
}

public static int getKeyUp() {
	return KEY_UP;
}

public static int getKeyDown() {
	return KEY_DOWN;
}

public static int getKeyLeft() {
	return KEY_LEFT;
}

public static int getKeyRight() {
	return KEY_RIGHT;
}

public static int getBrickwallscore() {
	return BrickWallScore;
}

public static int getPowerupscore() {
	return PowerUpScore;
}

public static int getScale() {
	return SCALE;
}

public static int getTilesize() {
	return TILESIZE;
}

public static String getGamename() {
	return gameName;
}




}