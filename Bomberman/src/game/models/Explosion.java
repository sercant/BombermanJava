package game.models;

import game.constants.Constants;

public class Explosion extends IndestructibleElement{

	//there might be a delta to hold how much the explosion going to appear on screen
	private int flameDuration;
	
	public Explosion(int x, int y) {
		super(x, y, ElementType.Explosion);
		this.flameDuration = Constants.DURATION_TIME_FLAME;
	}
	
	public boolean isExpired(int delta){
		boolean result = false;
		flameDuration -= delta;
		if(flameDuration <= 0){
			result = true;
		}
		return result;
	}
}
