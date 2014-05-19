package game.controllers;

import game.controllers.interfaces.GeneralController;
import game.models.Cell;
import game.models.Map;
import game.models.MapElement;

import java.util.Iterator;

import org.newdawn.slick.state.StateBasedGame;

public class MapController implements GeneralController{
	private Map map;
	@SuppressWarnings("unused")
	private StateBasedGame game;
	
	public MapController(Map map, StateBasedGame sbg){
		this.map = map;
		this.game = sbg;
	}
	
	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void addMapElement(MapElement e) throws ArrayIndexOutOfBoundsException{
		try{
			map.addMapElement(e);
		}catch(ArrayIndexOutOfBoundsException ex){
			throw ex;
		}
	}

	public int getMapHeight() {
		return map.getHeight();
	}

	public int getMapWidth() {
		return map.getWidth();
	}

	public int getTileCountY() {
		// TODO Auto-generated method stub
		return map.getTileCountY();
	}

	public int getTileCountX() {
		// TODO Auto-generated method stub
		return map.getTileCountX();
	}

	public Iterator<MapElement> getCellIteratorAt(int x, int y) {
		// TODO Auto-generated method stub
		return map.getCellIteratorAt(x, y);
	}

	public Cell getCellAt(int x, int y) {
		// TODO Auto-generated method stub
		return map.getCellAt(x, y);
	}

	public void deleteElementAtCell(int x, int y, MapElement e) {
		map.getCellAt(x, y).deleteElement(e);
	}

	public void addElementToCell(int x, int y, MapElement e) {
		map.getCellAt(x, y).addElement(e);
	}

}
