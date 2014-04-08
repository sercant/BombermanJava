package game.models.tests;

import static org.junit.Assert.fail;
import game.models.Direction;
import game.models.Player;
import game.models.PowerUpType;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {
	private Player player;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		player = new Player(0, 0, Direction.Down);
	}

	@Test
	public void testKill() {
		int prevLifeCount = player.getLives();
		player.kill();
		if(prevLifeCount <= player.getLives())
			fail("kill is not working");
		else
			System.out.println("kill is working");
	}
	
	@Test
	public void testAddScore(){
		int prevScore = player.getScore();
		player.addScore(1000);
		if(player.getScore() <= prevScore)
			fail("addScore is not working");
		else
			System.out.println("addScore working");	
	}
	@Test
	public void testPowerUp(){
		float prevMoveSpeed = player.getMoveSpeed();
		player.powerUp(PowerUpType.Speed);
		if(prevMoveSpeed >= player.getMoveSpeed()){
			fail("powerUp is not working");
		}else
			System.out.println("powerUp is working");
	}
}
