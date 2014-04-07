package game.models;

import java.util.Iterator;

import game.gui.test.Game;


public class Map {
	
	private Cell[][] cells;
	private int tileCountX;
	private int tileCountY;
	
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
	
	public void addMapElement(MapElement e) throws ArrayIndexOutOfBoundsException{
		if(e.getX() >= tileCountX || e.getY() >= tileCountY)
			throw new ArrayIndexOutOfBoundsException("MapElement " + e + " is out of bounds of Map.");
		else
			if(e != null)
				cells[e.getY()][e.getX()].addElement(e);
	}

	public int getWidth(){
		return tileCountX * Game.TILESIZE;
	}
	
	public int getHeight(){
		return tileCountY * Game.TILESIZE + Game.TILESIZE;
	}
	
	public Cell getCellAt(int x, int y){
		Cell cell;
		if(x < tileCountX && x >= 0 && y < tileCountY && y >= 0)
			cell = cells[y][x];
		else
			cell = null;
		return cell;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return null if the x and y value is out of bounds
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
}
