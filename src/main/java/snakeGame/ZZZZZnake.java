package snakeGame;

import java.awt.Point;
import java.util.Arrays;
import java.util.Scanner;

public class ZZZZZnake {
//	Auf dem Bildschirm gibt es den Spieler, eine Schlange, Gold und eine Tür.
//	Die Tür und das Gold sind fest, der Spieler kann bewegt werden und die Schlange bewegt sich selbstständig auf den Spieler zu.
//	Der Spieler muss zum Gold und dann zur Tür. Wenn die Schlange den Spieler vorher erwischt, ist das Spiel verloren.
	
	private static final int maxX = 40, maxY = 10;
	
	private static final Player player = new Player();
	private static final Snake snake1 = new Snake();
	private static final Snake snake2 = new Snake();
	private static final Gold gold1 = new Gold();
	private static final Gold gold2 = new Gold();
	private static final Door door = new Door();
	private static Point[] points = new Point[] {player, snake1, snake2, gold1, gold2, door};
	
	
	public static void main(String[] args) {
		for(Point p : points)
			initializeXY(p);
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			printScreen(points);
			
			if(gold1.isCollected && gold2.isCollected && player.equals(door)) {
				System.out.println( "Gewonnen!" );
				sc.close();
				return;
			}
			if(player.equals(snake1) || player.equals(snake2)) {
				System.out.println( "ZZZZZZZ - Die Schlange hat dich!" );
				sc.close();
				return;
			}
			if(player.equals(gold1)) {
				gold1.setLocation(-1, -1);
				gold1.isCollected = true;
			}
			if(player.equals(gold2)) {
				gold2.setLocation(-1, -1);
				gold2.isCollected = true;
			}
			
			if(!player.movePlayerOverBorders(sc, maxX, maxY)) {
				sc.close();
				return;
			}
			player.moves++;
			
			if(player.moves > 5) {
				snake1.moveSnake(player);
				snake2.moveSnake(player);
			}
		}
	}
	
	
	private static void initializeXY(final Point p) {
		if(p == null)
			throw new IllegalArgumentException("Point is null");
		
		int randomX = (int)(Math.random() * maxX);
		int randomY = (int)(Math.random() * maxY);
		p.setLocation(randomX, randomY);
	}
	
	private static void printScreen(final Point... points){
		if(points == null || points.length == 0)
			throw new IllegalArgumentException("Array is null or empty");
		
		final Point currentPosition = new Point(0, 0);
		for(int y = 0; y < maxY; y++) {
			for(int x = 0; x < maxX; x++) {
				currentPosition.setLocation(x, y);
				if(Arrays.asList(points).contains(currentPosition)) {
					for(Point p : points) {
						if(currentPosition.equals(p)) {
							if(p instanceof Snake) {
								System.out.print( "^" ); 
								break;
							}
							if(p instanceof Player) {
								System.out.print( "P" );
								break;
							}
							if(p instanceof Gold) {
								System.out.print( "$" );
								break;
							}
							if(p instanceof Door) {
								System.out.print( "#" );
								break;
							}
						}
					}
				}
				else
					System.out.print( "." );
			}
			System.out.println();
		}
	}
	
	@SuppressWarnings("unused")
	private static void printScreen(final Point player, final Point snake1, final Point snake2, final Point gold1, final Point gold2, final Point door){
		if(points == null || points.length == 0)
			throw new IllegalArgumentException("Array is null or empty");
		
		final Point currentPosition = new Point(0, 0);
		
		for(int y = 0; y < maxY; y++) {
			for(int x = 0; x < maxX; x++) {
				currentPosition.setLocation(x, y);
				
				if(currentPosition.equals(player))
					System.out.print( "P" );
				else if(currentPosition.equals(snake1) || currentPosition.equals(snake2))
					System.out.print( "^" );
					else if(currentPosition.equals(gold1) || currentPosition.equals(gold2))
						System.out.print( "$" );
						else if(currentPosition.equals(door))
							System.out.print( "#" );
							else
								System.out.print( "." );
			}
			System.out.println();
		}
	}
	
	@SuppressWarnings("unused")
	private static void printScreenFlag(final Point... points){
		if(points == null || points.length == 0)
			throw new IllegalArgumentException("Array is null or empty");
		
		final Point currentPosition = new Point(0, 0);
		boolean isPrinted;
		for(int y = 0; y < maxY; y++) {
			for(int x = 0; x < maxX; x++) {
				currentPosition.setLocation(x, y);
				isPrinted = false;
				for(Point p : points) {
					if(currentPosition.equals(p)) {
						if(p instanceof Player) {
							System.out.print( "P" );
							isPrinted = true;
							break;
						}
						if(p instanceof Snake) {
							System.out.print( "^" );
							isPrinted = true;
							break;
						}
						if(p instanceof Gold) {
							System.out.print( "$" );
							isPrinted = true;
							break;
						}
						if(p instanceof Door) {
							System.out.print( "#" );
							isPrinted = true;
							break;
						}
					}
				}
				if(!isPrinted)
					System.out.print( "." );
			}
			System.out.println();
		}
	}
	
	@SuppressWarnings("unused")
	private static void printScreenLabel(final Point... points){
		if(points == null || points.length == 0)
			throw new IllegalArgumentException("Array is null or empty");
		
		final Point currentPosition = new Point(0, 0);
		for(int y = 0; y < maxY; y++) {
			for(int x = 0; x < maxX; x++) {
				currentPosition.setLocation(x, y);
				nextPosition:{
					for(Point p : points) {
						if(currentPosition.equals(p)) {
							if(p instanceof Player) {
								System.out.print( "P" );
								break nextPosition;
							}
							if(p instanceof Snake) {
								System.out.print( "^" ); 
								break nextPosition;
							}
							if(p instanceof Gold) {
								System.out.print( "$" );
								break nextPosition;
							}
							if(p instanceof Door) {
								System.out.print( "#" );
								break nextPosition;
							}
						}
					}
					System.out.print( "." );
				}
			}
			System.out.println();
		}
	}
	
	
}
