package game.models.tests;

import static org.junit.Assert.fail;

import game.models.PowerUpElement;

import org.junit.BeforeClass;
import org.junit.Test;

public class PowerUpTest{
	@BeforeClass
	public static void setUpBeforeTest() throws Exception{
	}
	
	
	@Test
	public void test(){
	
		PowerUpElement powerupe =  new PowerUpElement(0,0, null);
		
		if(powerupe.getPowerType() == null)
			fail("Power up element type must be specified, or its not implemented yet.");
		else
			System.out.println("You picked up a Power UP.");
	}
	
}

