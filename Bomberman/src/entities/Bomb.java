package entities;

public class Bomb extends IndestructibleElement{
	
	private int detonationTime;
	private int explosionRange;
	private int delta;
	private boolean exploded;

	public Bomb(int x, int y, int explosionRange, int detonationTime) {
		super(x, y);
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
}
