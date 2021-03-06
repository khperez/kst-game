package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 * Manages the game Tetris. Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 */
public class Game
{
	private Grid grid; // the grid that makes up the Tetris board
	private Tetris display; // the visual for the Tetris game
	private boolean isOver; // has the game finished?
	private static boolean isPaused; // is the game paused?
	
	private AbstractPiece piece; // the current piece that is dropping
	private AbstractPiece [] pieceNext = new AbstractPiece[4];

	/** Creates a Tetris game
	 * @param Tetris the display
	 * */
	public Game(Tetris display)
	{
		grid = new Grid();
		this.display = display;
		isOver = false;
		isPaused = false;
		
		piece = randomPiece();
		for (int i=0; i<4; i++) pieceNext[i] = randomPiece();
	}

	/** Draws the current state of the game
	 * @param g the Graphics context on which to draw
	 */
	public void draw(Graphics g)
	{
		grid.draw(g);
		
		if (piece != null)
		{
			piece.drawGhost(g, piece.floorShiftAmount());
			piece.draw(g);
			
			// piece has shift down once, add point for piece
			ScoreCounter.pieceDropped();
		}
	}
	
	/**
	 * Draws next four pieces in the upcoming sequence
	 * @param g graphics object to draw pieces on
	 */
	public void drawNextPieces(Graphics g)
	{
		pieceNext[0].drawAtLocation(g, 110, 420);
		pieceNext[1].drawAtLocation(g, 110, 530);
		pieceNext[2].drawAtLocation(g, 110, 640);
		pieceNext[3].drawAtLocation(g, 110, 750);
	}

	/** Moves the piece in the given direction
	 * @param direction the direction to move
	 */
	public void movePiece(Direction direction)
	{
		if (piece != null) piece.move(direction);
		updatePiece();
        grid.checkRows();
        display.update();
	}
	
	/**
	 * Forces the piece to do a fast drop to the lowest unoccupied space
	 */
	public void hardDropPiece()
	{
		if (piece != null) 
		{
			int shift = piece.floorShiftAmount();
			piece.hardDropPiece(shift);
		}
		
	}

	/**
	 * Returns true if the game is over
	 */
	public boolean isGameOver()
	{
		// game is over if the piece occupies the same space as some non-empty
		// part of the grid. Usually happens when a new piece is made
		if (piece == null) {
			return false;
		}

		// check if game is already over
		if (isOver) {
			return true;
		}

		// check every part of the piece
		Point[] p = piece.getLocations();
		for (int i = 0; i < p.length; i++) {
			if (grid.isSet((int) p[i].getX(), (int) p[i].getY())) {
				isOver = true;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns if the game is paused
	 * @return paused state of game
	 */
	public boolean isPaused()
	{
		return isPaused;
	}
	
	/**
	 * Set paused state
	 * @param state the desired pause state to set the game to
	 */
	public void setPausedState(boolean state)
	{
		isPaused = state;
	}

	/**
	 * Updates the piece
	 */
	private void updatePiece()
	{
		if (piece == null)
		{	
			piece = pieceNext[0];
			pieceNext[0] = pieceNext[1];
			pieceNext[1] = pieceNext[2];
			pieceNext[2] = pieceNext[3];
			pieceNext[3] = randomPiece();
			
			display.panel_Right.update();
			
			// add piece points to score
			ScoreCounter.pieceAdded();
		}

		// set Grid positions corresponding to frozen piece
		// and then release the piece
		else if (!piece.canMove(Direction.DOWN)) {
			Point[] p = piece.getLocations();
			Color c = piece.getColor();
			for (int i = 0; i < p.length; i++) {
				grid.set((int) p[i].getX(), (int) p[i].getY(), c);
			}
			piece = null;
		}
	}

    /**
     * Rotate the piece clockwise
     */
	public void rotatePieceCW()
    {
		if (piece != null) piece.rotateCW();
        updatePiece();
        grid.checkRows();
        display.update();
    }
	
	/**
	 * Rotate the piece counter-clockwise
	 */
	public void rotatePieceCCW()
    {
		if (piece != null) piece.rotateCCW();
        updatePiece();
        grid.checkRows();
        display.update();
    }

	/**
	 * Returns a random piece from all available six pieces
	 * @return a random AbstractPiece
	 */
	public AbstractPiece randomPiece()
	{
		AbstractPiece p;
		Random rand = new Random();
		int center;
		
		if (Menu.difficulty.equals(Difficulty.ADVANCED) || Menu.difficulty.equals(Difficulty.LEGEND))
		{
			int max = Grid.WIDTH-4;
			int min = 1;
			center = rand.nextInt(max - min + 1) + min;
		}
		
		else
		{
			center = Grid.WIDTH/2-1;
		}
		
		switch(rand.nextInt(7))
		{
			case 0: p = new LShape(1, center, grid); break;
			case 1: p = new ZShape(1, center, grid); break;
			case 2: p = new SquareShape(1, center, grid); break;
			case 3: p = new JShape(1, center, grid); break;
			case 4:	p = new IShape(1, center, grid); break;
			case 5: p = new TShape(1, center, grid); break;
			case 6: p = new SShape(1, center, grid); break;
			default: p = new LShape(1, center, grid); break;
		}
		
		return p;
	}
}
