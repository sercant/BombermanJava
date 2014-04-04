package game.models;


public class DestructibleElement extends MapElement{
	private boolean destroyed;

	
	public DestructibleElement(int x, int y, ElementType type) {
		super(x, y, type);
		// TODO Auto-generated constructor stub
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
}
