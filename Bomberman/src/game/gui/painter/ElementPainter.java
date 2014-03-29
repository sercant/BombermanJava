package game.gui.painter;

import game.entities.Door;
import game.entities.Map;
import game.entities.MapElement;
import game.entities.Player;
import game.entities.SolidWall;
import game.gui.camera.Camera;
import game.gui.test.Game;

import java.util.LinkedList;
import java.util.ListIterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ElementPainter {
	//replace this part with sprites
	private Image solidWallIMG;
	private Image brickWallIMG;
	private Image bombIMG;
	private Image doorIMG;
	private Image explosionIMG;
	private Image playerIMG;
	private Image powerUpIMG;
	
	private Camera cam;
	private Map map;
	private int delta;
	private Graphics g;
	
	public ElementPainter(Map map, Camera cam, Image solidWallIMG, Image brickWallIMG, Image bombIMG, Image doorIMG, Image explosionIMG, Image playerIMG, Image powerUpIMG){
		this.solidWallIMG = filterAndScale(solidWallIMG);
		this.brickWallIMG = filterAndScale(brickWallIMG);
		this.bombIMG = filterAndScale(bombIMG);
		this.doorIMG = filterAndScale(doorIMG);
		this.explosionIMG = filterAndScale(explosionIMG);
		this.playerIMG = filterAndScale(playerIMG);
		this.powerUpIMG = filterAndScale(powerUpIMG);
		this.cam = cam;
		this.map = map;
		
		//sprite init
	}
	
	private Image filterAndScale(Image image){
		if(image != null){
			image.setFilter(Image.FILTER_NEAREST);
			return image.getScaledCopy(Game.SCALE);
		}else
			return null;
	}
	
	public void draw(int delta, Graphics g) {
		this.delta = delta;
		this.g = g;
		
		g.setColor(Color.white);
		
		LinkedList<SolidWall> solidWalls = map.getSolidWalls();
		
		ListIterator iterator = solidWalls.listIterator();
		
		while(iterator.hasNext()){
			SolidWall solidWall = (SolidWall) iterator.next();
			
			if(solidWall != null){
				drawElement(solidWallIMG, solidWall);
			}
		}
		
		Door door = map.getDoor();
		
		if(door != null){
			drawElement(doorIMG, door);
		}
		
		Player player = map.getPlayer();
		
		if(player != null){
			drawElement(playerIMG, player);
		}
		
		
	}
	
	private void drawElement(Image i, MapElement e){
		g.setColor(Color.black);
		i.draw(e.getX() * Game.TILESIZE - cam.getCameraX(), e.getY() * Game.TILESIZE - cam.getCameraY());
		g.setColor(Color.white);
	}
	
	public Image getSolidWallIMG() {
		return solidWallIMG;
	}

	public void setSolidWallIMG(Image solidWallIMG) {
		this.solidWallIMG = solidWallIMG;
	}

	public Image getBrickWallIMG() {
		return brickWallIMG;
	}

	public void setBrickWallIMG(Image brickWallIMG) {
		this.brickWallIMG = brickWallIMG;
	}

	public Image getBombIMG() {
		return bombIMG;
	}

	public void setBombIMG(Image bombIMG) {
		this.bombIMG = bombIMG;
	}

	public Image getDoorIMG() {
		return doorIMG;
	}

	public void setDoorIMG(Image doorIMG) {
		this.doorIMG = doorIMG;
	}

	public Image getExplosionIMG() {
		return explosionIMG;
	}

	public void setExplosionIMG(Image explosionIMG) {
		this.explosionIMG = explosionIMG;
	}

	public Image getPlayerIMG() {
		return playerIMG;
	}

	public void setPlayerIMG(Image playerIMG) {
		this.playerIMG = playerIMG;
	}

	public Image getPowerUpIMG() {
		return powerUpIMG;
	}

	public void setPowerUpIMG(Image powerUpIMG) {
		this.powerUpIMG = powerUpIMG;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
