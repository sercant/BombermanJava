package game.gui.painter;

import game.entities.Direction;
import game.entities.Door;
import game.entities.Map;
import game.entities.MapElement;
import game.entities.SolidWall;
import game.entityImps.IPlayerIMP;
import game.gui.camera.Camera;
import game.gui.test.Game;

import java.util.LinkedList;
import java.util.ListIterator;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

public class ElementPainter {
	//replace this part with sprites
	private Image solidWallIMG;
	private Image brickWallIMG;
	private Image bombIMG;
	private Image doorIMG;
	private Image explosionIMG;
	
	private Image playerIMG;
	private SpriteSheet playerSprite;
	private Animation playerAnim;
	
	private Image powerUpIMG;
	
	private int topSpacing;
	private Rectangle topRect;
	private GradientFill topRectFill;
	
	private Camera cam;
	private Map map;
	private int delta;
	private Graphics g;
	private float topShift;
	private float sideShift;
	
	public ElementPainter(Map map, Camera cam, Image solidWallIMG, Image brickWallIMG, Image bombIMG, Image doorIMG, Image explosionIMG, Image playerIMG, Image powerUpIMG){
		this.solidWallIMG = filterAndScale(solidWallIMG);
		this.brickWallIMG = filterAndScale(brickWallIMG);
		this.bombIMG = filterAndScale(bombIMG);
		this.doorIMG = filterAndScale(doorIMG);
		this.explosionIMG = filterAndScale(explosionIMG);
		
		this.playerIMG = filterAndScale(playerIMG);
		this.playerSprite = new SpriteSheet(this.playerIMG, Game.TILESIZE, Game.TILESIZE);
		this.playerAnim = new Animation(false);
		for(int i = 0; i < 4 ;i++)
			this.playerAnim.addFrame(playerSprite.getSubImage(0, i), 100);
		this.playerAnim.setCurrentFrame(1);
		
		this.powerUpIMG = filterAndScale(powerUpIMG);
		this.cam = cam;
		this.map = map;
		
		topRect = new Rectangle(0, 0, 1024, Game.TILESIZE);
		topRectFill = new GradientFill(0, 0, Color.gray, topRect.getMaxX(), topRect.getMaxY(), Color.gray);
		topSpacing = Game.TILESIZE;
		
		
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
		topShift = topSpacing - cam.getCameraY();
		sideShift = -cam.getCameraX();
		
		g.setColor(Color.white);
		
		LinkedList<SolidWall> solidWalls = map.getSolidWalls();
		
		@SuppressWarnings("rawtypes")
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
		
		IPlayerIMP player = (IPlayerIMP) map.getPlayer();
		
		if(player != null){
			g.setColor(Color.black);
			playerAnim.draw(player.getRealX() * Game.TILESIZE + sideShift, player.getRealY() * Game.TILESIZE + topShift);
			g.setColor(Color.white);
		}
		
		//Top info
		g.fill(topRect, topRectFill);//fix 800 later to game width
		g.drawString("LIVES: " + player.getLives(), 20, topRect.getHeight() / 2);
		g.drawString("SCORE: " + player.getScore(), 200, topRect.getHeight() / 2);
	}
	
	private void drawElement(Image i, MapElement e){
		g.setColor(Color.black);
		i.draw(e.getX() * Game.TILESIZE + sideShift, e.getY() * Game.TILESIZE + topShift);
		g.setColor(Color.white);
	}
	
	public void startPlayerAnim(Direction dir){
		switch (dir) {
		case Down:
			playerAnim.setAutoUpdate(true);
			break;

		default:
			break;
		}
	}
	public void stopPlayerAnim(Direction dir) {
		switch (dir) {
		case Down:
			playerAnim.setAutoUpdate(false);
			playerAnim.setCurrentFrame(1);
			break;

		default:
			break;
		}
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
