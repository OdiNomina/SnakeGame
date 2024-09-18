package snakeGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ZZZnake {
//	Auf dem Bildschirm gibt es den Spieler, Schlangen, Gold und eine Tür.
//	Die Tür und das Gold sind fest, der Spieler kann bewegt werden und die Schlangen bewegen sich selbstständig auf den Spieler zu.
//	Der Spieler muss zum Gold und dann zur Tür. Wenn eine Schlange den Spieler vorher erwischt, ist das Spiel verloren.
	
	public static void main(String[] args) {
		final Player player = new Player();
		final Gold gold1 = new Gold();
		final Gold gold2 = new Gold();
		final Door door = new Door();
		final Snake snake1 = new Snake();
		final Snake snake2 = new Snake();
		snake1.snakeSections.add(new SnakeSection());
		snake2.snakeSections.add(new SnakeSection());
		
		player.initializeRandomXY();
		gold1.initializeRandomXY();
		gold2.initializeRandomXY();
		door.initializeRandomXY();
		snake1.snakeSections.getFirst().initializeRandomXY();
		snake2.snakeSections.getFirst().initializeRandomXY();
		
		
		Scanner sc = new Scanner(System.in);
		ArrayList<GamePoint> snakePoints;
		while(true) {
			snakePoints = GamePoint.collectGamePoints(snake1.snakeSections, snake2.snakeSections);
			printScreen(snakePoints, player, gold1, gold2, door);
			
			if(gold1.isCollected && gold2.isCollected && player.equals(door)) {
				System.out.println( "\nGewonnen!" );
				sc.close();
				return;
			}
			
			if(snakePoints.contains(player)) {
				System.out.println( "\nZZZZZZZ - Die Schlange hat dich!" );
				sc.close();
				return;
			}
			
			if(player.isRich(gold1) || player.isRich(gold2) ) {		
				snake1.halve();
				snake2.cut(5);
				snakePoints = GamePoint.collectGamePoints(snake1.snakeSections, snake2.snakeSections);
				printScreen(snakePoints, player, gold1, gold2, door);
			}
			
			player.movePlayerOverBorders(sc);
			
			if(!player.validInput) {
				sc.close();
				return;
			}
			
			// The snake grows after every second move of the player
			snake1.grow(player);
			snake2.grow(player);
		}
	}
	
	
	private static void printScreen(final ArrayList<GamePoint> snakePoints, GamePoint... points){
		ArrayList<Point> printPoints = new ArrayList<Point>();
		printPoints.addAll(snakePoints);
		printPoints.addAll(Arrays.asList(points));
		
		final Point currentPosition = new Point(0, 0);
		for(int y = 0; y < GamePoint.YMAX; y++) {
			for(int x = 0; x < GamePoint.XMAX; x++) {
				currentPosition.setLocation(x, y);
				if(printPoints.contains(currentPosition)) {
					for(Point p : printPoints) {
						if(currentPosition.equals(p)) {
							if(p instanceof SnakeSection) {
								System.out.print( " S " ); 
								break;
							}
							if(p instanceof Player) {
								System.out.print( "°O°" );
								break;
							}
							if(p instanceof Gold) {
								System.out.print( "|$|" );
								break;
							}
							if(p instanceof Door) {
								System.out.print( "###" );
								break;
							}
						}
					}
				}
				else
					System.out.print( "..." );
			}
			System.out.println();
		}
	}
	
}