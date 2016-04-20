package tetris;

/**
 * @author Katherine Perez
 */

import java.awt.Color;
import java.awt.Graphics;

/**
 * A ghost piece that mimics the current playable piece and shows where the piece will land
 * at the bottom.
 */
public class Ghost extends AbstractPiece
{

	/** Creates a ghost piece
	 */
	
	public Ghost(int r, int c, Grid g) {
		grid = g;
		square = new Square[PIECE_COUNT];
		ableToMove = true;
		centerOffset = c;
		offsetCorrection = 2;

		// Create the squares
		square[0] = new Square(g, r, c, Color.BLACK, true);
		square[1] = new Square(g, r, c, Color.BLACK, true);
		square[2] = new Square(g, r, c, Color.BLACK, true);
		square[3] = new Square(g, r, c, Color.BLACK, true);
	}
}

