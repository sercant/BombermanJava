package game.models;

import java.util.Iterator;
import java.util.LinkedList;

public class Cell {
	private LinkedList<MapElement> mapElements;
	/**
	 * Constructor method.
	 */
	public Cell(){
		mapElements = new LinkedList<MapElement>();
	}
	/**
	 * Copy constructor.
	 * @param cell
	 */
	public Cell(Cell cell){
		mapElements = cell.getMapElements();
	}
	/**
	 * Adds MapElement to the cell.
	 * @param e MapElement to be added.
	 */
	public void addElement(MapElement e){
		mapElements.add(e);
	}
	/**
	 * Deletes MapElement from the cell.
	 * @param e MapElement to be deleted.
	 * @return True if element is deleted. If there is no such element false. 
	 */
	public boolean deleteElement(MapElement e){
		return mapElements.remove(e);
	}
	/**
	 * Check if the Cell contains given element.
	 * @param type Type of the element that is going to be checked.
	 * @return True if Cell contains this type of element.
	 */
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
	/**
	 * Gets the first occurrence of the given type in the Cell.
	 * @param type Type of the element that is going to be searched.
	 * @return Returns the MapElement if found. Else null is returned.
	 */
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
