package tetris;
/**
 * @author Katherine Perez
 *
 */

import java.awt.Color;
/**
 * A Z-Shape piece in the Tetris Game. This piece is made up of 4 squares in the following configuration:
 * <br><br>
 * Sq Sq <br>
 *    Sq Sq <br>
 * <br>
 * The game piece "floats above" the Grid. The (row, col) coordinates are the location of the middle Square on the side within the Grid */
public class ZShape extends AbstractPiece
{

	/** Creates an Z-Shape piece. See class description for actual location of r and c
	 *  @param r row location for this piece
	 *  @param c column location for this piece
	 *  @param g the grid for this game piece */
	public ZShape(int r, int c, Grid g) {
		grid = g;
		square = new Square[PIECE_COUNT];
		ghost = new Square[PIECE_COUNT];
		ableToMove = true;
		centerOffset = c;
		offsetCorrection = 2;

		// Create the squares
		square[0] = new Square(g, r, c - 1, Color.red, true);
		square[1] = new Square(g, r, c, Color.red, true);
		square[2] = new Square(g, r + 1, c, Color.red, true);
		square[3] = new Square(g, r + 1, c + 1, Color.red, true);
		
		
	}
}
