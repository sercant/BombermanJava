package game.models;


public class MapElement {
	private ElementType type;
	private int x;
	private int y;
	
    public MapElement(int x, int y, ElementType type) {
        this.x = x;
        this.y = y;
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
}
