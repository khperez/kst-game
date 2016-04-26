package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public interface Piece
{
	/**
	 * Draws the piece on the given Graphics context
	 */
	public void draw(Graphics g);
	
	/**
	 * Draws the piece's ghost at the lowest unoccupied space of the grid
	 * @param g the graphics object to draw the ghost on
	 * @param shift how much to shift the ghost down from the location of the current piece
	 */
	public void drawGhost(Graphics g, int shift);

	/**
	 * Draw piece at specified coordinates
	 * @param g the graphics object to draw the piece on
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public void drawAtLocation(Graphics g, int x, int y);
	
	/**
	 * Moves the piece if possible. freeze the piece if it cannot move down anymore
	 * @param direction the direction to move
	 */
	public void move(Direction direction);
	
	/**
	 * Drops the piece to the first unoccupied space from the bottom of the grid.
	 * @param floorShift how many spaces to move the piece down
	 */
	public void hardDropPiece(int floorShift);

	/**
	 * Returns the (row, col) grid coordinates occupied by this Piece
	 * @return an Array of (row, col) Points
	 */
	public Point[] getLocations();

	/**
	 * Return the color of this piece
	 */
	public Color getColor();

	/**
	 * Returns if this piece can move in the given direction
	 */
	public boolean canMove(Direction direction);

	/**
	 * calculates the amount of spaces from current space to lowest unoccupied space
	 * @return amount of spaces piece can move down freely
	 */
	public int floorShiftAmount();
	
	/**
	 * Rotate the piece clockwise
	 */
	public void rotateCW();
	
	/**
	 * Rotate the piece counter-clockwise
	 */
	public void rotateCCW();
}
