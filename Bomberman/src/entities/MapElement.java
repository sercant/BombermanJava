package entities;

import org.newdawn.slick.geom.Vector2f;

public class MapElement {
	
	private Vector2f location;

	public MapElement(Vector2f loc) {
		this.location = loc;
    }
	
    public MapElement(float x, float y) {
        location = new Vector2f();
        location.x = x;
        location.y = y;
    }

    public float getX() {
        return location.x;
    }

    public void setX(int x) {
        this.location.x = x;
    }

    public float getY() {
        return location.y;
    }

    public void setY(int y) {
        this.location.y = y;
    }
}
