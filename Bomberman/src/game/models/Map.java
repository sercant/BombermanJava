package game.models;

import game.gui.main.Game;

import java.util.Iterator;



public class Map {
	
	private Cell[][] cells;
	private int tileCountX;
	private int tileCountY;
	/**
	 * Constructor method.
	 * @param tileCountX Tile count of the x axis.
	 * @param tileCountY Tile count of the y axis.
	 */
	public Map(int tileCountX, int tileCountY){
		this.tileCountX = tileCountX;
		this.tileCountY = tileCountY;
		
		cells = new Cell[tileCountY][tileCountX];
		for(int y=0; y < tileCountY; y++){
			for(int x=0; x < tileCountX; x++){
				cells[y][x] = new Cell();
			}
		}
	}
	public Map(Map map) {
		this.cells = map.getCells();
		this.tileCountX = map.getTileCountX();
		this.tileCountY = map.getTileCountY();
	}
	/**
	 * Adds MapElement to the map. Throws exception if element's x or y coordinates out of bounds.
	 * @param e	MapElement to be added.
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public void addMapElement(MapElement e) throws ArrayIndexOutOfBoundsException{
		if(e.getX() >= tileCountX || e.getY() >= tileCountY)
			throw new ArrayIndexOutOfBoundsException("MapElement " + e + " is out of bounds of Map.");
		else
			if(e != null)
				cells[e.getY()][e.getX()].addElement(e);
	}
	/**
	 * Getter method for width of the map
	 * @return	Width of the map
	 */
	public int getWidth(){
		return tileCountX * Game.TILESIZE;
	}
	/**
	 * Getter method for height of the map
	 * @return	Width of the map
	 */
	public int getHeight(){
		return tileCountY * Game.TILESIZE + Game.TILESIZE;
	}
	/**
	 * Getter method for accessing a Cell at the coordinates x and y.
	 * @param x	X coordinate of the Cell.
	 * @param y	Y coordinate of the Cell.
	 * @return	Cell at given location. Null if given coordinates is out of bounds.
	 */
	public Cell getCellAt(int x, int y){
		Cell cell;
		if(x < tileCountX && x >= 0 && y < tileCountY && y >= 0)
			cell = cells[y][x];
		else
			cell = null;
		return cell;
	}
	/**
	 * Getter method for Cell iterator.
	 * @param x	X coordinate of the Cell.
	 * @param y Y coordinate of the Cell.
	 * @return Cell iterator of given location. Null if given coordinates is out of bounds.
	 */
	public Iterator<MapElement> getCellIteratorAt(int x, int y){
		Iterator<MapElement> it;
		if(x < tileCountX && x >= 0 && y < tileCountY && y >= 0)
			it = cells[y][x].getIterator();
		else
			it = null;
		return it;
	}

	public int getTileCountX() {
		return tileCountX;
	}

	public int getTileCountY() {
		return tileCountY;
	}
	public Cell[][] getCells() {
		return cells;
	}
	
}
