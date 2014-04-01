package game.models;

public class Door extends DestructibleElement{
	
	public static final int ID = 3;
	private boolean open;

	public Door(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.open = false;
	}

	public boolean isOpen() {
		return open;
	}

	public void open() {
		this.open = true;
	}
	
	public void close() {
		this.open = false;
	}
	
}
