package game.gui.camera.tests;

import static org.junit.Assert.fail;
import game.gui.camera.Camera;
import game.gui.test.Game;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class CameraTest {
	private Camera cam;
	int x, y;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		AppGameContainer appgc;
		Game game = new Game("Test");
		
		try {
			appgc = new AppGameContainer(game);
			//appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), true);
			appgc.setDisplayMode(1024, 768, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
			System.out.println("Width: " + game.getContainer().getScreenWidth() + " Height: " + game.getContainer().getScreenHeight());
			cam = new Camera(game.getContainer(), 200, 100);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void test() {
		cam.centerOn(0, 0);
		System.out.println("X: " + cam.getCameraX() + " Y: " + cam.getCameraY());
		if(cam.getCameraX() < 0 || cam.getCameraY() < 0){
			fail("Cam is not properly centered");
		}else
			System.out.println("Works");
	}

}
