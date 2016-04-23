package tetris;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class AbstractPiece implements Piece
{
	protected boolean ableToMove; // can this piece move
	protected Square[] square; // the squares that make up this piece
	protected Grid grid; // the board this piece is on	// Made up of PIECE_COUNT squares
	protected static final int PIECE_COUNT = 4;	// number of squares in one Tetris game piece
	protected int centerOffset;
	protected int offsetCorrection = 0;

	public void draw(Graphics g)
	{
		for (int i = 0; i < PIECE_COUNT; i++)
			square[i].draw(g);
	}
	
	public void drawGhost(Graphics g, int shift)
	{
		for (int i = 0; i < PIECE_COUNT; i++)
			square[i].drawGhost(g, shift);
	}
	
	public void drawAtLocation(Graphics g, int x, int y)
	{
		for (int i = 0; i < PIECE_COUNT; i++)
			square[i].drawAtLocation(g, x, y, centerOffset, offsetCorrection);
	}

	public void move(Direction direction)
	{
		if (canMove(direction))
			for (int i = 0; i < PIECE_COUNT; i++) square[i].move(direction);
		
		else if (direction == Direction.DOWN)	// if we couldn't move, check if it's because we're at the bottom
			ableToMove = false;
	}
	
	public void hardDropPiece(int floorShift)
	{
		for (int i = 0; i < PIECE_COUNT; i++)
			square[i].moveToGhost(floorShift);
	}

	public Point[] getLocations()
	{
		Point[] points = new Point[PIECE_COUNT];
		
		for (int i = 0; i < PIECE_COUNT; i++)
			points[i] = new Point(square[i].getRow(), square[i].getCol());
		
		return points;
	}

	public Color getColor()
	{
		return square[0].getColor();	// all squares of this piece have the same color
	}

	public boolean canMove(Direction direction)
	{
		boolean answer = true;
		
		if (!ableToMove) answer = false;
	
		else
		{
			for (int i = 0; i < PIECE_COUNT; i++)
				answer = answer && square[i].canMove(direction);
		}
		
		return answer;
	}
	
	public int floorShiftAmount()
	{
		int lowestPoint = Grid.HEIGHT+1;
		int point = 0;
		
		for (int i = 0; i < PIECE_COUNT; i++)
		{
			point = square[i].floorShiftAmount();
			lowestPoint = (point < lowestPoint) ? point : lowestPoint;
		}
		
		lowestPoint--;
		lowestPoint = (lowestPoint > 0) ? lowestPoint : 0;
		return (lowestPoint);
	}

	public void rotateCW()
	{
		
		int pivotRow, pivotCol, curRow, curCol, nextRow, nextCol;
		
		pivotRow = square[1].getRow();
		pivotCol = square[1].getCol();
		
		int[][] clockwise = { {  0, 1},
							  { -1, 0}};
		
		int i = 0;
		
		// modified code to ensure exceptions aren't possible, sergio 4/18
		if ((pivotRow == 0) || ((pivotCol+1 >= Grid.WIDTH -1) ? true : grid.isSet(pivotRow, pivotCol+1)) || ((pivotCol-1 <= 0) ? true : grid.isSet(pivotRow, pivotCol - 1))) return;
														// -1 HERE
														// NO MORE ERRORS BUTTTTTTTTT LIMIT THE ROTATION NEAR THE EDGE
		
		while (i < PIECE_COUNT)
		{
			if (i != 1)
			{
				curRow = square[i].getRow() - pivotRow;			// get coordinates wrt origin
				curCol = square[i].getCol() - pivotCol;
				
				nextRow = ((clockwise[0][0])*curRow) + ((clockwise[0][1])*curCol);		// rotate coordinates
				nextCol = ((clockwise[1][0])*curRow) + ((clockwise[1][1])*curCol);
				
				int row = pivotRow + nextRow;
				int col = pivotCol + nextCol;
				
				square[i].setRow(row);			// set individual square
				square[i].setCol(col);
			}
			
			else
			{
				square[i].setRow(pivotRow);		// set origin
				square[i].setCol(pivotCol);
			}
			
			i++;
		}
	}
		
	public void rotateCCW()
	{
		int pivotRow, pivotCol, curRow, curCol, nextRow, nextCol;
		
		pivotRow = square[1].getRow();
		pivotCol = square[1].getCol();
		
		int[][] ccw = { { 0, -1},
						{ 1,  0}};
		
		int i = 0;
		
		// modified code to ensure exceptions aren't possible, sergio 4/18
		if((pivotRow == 0) || ((pivotCol+1 >= Grid.WIDTH-1) ? true : grid.isSet(pivotRow, pivotCol+1)) || ((pivotCol-1 <= 0) ? true : grid.isSet(pivotRow, pivotCol - 1))) return;
														// -1 HERE
														// NO MORE ERRORS BUTTTTTTTTT LIMIT THE ROTATION NEAR THE EDGE
		
		while (i < PIECE_COUNT)
		{
			if (i != 1)
			{
				curRow = square[i].getRow() - pivotRow;			// get coordinates wrt origin
				curCol = square[i].getCol() - pivotCol;
				
				nextRow = ((ccw[0][0])*curRow) + ((ccw[0][1])*curCol);		// rotate coordinates
				nextCol = ((ccw[1][0])*curRow) + ((ccw[1][1])*curCol);
				
				int row = pivotRow + nextRow;
				int col = pivotCol + nextCol;
				
				square[i].setRow(row);			// set individual square
				square[i].setCol(col);
			}
			
			else
			{
				square[i].setRow(pivotRow);		// set origin
				square[i].setCol(pivotCol);
			}
			
			i++;
		}
	}
}
