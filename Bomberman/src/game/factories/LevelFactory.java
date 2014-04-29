package game.factories;

import game.models.BrickWall;
import game.models.Cell;
import game.models.Door;
import game.models.ElementType;
import game.models.Map;
import game.models.Player;
import game.models.PowerUpElement;
import game.models.PowerUpType;
import game.models.SolidWall;

import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.geom.Vector2f;

public class LevelFactory {
	private LinkedList<BrickWall> brickWalls;
	private LinkedList<PowerUpElement> powerUps;
	private Door door;
	
//	public LevelFactory(Player player){
//		this.player = player;
//	}
	
	public Map generateLevel(int dificulty, Player player){
		brickWalls = new LinkedList<BrickWall>();
		this.powerUps = new LinkedList<PowerUpElement>();
		this.door = null;
		Vector2f tempVec = new Vector2f();
		
		int tileCountX = 17 + dificulty * 2;
		int tileCountY = 11 + dificulty * 2;
		
		Map map = new Map(tileCountX, tileCountY);
		//Main structure
		for (int i = 0; i < tileCountY; i++)
		{
			for (int j = 0; j < tileCountX; j++)
			{
				if(		i == 0 || i == tileCountY - 1 
					||	j == 0 || j == tileCountX - 1 
					||	(i % 2 == 0 && j % 2 == 0))
					map.addMapElement(new SolidWall(j, i));
			}
		}
		//player
		player.initLoc(1, 1);
		map.addMapElement(player);
		
		int brickWallCount = 	(	(tileCountX / 2 + 1) * (tileCountY - 2)
					+ (tileCountX / 2) * ((tileCountY - 1) / 2)
				) 
				/ 3;
		
		//door
		tempVec = getValidRandomLoc(tileCountX, tileCountY);
		this.door = new Door((int)tempVec.x, (int)tempVec.y);
		map.addMapElement(this.door);
		//hide door
		BrickWall b = new BrickWall((int)tempVec.x, (int)tempVec.y);
		map.addMapElement(b);
		brickWalls.add(b);
		brickWallCount--;
		
		//powerup
		tempVec = getValidRandomLoc(tileCountX, tileCountY);
		PowerUpElement pue = new PowerUpElement((int)tempVec.x,
												(int)tempVec.y,
												PowerUpType.getRandomType());
		map.addMapElement(pue);
		powerUps.add(pue);
		b = new BrickWall((int)tempVec.x, (int)tempVec.y);
		map.addMapElement(b);
		brickWalls.add(b);
		brickWallCount--;
		
		//add brick walls last
		
		for(int i = 0; i < brickWallCount; i++){
			tempVec = getValidRandomLoc(tileCountX, tileCountY);
			Cell cell = map.getCellAt((int)tempVec.x, (int)tempVec.y);
			if(		cell.isContains(ElementType.BrickWall)
					|| cell.isContains(ElementType.Enemy)){
				i--;
				continue;
			}
			b = new BrickWall((int)tempVec.x, (int)tempVec.y);
			brickWalls.add(b);
			map.addMapElement(b);
		}
		return map;
	}

	public LinkedList<BrickWall> getBrickWalls() {
		return brickWalls;
	}

	public LinkedList<PowerUpElement> getPowerUps() {
		return powerUps;
	}

	public Door getDoor() {
		return door;
	}
	
	private Vector2f getValidRandomLoc(int xLimit, int yLimit){
		Vector2f result = new Vector2f();
		Random rand = new Random();
		boolean valid = false;
		int x, y;
		do{
			x = rand.nextInt(xLimit - 2) + 1;
			if(x % 2 == 1){
				y = rand.nextInt(yLimit - 3) + 1;
			}else{
				y = 2 * rand.nextInt(xLimit / 2 - 3) + 1;
			}
			if(x + y > 3){	//NEXT LEVEL PROGRAMMING RIGHT HERE
				valid = true;
			}
		}while(!valid);
		
		result.x = x;
		result.y = y;
		
		return result;
	}
}
