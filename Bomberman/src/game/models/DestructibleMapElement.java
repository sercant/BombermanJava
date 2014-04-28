package game.models;


public abstract class DestructibleMapElement extends MapElement{
	protected boolean destroyed;
	/**
	 * Constructor method.
	 * @param x X coordinate of the destructible map element.
	 * @param y Y coordinate of the destructible map element.
	 * @param type Type of the element.
	 */
	public DestructibleMapElement(int x, int y, ElementType type) {
		super(x, y, type);
	}
	/**
	 * 
	 * @return True if the  element is destroyed. Else false.
	 */
	public boolean isDestroyed() {
		return destroyed;
	}
	/**
	 * Set the destroyed state of the element. 
	 * @param destroyed
	 */
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
}
