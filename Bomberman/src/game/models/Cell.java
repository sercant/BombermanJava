package game.models;

import java.util.LinkedList;
import java.util.ListIterator;

public class Cell {
	private LinkedList<MapElement> mapElements;
	
	public Cell(){
		mapElements = new LinkedList<MapElement>();
	}
	
	public Cell(Cell cell){
		mapElements = cell.getMapElements();
	}
	
	public void addElement(MapElement e){
		mapElements.add(e);
	}
	
	public boolean deleteElement(MapElement e){
		return mapElements.remove(e);
	}
	
	public boolean isContains(ElementType type){
		boolean result = false;
		@SuppressWarnings("rawtypes")
		ListIterator iterator = mapElements.listIterator();
		
		while(iterator.hasNext()){
			MapElement e = (MapElement) iterator.next();
			
			if(e != null && e.getType() == type){
				result = true;
				break;
			}
		}
		return result;
	}

	public MapElement getElement(ElementType type){
		@SuppressWarnings("rawtypes")
		ListIterator iterator = mapElements.listIterator();
		
		while(iterator.hasNext()){
			MapElement e = (MapElement) iterator.next();
			
			if(e != null && e.getType() == type){
				return e;
			}
		}
		return null;
	}
	
	private LinkedList<MapElement> getMapElements() {
		// TODO Auto-generated method stub
		return mapElements;
	}
	
}
