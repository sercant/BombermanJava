package domain.deneme;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {
	
	private int ID;
	private Image logo;
	private String[] menuButtons;
	private int selectedButton;
	
	public Menu(int state){
		ID = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		logo = new Image("res/bombermanText.png");
		logo.setFilter(Image.FILTER_NEAREST);
		logo = logo.getScaledCopy(5); //temporary
		
		menuButtons = new String[4];
		menuButtons[0] = ">Start Game<";
		menuButtons[1] = "Continue";
		menuButtons[2] = "High Scores";
		menuButtons[3] = "Quit Game";
		
		selectedButton = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(logo, gc.getWidth() / 2 - logo.getWidth() /2, 50);
		
		for (int i = 0, j = 200; i < menuButtons.length; i++, j += 50) {
			g.drawString(menuButtons[i], gc.getWidth() / 2, j);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		input.disableKeyRepeat();
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			menuButtons[selectedButton] = menuButtons[selectedButton].replace(">", "");
			menuButtons[selectedButton] = menuButtons[selectedButton].replace("<", "");
			selectedButton++;
			if(selectedButton > 3){ selectedButton = 0; }
			else if(selectedButton < 0) { selectedButton = 3; }
			menuButtons[selectedButton] = ">" + menuButtons[selectedButton] + "<";
		}
		if(input.isKeyPressed(Input.KEY_UP)){
			menuButtons[selectedButton] = menuButtons[selectedButton].replace(">", "");
			menuButtons[selectedButton] = menuButtons[selectedButton].replace("<", "");
			selectedButton--;
			if(selectedButton > 3){ selectedButton = 0; }
			else if(selectedButton < 0) { selectedButton = 3; }
			menuButtons[selectedButton] = ">" + menuButtons[selectedButton] + "<";
		}
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(selectedButton + 1);
		}
	}

	@Override
	public int getID() {
		return ID;
	}
}
