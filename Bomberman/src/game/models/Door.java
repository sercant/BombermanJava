package game.models;

public class Door extends DestructibleMapElement{
	
	private boolean open;
	/**
	 * Constructor method.
	 * @param x	X coordinate of the door.
	 * @param y Y coordinate of the door.
	 */
	public Door(int x, int y) {
		super(x, y, ElementType.Door);
		// TODO Auto-generated constructor stub
		this.open = false;
	}
	/**
	 * 
	 * @return True if the door is open. Else false.
	 */
	public boolean isOpen() {
		return open;
	}
	/**
	 * Opens the door.
	 */
	public void open() {
		this.open = true;
	}
	/**
	 * Closes the door.
	 */
	public void close() {
		this.open = false;
	}
	
}
