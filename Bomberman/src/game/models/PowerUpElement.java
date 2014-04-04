package game.models;


public class PowerUpElement extends MapElement{

	private PowerUp powerType;
	private boolean available;
	private boolean taken;
	
	public PowerUpElement(int x, int y, PowerUp type) {
		super(x, y, ElementType.PowerUp);
		
		this.powerType = type;
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

	public PowerUp getPowerType() {
		return powerType;
	}
	
}
