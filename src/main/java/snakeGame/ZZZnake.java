package snakeGame;

import java.awt.Point;
import java.io.Console;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ZZZnake {
//	Auf dem Bildschirm gibt es den Spieler, Schlangen, Gold und eine Tür.
//	Die Tür und das Gold sind fest, der Spieler kann bewegt werden und die Schlangen bewegen sich selbstständig auf den Spieler zu.
//	Der Spieler muss zum Gold und dann zur Tür. Wenn eine Schlange den Spieler vorher erwischt, ist das Spiel verloren.
	
	public static void main(String[] args) {
		Console console = System.console();
		if (console == null)
			System.err.println("No console device available.");

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
	    
		ArrayList<GamePoint> snakePoints;
		while(true) {
			snakePoints = GamePoint.collectGamePoints(snake1.snakeSections, snake2.snakeSections);
			printScreen(console, snakePoints, player, gold1, gold2, door);
			
			if(gold1.isCollected && gold2.isCollected && player.equals(door)) {
				printOutput(console, "\nGewonnen!");
				return;
			}
			
			if(snakePoints.contains(player)) {
				printOutput(console, "\nZZZZZZZ - Die Schlange hat dich!");
				return;
			}
			
			if(player.isRich(gold1) || player.isRich(gold2) ) {		
				snake1.halve();
				snake2.cut(5);
				snakePoints = GamePoint.collectGamePoints(snake1.snakeSections, snake2.snakeSections);
				printScreen(console, snakePoints, player, gold1, gold2, door);
			}
			
			player.movePlayerOverBorders(console);
			
			if(!player.validInput)
				return;
			
			snake1.growAndMove(player);
			snake2.growAndMove(player);
		}
	}
	
	
	private static void printScreen(Console console, final ArrayList<GamePoint> snakePoints, GamePoint... points){
		if(console != null) {
			PrintWriter consoleWriter = console.writer();
			
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
									consoleWriter.print( " S " ); 
									break;
								}
								if(p instanceof Player) {
									consoleWriter.print( "°O°" );
									break;
								}
								if(p instanceof Gold) {
									consoleWriter.print( "|$|" );
									break;
								}
								if(p instanceof Door) {
									consoleWriter.print( "###" );
									break;
								}
							}
						}
					}
					else
						consoleWriter.print( "..." );
				}
				consoleWriter.println();
			}
			consoleWriter.close();
		}
		else
			printScreen(snakePoints, points);
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
	
	private static void printOutput(Console console, String output) {
		if(console != null) {
			PrintWriter consoleWriter = console.writer();
			consoleWriter.println( output );
			consoleWriter.close();
		}
		else
			System.out.println( output );
	}
}