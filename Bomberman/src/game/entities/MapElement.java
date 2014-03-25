package game.entities;


public class MapElement {
	
	private int x;
	private int y;
	
    public MapElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
