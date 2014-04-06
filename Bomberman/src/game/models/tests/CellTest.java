package game.models.tests;

import static org.junit.Assert.fail;
import game.models.Cell;
import game.models.Direction;
import game.models.Door;
import game.models.MapElement;
import game.models.Player;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CellTest {
	Cell cell;
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
	public void test() {
		LinkedList<MapElement> el = cell.getMapElements();
		java.util.Iterator<MapElement> iterator = el.descendingIterator();
		
		while(iterator.hasNext()){
			MapElement e = (MapElement) iterator.next();
			System.out.println(e.getType());
		}
		fail("Not yet implemented");
	}

}
