package game.models;

import java.util.Iterator;
import java.util.LinkedList;

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
		
		Iterator<MapElement> iterator = mapElements.descendingIterator();
		
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
		
		Iterator<MapElement> iterator = mapElements.descendingIterator();
		
		while(iterator.hasNext()){
			MapElement e = (MapElement) iterator.next();
			
			if(e != null && e.getType() == type){
				return e;
			}
		}
		return null;
	}
	/**
	 * Returns LIFO iterator
	 * @return LIFO iterator
	 */
	public Iterator<MapElement> getIterator(){
		return mapElements.listIterator();
	}
	
	public LinkedList<MapElement> getMapElements() {
		// TODO Auto-generated method stub
		return mapElements;
	}
	
}
