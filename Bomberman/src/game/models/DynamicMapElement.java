package game.models;

public abstract class DynamicMapElement extends MapElement {
	
	private int prevX;
	private int prevY;

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
