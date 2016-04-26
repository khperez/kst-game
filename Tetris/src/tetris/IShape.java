package tetris;

import java.awt.Color;

public class IShape extends AbstractPiece
{
	/**
	 * Creates an T-Shape piece
	 * An L-Shape piece in the Tetris Game. This piece is made up of 4 squares in the following configuration:
	 * <br><br>
	 * Sq Sq Sq Sq <br> 
	 */
	public IShape(int r, int c, Grid g)
	{
		grid = g;
		square = new Square[PIECE_COUNT];
		ableToMove = true;
		centerOffset = c;
		
		//Create the squares
		square[0] = new Square(g, r, c, Color.cyan, true);
		square[1] = new Square(g, r, c + 1, Color.cyan, true);
		square[2] = new Square(g, r, c + 2, Color.cyan, true);
		square[3] = new Square(g, r, c + 3, Color.cyan, true);
	}
}
