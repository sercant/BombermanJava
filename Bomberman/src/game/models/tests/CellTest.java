package game.models.tests;

import static org.junit.Assert.fail;
import game.models.Cell;
import game.models.Direction;
import game.models.Door;
import game.models.ElementType;
import game.models.MapElement;
import game.models.Player;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CellTest {
	private Cell cell;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		cell = new Cell();
		Player player = new Player(0, 0, Direction.Down);
		Door door = new Door(0, 0);
		cell.addElement(door);
		cell.addElement(player);
	}

	@Test
	public void testIterator() {
		LinkedList<MapElement> el = cell.getMapElements();
		java.util.Iterator<MapElement> iterator = el.descendingIterator();
		
		while(iterator.hasNext()){
			MapElement e = (MapElement) iterator.next();
			System.out.println(e.getType());
		}
		
	}

	@Test
	public void testIsContains(){
		Player player = new Player(0, 0, Direction.Down);
		cell.addElement(player);
		if(cell.isContains(ElementType.Player))
			fail("IsContains is not working.");
		else
			System.out.println("IsContains is working");
	}
	@Test
	public void testDeleteElement(){
		Player player = new Player(0, 0, Direction.Down);
		cell.addElement(player);
		cell.deleteElement(player);
		if(cell.isContains(ElementType.Player))
			fail("deleteElement is not working.");
		else
			System.out.println("deleteElement is working");
	}
	@Test
	public void testAddElement(){
		Player player = new Player(0, 0, Direction.Down);
		cell.addElement(player);
		if(cell.isContains(ElementType.Player))
			System.out.println("addElement is working");
		else
			fail("addElement is not working.");
	}

}
