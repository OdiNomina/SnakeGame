package snakeGame;

import java.awt.Point;

public class Snake  extends Point {
	
	private static final long serialVersionUID = 1L;
	
	public Snake() {
		this.x = -1;
		this.y = -1;
	}
	
	protected void moveSnake(final Point player) {
		if(player == null)
			throw new IllegalArgumentException("Player is null");
		
		if(player.x < x)
			x--;
		else if(player.x > x )
			x++;
			
		if(player.y < y)
			y--;
		else if(player.y > y)
			y++;
	}
}
