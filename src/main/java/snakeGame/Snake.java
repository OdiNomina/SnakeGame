package snakeGame;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	
	protected static final int SnakeStartLength = 5;
	
	protected ArrayList<SnakeSection> snakeSections;
	
	public Snake() {
		this.snakeSections  = new ArrayList<SnakeSection>();
	}
	
	protected void moveSnake(final Point snakeSection, final Point player) {
		if(snakeSection == null || player == null)
			throw new IllegalArgumentException("Can not move snake, argument is null");
		
		if(player.x < snakeSection.x)
			snakeSection.x--;
		else if(player.x > snakeSection.x )
			snakeSection.x++;
			
		if(player.y < snakeSection.y)
			snakeSection.y--;
		else if(player.y > snakeSection.y)
			snakeSection.y++;
	}
}
