package game.models;


public abstract class MapElement {
	protected ElementType type;
	protected int x;
	protected int y;
	protected float realX;
	protected float realY;
	/**
	 * Constructor method for map element.
	 * @param x X coordinate of the map element.
	 * @param y Y coordinate of the map element.
	 * @param type Type of the element.
	 */
    public MapElement(int x, int y, ElementType type) {
        this.x = x;
        this.y = y;
        this.realX = x;
        this.realY = y;
        this.type = type;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public ElementType getType(){
    	return type;
    }

	public float getRealX() {
		return realX;
	}

	public void setRealX(float realX) {
		this.realX = realX;
	}

	public float getRealY() {
		return realY;
	}

	public void setRealY(float realY) {
		this.realY = realY;
	}
}
