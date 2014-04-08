package game.gui.camera;

import org.newdawn.slick.GameContainer;

public class Camera {
	
	private float cameraX;
	
	private float cameraY;
	
	private GameContainer gc;
	
	private int mapHeight;
	
	private int mapWidth;
	
	public Camera(GameContainer gc, int mapWidth, int mapHeight) {
		
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		
		this.cameraX = 0;
		this.cameraY = 0;
		
		this.gc = gc;
	}
	  
	public void centerOn(float x, float y) {
	//try to set the given position as center of the camera by default
			cameraX = x - gc.getWidth()  / 2;
			cameraY = y - gc.getHeight() / 2; // this is for top spacing
			
			//if the camera is at the right or left edge lock it to prevent a black bar
			if(cameraX + gc.getWidth() >= mapWidth) cameraX = mapWidth - gc.getWidth() ;
			if(cameraX < 0) cameraX = 0;
			
			//if the camera is at the top or bottom edge lock it to prevent a black bar
			if(cameraY + gc.getHeight() >= mapHeight) cameraY = mapHeight - gc.getHeight() ;
			if(cameraY < 0) cameraY = 0;
	}

	public float getCameraX() {
		return cameraX;
	}

	public float getCameraY() {
		return cameraY;
	}   
}