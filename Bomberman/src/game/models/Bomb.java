package game.models;

public class Bomb extends DestructibleMapElement{
	
	private int detonationTime;
	private int explosionRange;
	private int delta;
	private boolean exploded;

	public Bomb(int x, int y, int explosionRange, int detonationTime) {
		super(x, y, ElementType.Bomb);
		this.detonationTime = detonationTime;
		this.explosionRange = explosionRange;
		this.delta = 0;
		this.exploded = false;
	}

	public float getDetonationTime() {
		return detonationTime;
	}

	public int getExplosionRange() {
		return explosionRange;
	}

	public boolean isExploded(int time) {
		this.delta += time;
		
		if (delta >= detonationTime) {
			exploded = true;
		}
		
		return exploded;
	}
	
	public void setExploded(boolean exploded) { // use this in case an explosion intersected with this bomb
		this.exploded = exploded;
	}
	
	public void setDestroyed(boolean destroyed){
		this.destroyed = destroyed;
		if(destroyed){
			this.exploded = true;
		}
	}
}
