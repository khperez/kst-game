package tetris;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 * Manages the game Tetris. Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed. */
public class Game
{
	private Grid grid; // the grid that makes up the Tetris board
	private Tetris display; // the visual for the Tetris game
	private boolean isOver; // has the game finished?
	private static boolean isPaused; // is the game paused?
	
	private AbstractPiece piece; // the current piece that is dropping
	private AbstractPiece [] pieceNext = new AbstractPiece[4];

	/** Creates a Tetris game
	 * @param Tetris the display */
	public Game(Tetris display)
	{
		grid = new Grid();
		this.display = display;
		isOver = false;
		
		piece = randomPiece();
		for (int i=0; i<4; i++) pieceNext[i] = randomPiece();
	}

	/** Draws the current state of the game
	 * @param g the Graphics context on which to draw */
	public void draw(Graphics g)
	{
		grid.draw(g);
		if (piece != null)
		{
			piece.draw(g);
		}
	}
	
	public void drawNextPieces(Graphics g)
	{
		pieceNext[0].drawAtLocation(g, 110, 400);
		pieceNext[1].drawAtLocation(g, 110, 500);
		pieceNext[2].drawAtLocation(g, 110, 600);
		pieceNext[3].drawAtLocation(g, 110, 700);
	}

	/** Moves the piece in the given direction
	 * @param direction the direction to move */
	public void movePiece(Direction direction)
	{
		if (piece != null) piece.move(direction);
		updatePiece();
        grid.checkRows();
        display.update();
	}

	/** Returns true if the game is over */
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
	
	public boolean isPaused()
	{
		return isPaused;
	}
	
	public void setPausedState(boolean state)
	{
		isPaused = state;
	}

	/** Updates the piece */
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

    /** Rotate the piece */
	public void rotatePieceCW()
    {
		if (piece != null) piece.rotateCW();
        updatePiece();
        grid.checkRows();
        display.update();
    }
	
	public void rotatePieceCCW()
    {
		if (piece != null) piece.rotateCCW();
        updatePiece();
        grid.checkRows();
        display.update();
    }

	// SERGIO 4/7/16 //
	/** Returns a random piece */
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
