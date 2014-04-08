package game.models.tests;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import game.models.Bomb;


public class BombExplosionTest {
	@BeforeClass
	public static void setUpBeforeTest() throws Exception{
	}
	
	@Test
	public void test(){
		
		int delta = 200;
		Bomb bomb = new Bomb(0, 0, 1, 700);
		int expectedLoopCount = (int) (bomb.getDetonationTime() / delta);	
		int i;
		
		for(i = 0; i < expectedLoopCount + 5; i++) {
			if(bomb.isExploded(delta)){
					System.out.println("Bomb exploded.");
					break;
			}		
		}
		if(i > expectedLoopCount)
			fail("Bomb didn't explode.");
	}
}