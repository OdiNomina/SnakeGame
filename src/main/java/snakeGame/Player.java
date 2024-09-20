package snakeGame;

import java.io.Console;
import java.io.PrintWriter;
import java.util.Scanner;

public class Player extends GamePoint {
	
	private static final long serialVersionUID = 1L;
	
	private static String validKeys = "Der Spieler kann mit folgenden Tasten bewegt werden:\n"
			+ "Hoch: w, W, h, H\n"
			+ "Tief: s, S, t, T\n"
			+ "Links: a, A, l, L\n"
			+ "Rechts: d, D, r, R";
	protected int moves = 0;
	protected boolean validInput = true;
	
	
	protected boolean isRich(Gold gold) {
		if(this.equals(gold)) {
			gold.setLocation(-1, -1);
			gold.isCollected = true;
			return true;
		}
		return false;
	}
	
	protected void movePlayer(Console console, PrintWriter consoleWriter) {
		char switchExpression;
		Scanner sc = null;
		if(console != null && consoleWriter != null) {
			switchExpression = console.readLine().charAt(0);
		}
		else {
			sc = new Scanner(System.in);
			switchExpression = sc.next().charAt(0);
		}
		
		moved:{
			for(int i = 0; i < 10; i++) {
				switch(switchExpression) {
					case 'w', 'W', 'h', 'H' -> {
						y = Math.max(0, y - 1);
						moves++;
						break moved;
					}
					case 's', 'S', 't', 'T' -> {
						y = Math.min((GamePoint.YMAX - 1), y + 1);
						moves++;
						break moved;
					}
					case 'a', 'A', 'l', 'L' -> {
						x = Math.max(0, x - 1);
						moves++;
						break moved;
					}
					case 'd', 'D', 'r', 'R' -> {
						x = Math.min((GamePoint.XMAX - 1), x + 1);
						moves++;
						break moved;
					}
					default -> {
						if(consoleWriter != null) {
							consoleWriter.flush();
							consoleWriter.println(validKeys);
							consoleWriter.flush();
							consoleWriter.println();
						}
						else
							System.out.println(validKeys);
					}
				}
			}
			validInput = false;
		}
		if(sc != null)
			sc.close();
	}
	
	protected void movePlayerOverBorders(Console console, PrintWriter consoleWriter) {
		char switchExpression = 0;
		Scanner sc = null;
		if(console == null)
			sc = new Scanner(System.in);
		
		moved:{
			for(int i = 0; i < 3; i++) {
				if(console != null)
					switchExpression = console.readLine().charAt(0);
				else if(sc != null)
					switchExpression = sc.next().charAt(0);
				
				switch(switchExpression) {
					case 'w', 'W', 'h', 'H' -> {
						y = (y - 1) < 0 ? (GamePoint.YMAX - 1) : ( y - 1 );
						moves++;
						break moved;
					}
					case 's', 'S', 't', 'T' -> {
						y = (y + 1) > (GamePoint.YMAX - 1) ? 0 : (y + 1);
						moves++;
						break moved;
					}
					case 'a', 'A', 'l', 'L' -> {
						x = (x - 1) < 0 ? (GamePoint.XMAX - 1) : (x - 1);
						moves++;
						break moved;
					}
					case 'd', 'D', 'r', 'R' -> {
						x = (x + 1) > (GamePoint.XMAX - 1) ? 0 : (x + 1);
						moves++;
						break moved;
					}
					default -> {
						if(consoleWriter != null) {
							consoleWriter.flush();
							consoleWriter.println(validKeys);
							consoleWriter.flush();
							consoleWriter.println();
						}
						else
							System.out.println(validKeys);
					}
				}
			}
			validInput = false;
		}
	}
}
