package game.factories;

import game.constants.Constants;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageFactory {
	
	private static Image solidWallImage;
	private static Image brickWallImage;
	private static Image bombImage;
	private static Image doorImage;
	private static Image explosionImage;
	private static Image playerImage;
	private static Image powerUpImage;
	
	public static Image getSolidWallImage() throws SlickException{
		if(solidWallImage == null){
			solidWallImage = new Image(Constants.IMAGE_LOC_SOLIDWALL);
		}
		return solidWallImage;
	}
	
	public static Image getBrickWallImage() throws SlickException{
		if(brickWallImage == null){
			brickWallImage = new Image(Constants.IMAGE_LOC_BRICKWALL);
		}
		return brickWallImage;
	}
	public static Image getBombImage() throws SlickException{
		if(bombImage == null){
			bombImage = new Image(Constants.IMAGE_LOC_BOMB);
		}
		return bombImage;
	}
	public static Image getDoorImage() throws SlickException{
		if(doorImage == null){
			doorImage = new Image(Constants.IMAGE_LOC_DOOR);
		}
		return doorImage;
	}
	public static Image getExplosionImage() throws SlickException{
		if(explosionImage == null){
			explosionImage = new Image(Constants.IMAGE_LOC_EXPLOSION);
		}
		return explosionImage;
	}
	public static Image getPlayerImage() throws SlickException{
		if(playerImage == null){
			playerImage = new Image(Constants.IMAGE_LOC_PLAYER);
		}
		return playerImage;
	}
	public static Image getPowerUpImage() throws SlickException{
		if(powerUpImage == null){
			powerUpImage = new Image(Constants.IMAGE_LOC_POWERUP);
		}
		return powerUpImage;
	}
	
}
