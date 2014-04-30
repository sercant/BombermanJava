package game.factories;

import game.constants.Constants;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageFactory {
	
	
	public static Image getSolidWallImage() throws SlickException{
		return new Image(Constants.IMAGE_LOC_SOLIDWALL);
	}
	
	public static Image getBrickWallImage() throws SlickException{
		return new Image(Constants.IMAGE_LOC_BRICKWALL);
	}
	public static Image getBombImage() throws SlickException{
		return new Image(Constants.IMAGE_LOC_BOMB);
	}
	public static Image getDoorImage() throws SlickException{
		return new Image(Constants.IMAGE_LOC_DOOR);
	}
	public static Image getExplosionImage() throws SlickException{
		return new Image(Constants.IMAGE_LOC_EXPLOSION);
	}
	public static Image getPlayerImage() throws SlickException{
		return new Image(Constants.IMAGE_LOC_PLAYER);
	}
	public static Image getPowerUpImage() throws SlickException{
		return new Image(Constants.IMAGE_LOC_POWERUP);
	}
	
}
