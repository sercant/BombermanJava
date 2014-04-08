package game.models;


public class PowerUpElement extends MapElement{

	private PowerUpType powerType;
	private boolean available;
	private boolean taken;
	
	public PowerUpElement(int x, int y, PowerUpType type) {
		super(x, y, ElementType.PowerUp);
		if(type != null)
			this.powerType = type;
		else
			this.powerType = PowerUpType.BombCount;
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

	public PowerUpType getPowerType() {
		return powerType;
	}
	
}
