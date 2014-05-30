package game.factories;

import game.constants.Constants;
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
	private int brickWallCount;
	private int tileCountX;
	private int tileCountY;

	// public LevelFactory(Player player){
	// this.player = player;
	// }

	public Map generateLevel(int dificulty, Player player) {
		brickWalls = new LinkedList<BrickWall>();
		this.powerUps = new LinkedList<PowerUpElement>();
		this.door = null;

		tileCountX = Constants.MAP_BASE_TILECOUNTX + dificulty * 2;
		tileCountY = Constants.MAP_BASE_TILECOUNTY + dificulty * 2;

		Map map = new Map(tileCountX, tileCountY);

		// Main structure
		createMainStructure(map);

		// player
		player.initLoc(1, 1);
		map.addMapElement(player);

		brickWallCount = ((tileCountX / 2 + 1) * (tileCountY - 2) + (tileCountX / 2)
				* ((tileCountY - 1) / 2)) / 3;

		// door
		placeDoor(map);

		// powerup
		placePowerUp(map);

		// add brick walls last
		placeBrickWalls(map);
		
		return map;
	}

	private void placeBrickWalls(Map map) {
		for (int i = 0; i < brickWallCount; i++) {
			Vector2f tempVec = getValidRandomLoc(tileCountX, tileCountY);
			Cell cell = map.getCellAt((int) tempVec.x, (int) tempVec.y);
			if (cell.isContains(ElementType.BrickWall)
					|| cell.isContains(ElementType.Enemy)) {
				i--;
				continue;
			}
			BrickWall b = new BrickWall((int) tempVec.x, (int) tempVec.y);
			brickWalls.add(b);
			map.addMapElement(b);
		}
	}

	private void placePowerUp(Map map) {
		Vector2f tempVec = getValidRandomLoc(tileCountX, tileCountY);
		PowerUpElement pue = new PowerUpElement((int) tempVec.x,
				(int) tempVec.y, PowerUpType.getRandomType());
		map.addMapElement(pue);
		powerUps.add(pue);
		BrickWall b = new BrickWall((int) tempVec.x, (int) tempVec.y);
		map.addMapElement(b);
		brickWalls.add(b);
		brickWallCount--;
	}

	private void placeDoor(Map map) {
		Vector2f tempVec = getValidRandomLoc(tileCountX, tileCountY);
		this.door = new Door((int) tempVec.x, (int) tempVec.y);
		map.addMapElement(this.door);
		// hide door
		BrickWall b = new BrickWall((int) tempVec.x, (int) tempVec.y);
		map.addMapElement(b);
		brickWalls.add(b);
		brickWallCount--;
	}

	private void createMainStructure(Map map) {
		for (int i = 0; i < tileCountY; i++) {
			for (int j = 0; j < tileCountX; j++) {
				if (i == 0 || i == tileCountY - 1 || j == 0
						|| j == tileCountX - 1 || (i % 2 == 0 && j % 2 == 0))
					map.addMapElement(new SolidWall(j, i));
			}
		}
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

	private Vector2f getValidRandomLoc(int xLimit, int yLimit) {
		Vector2f result = new Vector2f();
		Random rand = new Random();
		boolean valid = false;
		int x, y;
		do {
			x = rand.nextInt(xLimit - 2) + 1;
			if (x % 2 == 1) {
				y = rand.nextInt(yLimit - 3) + 1;
			} else {
				y = 2 * rand.nextInt(xLimit / 2 - 3) + 1;
			}
			if (x + y > 3) { // NEXT LEVEL PROGRAMMING RIGHT HERE
				valid = true;
			}
		} while (!valid);

		result.x = x;
		result.y = y;

		return result;
	}
}
