package tetris;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class AbstractPiece implements Piece
{
	protected boolean ableToMove; // can this piece move
	protected Square[] square; // the squares that make up this piece
	protected Square[] ghost;
	private int ghostY;
	private int maxRow = Grid.HEIGHT - 1;
	private int maxCol = Grid.WIDTH - 1;
	protected Grid grid; // the board this piece is on	// Made up of PIECE_COUNT squares
	protected static final int PIECE_COUNT = 4;	// number of squares in one Tetris game piece
	protected int centerOffset;
	protected int offsetCorrection = 0;

	public void draw(Graphics g)
	{
		for (int i = 0; i < PIECE_COUNT; i++)
		{
			square[i].draw(g);
			computeGhost(g);
		}
	}
	
	public void drawAtLocation(Graphics g, int x, int y)
	{
		for (int i = 0; i < PIECE_COUNT; i++)
			square[i].drawAtLocation(g, x, y, centerOffset, offsetCorrection);
	}

	public void move(Direction direction)
	{
		if (canMove(direction))
		{
			for (int i = 0; i < PIECE_COUNT; i++) square[i].move(direction);
			
		}
		
		else if (direction == Direction.DOWN)	// if we couldn't move, check if it's because we're at the bottom
			ableToMove = false;
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

	public void rotateCW()
	{
		
		int pivotRow, pivotCol, curRow, curCol, nextRow, nextCol;
		
		pivotRow = square[1].getRow();
		pivotCol = square[1].getCol();
		
		int[][] clockwise = { {  0, 1},
							  { -1, 0}};
		
		int i = 0;
		
		// modified code to ensure exceptions aren't possible, sergio 4/18
		if ((pivotRow == 0) || ((pivotCol+1 >= Grid.WIDTH) ? true : grid.isSet(pivotRow, pivotCol+1)) || ((pivotCol-1 <= 0) ? true : grid.isSet(pivotRow, pivotCol - 1))) return;
		
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
		if((pivotRow == 0) || ((pivotCol+1 >= Grid.WIDTH) ? true : grid.isSet(pivotRow, pivotCol+1)) || ((pivotCol-1 <= 0) ? true : grid.isSet(pivotRow, pivotCol - 1))) return;
		
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
	
	protected void computeGhost(Graphics g)
	{
		for (int i = 0; i < PIECE_COUNT; i++)
			ghost[i].drawGhost(g, maxRow, maxCol);
	}
	/*
	public void showGhost(int distance)
	{
		
		int curRow, curCol;
		
		Point[] curPos = this.getLocations();
		
		int i= 0;
		for (i = 0; i < curPos.length; i++)
		{
			curRow = (int) curPos[i].getX();
			curCol = (int) curPos[i].getY();
			if (isValid(distance, curCol))
			{
				ghost[i].setRow(distance + curRow);
				ghost[i].setCol(curCol);
			}
			else
				return;
		}
	}
	
	protected boolean isValid(int x, int y)
	{
		if (!(x >= 0 && x <= maxRow) || !(y >= 0 && y <= maxCol))
			return false;
		return true;
	}
	*/
	
	/*protected int computeGhost()
	{			
		ghostY = 0;
		int curRow, curCol, ghostX;
		int distance = maxRow;
		boolean gridCheck = true;
		Point[] curPos = this.getLocations();
		
		int i= 0, j = 0;
		for (i = 0; i < curPos.length; i++) 
		{
			curRow = (int) curPos[i].getX();
			if (ghostY <= curRow)
			{
				ghostY = curRow;
			}
		}
		for (i = 0; i < curPos.length; i++) 
		{
			curRow = (int) curPos[i].getX();
			if (curRow == ghostY)
			{
				j++;
			}
		}
		
		Point[] ghostPos = new Point[j];
		for (i = 0; i < curPos.length; i++)
		{
			curRow = (int) curPos[i].getX();
			curCol = (int) curPos[i].getY();
			if(curRow == ghostY)
			{
				ghostPos[j] = new Point(ghostY, curCol);
				j++;
			}
		}
		
		for (j = 0; j < ghostPos.length; j++)
		{
			curRow = (int) ghostPos[j].getX();
			curCol = (int) ghostPos[j].getY();
			distance = maxRow;
			while(gridCheck)
			{
				if (grid.isSet(distance, curCol))
				{
					distance--;
					ghostY = distance;
				}
				else
					gridCheck = false;
			}
			
			if (ghostY <= distance)
			{
				distance = ghostY;
			}			
		}
				
		return distance;
	}
	*/
}
