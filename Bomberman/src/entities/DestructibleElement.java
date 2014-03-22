package entities;

import org.newdawn.slick.geom.Vector2f;

public class DestructibleElement extends MapElement{
	private boolean destroyed;

	public DestructibleElement(Vector2f loc) {
		super(loc);
		// TODO Auto-generated constructor stub
	}
	
	public DestructibleElement(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
}
