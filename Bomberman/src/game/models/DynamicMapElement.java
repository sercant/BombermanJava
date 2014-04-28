package game.models;

public abstract class DynamicMapElement extends MapElement {
	
	protected int prevX;
	protected int prevY;
	/**
	 * Constructor method.
	 * @param x X coordinate of the dynamic map element.
	 * @param y Y coordinate of the dynamic map element.
	 * @param type Type of the element.
	 */
	public DynamicMapElement(int x, int y, ElementType type) {
		super(x, y, type);
		this.prevX = x;
		this.prevY = y;
	}

	public int getPrevX() {
		return prevX;
	}

	public void setPrevX(int prevX) {
		this.prevX = prevX;
	}

	public int getPrevY() {
		return prevY;
	}

	public void setPrevY(int prevY) {
		this.prevY = prevY;
	}

}
