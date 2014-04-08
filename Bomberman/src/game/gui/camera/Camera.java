package game.gui.camera;

import org.newdawn.slick.GameContainer;

public class Camera {
	
	private float cameraX;
	
	private float cameraY;
	
	private GameContainer gc;
	
	private int mapHeight;
	
	private int mapWidth;
	/**
	 * Constructor method.
	 * @param gc GameContainer of the game.
	 * @param mapWidth Width of the map.
	 * @param mapHeight Height of the map.
	 */
	public Camera(GameContainer gc, int mapWidth, int mapHeight) {
		
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		
		this.cameraX = 0;
		this.cameraY = 0;
		
		this.gc = gc;
	}
	/**
	 * Centers on the given coordinates.
	 * @param x X coordinate to be centered.
	 * @param y Y coordinate to be centered.
	 */
	public void centerOn(float x, float y) {
	//try to set the given position as center of the camera by default
		float gcWidth = gc.getWidth();
		float gcHeight = gc.getHeight();
		
		cameraX = x - gcWidth  / 2;
		cameraY = y - gcHeight / 2; // this is for top spacing
		
		//if the camera is at the right or left edge lock it to prevent a black bar
		if(cameraX + gcWidth >= mapWidth) cameraX = mapWidth - gcWidth ;
		if(cameraX < 0) cameraX = 0;
		
		//if the camera is at the top or bottom edge lock it to prevent a black bar
		if(cameraY + gcHeight >= mapHeight) cameraY = mapHeight - gcHeight ;
		if(cameraY < 0) cameraY = 0;
	}

	public float getCameraX() {
		return cameraX;
	}

	public float getCameraY() {
		return cameraY;
	}   
}