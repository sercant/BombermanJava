package game.gui.states;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
	
	private Animation player, movingUp, movingDown, movingLeft, movingRight;
	private SpriteSheet sheet;
	private Image worldMap;
	private Vector2f playerPos;
	
	
	public Play(int state){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		worldMap = new Image("res/795fb084cb.png");
		sheet = new SpriteSheet("res/player.png", 32, 32);
		
		movingUp = new Animation();
		movingUp.setAutoUpdate(false);
		
		movingDown = new Animation();
		movingDown.setAutoUpdate(false);
		
		movingLeft = new Animation();
		movingLeft.setAutoUpdate(false);
		
		movingRight = new Animation();
		movingRight.setAutoUpdate(false);
		
		for (int col = 0; col < 3; col++) {
			movingUp.addFrame(sheet.getSprite(col, 3), 150);
			movingDown.addFrame(sheet.getSprite(col, 0), 150);
			movingLeft.addFrame(sheet.getSprite(col, 1), 150);
			movingRight.addFrame(sheet.getSprite(col, 2), 150);
		}
		movingDown.setCurrentFrame(1);
		player = movingDown;
		playerPos = new Vector2f(gc.getWidth() / 2, gc.getHeight() / 2);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		worldMap.draw();
		g.drawString("Play", gc.getWidth() / 2, 50);
		player.draw(playerPos.x, playerPos.y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		//input.disableKeyRepeat();
		
		if(!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)){
			player.setAutoUpdate(false);
			player.setCurrentFrame(1);
		}else{
			if(input.isKeyDown(Input.KEY_DOWN)){
				player = movingDown;
				player.setAutoUpdate(true);
				playerPos.y += delta * .1f; 
			}
			if(input.isKeyDown(Input.KEY_UP)){
				player = movingUp;
				player.setAutoUpdate(true);
				playerPos.y -= delta * .1f; 
			}
			if(input.isKeyDown(Input.KEY_LEFT)){
				player = movingLeft;
				player.setAutoUpdate(true);
				playerPos.x -= delta * .1f; 
			}
			if(input.isKeyDown(Input.KEY_RIGHT)){
				player = movingRight;
				player.setAutoUpdate(true);
				playerPos.x += delta * .1f; 
			}
		}
	}

	@Override
	public int getID() {
		return 1;
	}
}
