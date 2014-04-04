package game.models;

import game.gui.test.Game;

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
	private Cell[][] colMap;
	private int tileCountX;
	private int tileCountY;
	
	public Map(int tileCountX, int tileCountY) {
		solidWalls = new LinkedList<SolidWall>();
		
		brickWalls = new LinkedList<BrickWall>();
		
		bombs = new LinkedList<Bomb>();
		
		explosions = new LinkedList<Explosion>();
		
		powerUpElements = new LinkedList<PowerUpElement>();
		this.tileCountX = tileCountX;
		this.tileCountY = tileCountY;
		colMap = new Cell[tileCountY][tileCountX];
		for(int i=0; i < tileCountY; i++){
			for(int j=0; j < tileCountX; j++){
				colMap[i][j] = new Cell();
			}
		}
		
		//TODO init door later
		
		//TODO init player later
		
	}

	//TODO updates
	

	public void addSolidWall(SolidWall solidWall){
		solidWalls.add(solidWall);
		colMap[solidWall.getY()][solidWall.getX()].addElement(solidWall);
	}
	
	public void setPlayer(Player player){
		this.player = player;
		colMap[player.getY()][player.getX()].addElement(player);;
	}

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

	public void setDoor(Door door) {
		// TODO Auto-generated method stub
		this.door = door;
		colMap[door.getY()][door.getX()].addElement(door);
	}
	
	public int getWidth(){
		return tileCountX * Game.TILESIZE;
	}
	
	public int getHeight(){
		return tileCountY * Game.TILESIZE + Game.TILESIZE;
	}
	public Cell getCellAt(int x, int y){
		Cell cell;
		if(x < tileCountX && x >= 0 && y < tileCountY && y >= 0)
			cell = colMap[y][x];
		else
			cell = null;
		return cell;
	}
}
