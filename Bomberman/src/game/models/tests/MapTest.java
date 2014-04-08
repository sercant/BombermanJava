package game.models.tests;

import static org.junit.Assert.fail;
import game.models.Direction;
import game.models.ElementType;
import game.models.Map;
import game.models.Player;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapTest {
	private Map map;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		map = new Map(11, 19);
	}

	@Test
	public void testAddElement() {
		Player player = new Player(0, 0, Direction.Down);
		map.addMapElement(player);
		if(map.getCellAt(0, 0).isContains(ElementType.Player)){
			System.out.println("addelement is working");
		}else
			fail("addelement is not working");
	}
}
