package entities;

import org.newdawn.slick.geom.Vector2f;

public class PowerUpElement extends MapElement{

	private PowerUp type;
	private boolean available;
	private boolean taken;
	
	public PowerUpElement(float x, float y, PowerUp type) {
		super(x, y);
		init(type);
		// TODO Auto-generated constructor stub
	}
	
	public PowerUpElement(Vector2f loc, PowerUp type) {
		super(loc);
		init(type);
		// TODO Auto-generated constructor stub
	}
	private void init(PowerUp type){
		this.type = type;
		available = false;
		taken = false;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public PowerUp getType() {
		return type;
	}
	
}
