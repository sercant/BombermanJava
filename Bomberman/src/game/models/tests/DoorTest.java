package game.models.tests;

import static org.junit.Assert.*;
import game.models.Door;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DoorTest {
	private Door door;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		door = new Door(0, 0);
	}

	@Test
	public void testOpen() {
		door.open();
		if(door.isOpen()){
			System.out.println("Open is working");
		}else
			fail("Open is not working");
	}
	@Test
	public void testClose() {
		door.open();
		door.close();
		if(!door.isOpen()){
			System.out.println("Close is working");
		}else
			fail("Close is not working");
	}
}
