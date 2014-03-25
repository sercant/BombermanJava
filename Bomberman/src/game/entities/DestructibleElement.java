package game.entities;


public class DestructibleElement extends MapElement{
	private boolean destroyed;

	
	public DestructibleElement(int x, int y) {
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
