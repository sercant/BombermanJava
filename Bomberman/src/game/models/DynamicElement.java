package game.models;

public class DynamicElement extends MapElement{
	
	private int prevX;
	private int prevY;
	private Direction currentDir;
	
	public DynamicElement(int x, int y, Direction dir) {
		super(x, y);
		// TODO Auto-generated constructor stub
		prevX = x;
		prevY = y;
		currentDir = dir;
	}
	
	public int getPrevX() {
		return prevX;
	}

	public int getPrevY() {
		return prevY;
	}

	public Direction getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(Direction currentDir) {
		this.currentDir = currentDir;
	}
}
