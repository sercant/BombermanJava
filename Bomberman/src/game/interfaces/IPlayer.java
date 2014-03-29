package game.interfaces;

import game.entities.Direction;
import game.entities.Map;
import game.entities.PowerUp;

public interface IPlayer {
	public void update(Map map, int delta);
	public void move(Direction dir);
	public void powerUp(PowerUp power);
	public void addScore(int score);
	public void kill();
}
