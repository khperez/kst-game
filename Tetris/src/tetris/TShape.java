package tetris;

import java.awt.Color;

public class TShape extends AbstractPiece
{
	/**
	 * Creates an T-Shape piece
	 * An L-Shape piece in the Tetris Game. This piece is made up of 4 squares in the following configuration:
	 * <br><br>
	 * Sq Sq Sq <br> 
	 *    Sq <br>
	 * <br>
	 */
	public TShape(int r, int c, Grid g)
	{
		grid = g;
		square = new Square[PIECE_COUNT];
		ableToMove = true;
		
		//Create the squares
		square[0] = new Square(g, r, c, Color.yellow, true);
		square[1] = new Square(g, r, c + 1, Color.yellow, true);
		square[2] = new Square(g, r, c + 2, Color.yellow, true);
		square[3] = new Square(g, r + 1, c + 1, Color.yellow, true);
	}
	
	/**
	* Rotate the Piece
	*/
}
