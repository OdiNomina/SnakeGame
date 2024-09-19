package snakeGame;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	
	protected static final int SnakeStartLength = 10;
	
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
	
	protected void cut(int amount){
		if(snakeSections.size() > amount)
			snakeSections.removeAll(snakeSections.subList(0, amount));
	}
	
	protected void halve(){
		if(snakeSections.size() > 1)
			snakeSections.removeAll(snakeSections.subList(0, snakeSections.size() / 2));
	}
	
	protected void growAndMove(Player player) {
		// The snake grows after every second move of the player
		if(snakeSections.size() < SnakeStartLength || player.moves % 2 == 0) {
			snakeSections.add(new SnakeSection(snakeSections.getLast()));
			moveSnake(snakeSections.getLast(), player);
		}
	}
}
