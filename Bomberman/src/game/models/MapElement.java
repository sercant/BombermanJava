package game.models;


public abstract class MapElement {
	private ElementType type;
	private int x;
	private int y;
	private float realX;
	private float realY;
	
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
