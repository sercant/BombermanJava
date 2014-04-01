package game.controllers.interfaces;

import game.models.Direction;
import game.models.Map;

public interface IPlayerController {
	public void update(Map map, int delta);
	public void move(Direction dir);
}
