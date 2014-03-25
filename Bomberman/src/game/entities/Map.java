package game.entities;

import java.util.LinkedList;

// We are going to be using LinkedList implementation of list because as it is stated the web-site bellow
// http://www.java-gaming.org/index.php?topic=27017.0

public class Map {
	
	private LinkedList<SolidWall> solidWalls;
	private LinkedList<BrickWall> brickWalls;
	private LinkedList<Bomb> bombs;
	private LinkedList<Explosion> explosions;
	private LinkedList<PowerUpElement> powerUpElements;
	//private LinkedList<Player> players;					//for multiplayer purposes **later**
	private Door door;
	private Player player;									//single player for now
	
	public Map() {
		solidWalls = new LinkedList<SolidWall>();
		
		brickWalls = new LinkedList<BrickWall>();
		
		bombs = new LinkedList<Bomb>();
		
		explosions = new LinkedList<Explosion>();
		
		powerUpElements = new LinkedList<PowerUpElement>();
		
		//TODO init door later
		
		//TODO init player later
	}

	//TODO updates
	
	
	public LinkedList<SolidWall> getSolidWalls() {
		return solidWalls;
	}

	public LinkedList<BrickWall> getBrickWalls() {
		return brickWalls;
	}

	public LinkedList<Bomb> getBombs() {
		return bombs;
	}

	public LinkedList<Explosion> getExplosions() {
		return explosions;
	}

	public LinkedList<PowerUpElement> getPowerUpElements() {
		return powerUpElements;
	}

	public Door getDoor() {
		return door;
	}

	public Player getPlayer() {
		return player;
	}
}
