package tetris;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public interface Piece
{
	/** Draws the piece on the given Graphics context */
	public void draw(Graphics g);

	/** Moves the piece if possible Freeze the piece if it cannot move down anymore
	 * @param direction the direction to move */
	public void move(Direction direction);
	
	/** Drops the piece to the first unoccupied space from the bottom of the grid.
	 * @param 
	 */
	public void hardDropPiece(int floorShift);

	/** Returns the (row,col) grid coordinates occupied by this Piece
	 * @return an Array of (row,col) Points */
	public Point[] getLocations();

	/** Return the color of this piece */
	public Color getColor();

	/** Returns if this piece can move in the given direction */
	public boolean canMove(Direction direction);

	/** Rotate the piece clockwise*/
	public void rotateCW();
	
	/** Rotate the piece counter-clockwise*/
	public void rotateCCW();
}
