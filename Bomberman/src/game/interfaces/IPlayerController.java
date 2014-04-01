package game.interfaces;

import game.entities.Direction;
import game.entities.Map;

public interface IPlayerController {
	public void update(Map map, int delta);
	public void move(Direction dir);
}
