package tetris;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This is the Tetris board represented by a (HEIGHT - by - WIDTH) matrix of
 * Squares.
 * 
 * The upper left Square is at (0,0). The lower right Square is at (HEIGHT -1,
 * WIDTH -1).
 * 
 * Given a Square at (x,y) the square to the left is at (x-1, y) the square
 * below is at (x, y+1)
 * 
 * Each Square has a color. A white Square is EMPTY; any other color means that
 * spot is occupied (i.e. a piece cannot move over/to an occupied square). A
 * grid will also remove completely full rows.
 */

public class Grid
{
	private Square[][] board;
	public static int HEIGHT = 20; // Width and Height of Grid in number of squares
	public static int WIDTH = 10;
	private static final int BORDER = 5;
	public static int LEFT = 100; // pixel position of left of grid
	public static int TOP = 50; // pixel position of top of grid
	public static final Color EMPTY = Color.WHITE;

	/** Creates the grid */
	public Grid()
	{
		// set width and height based on selected board size from menu
		switch(Menu.boardSize)
		{
			case SMALL:
					WIDTH = 10;
					HEIGHT = 20;
					break;
			case MEDIUM:
					WIDTH = 16;
					HEIGHT = 32;
					break;
			case LARGE:
					WIDTH = 22;
					HEIGHT = 44;
					break;
			default: WIDTH = 10; 
					 HEIGHT = 20;
					 break;
		}
		
		// evaluate top and left values to draw board in center.
		TOP = ((Tetris.WINDOWHEIGHT / 2) - ((HEIGHT / 2) * Square.HEIGHT));
		LEFT = ((Tetris.WINDOWWIDTH / 2) - ((WIDTH / 2) * Square.WIDTH));
		
		board = new Square[HEIGHT][WIDTH];

		// put squares in the board
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				board[row][col] = new Square(this, row, col, EMPTY, false);
			}
		}
	}

	/** Returns true if the location (row, col) on the grid is occupied
	 * @param row the row in the grid
	 * @param col the column in the grid
	 */
	public boolean isSet(int row, int col)
	{
		return !board[row][col].getColor().equals(EMPTY);
	}

	/** Changes the color of the Square at the given location
	 * @param row the row of the Square in the Grid
	 * @param col the column of the Square in the Grid
	 * @param c the color to set the Square
	 * @throws IndexOutOfBoundsException if row < 0 || row>= WIDTH || col < 0 || col >= HEIGHT
	 */
	public void set(int row, int col, Color c) {
		board[row][col].setColor(c);
	}

	/**
	 * Checks for and remove all solid rows of squares.
	 * 
	 * If a solid row is found and removed, all rows above it are moved down and
	 * the top row set to empty
	 */
	private void removeRow(int r)
    {
		//change color of that row to white
        for (int col = 0; col < WIDTH; col++) set(r,col,EMPTY);

        //move the rest of the thing down
        for (int row = r-1; row >= 0; row--) {
        	for (int col = 0; col < WIDTH; col++) {
        		if(isSet(row,col))
                {
        			Color c = board[row][col].getColor();
                    board[row][col].setColor(EMPTY);
                    board[row+1][col].setColor(c);  
                }
        	}
        }
    }

	public void checkRows()
	{
		int col,row;
        for (row = 0; row< HEIGHT; row++) {
        	for( col = 0; col < WIDTH; col++) {
        		if(!isSet(row,col)) break;
            }
            
        	if(col == WIDTH) // a row is found
            {
        		removeRow(row);
        		
        		//UPDATE SCORE - TY - 4/13/16
                Tetris.point.addPoint();
                StringBuilder temp = new StringBuilder();
                temp.append(Tetris.point.getScore());
                Tetris.scoreDis = temp.toString();
                Tetris.scoreText.setText(Tetris.scoreDis);
                
                
            }
        } 
	}

	/** Draws the grid on the given Graphics context
	 */
	public void draw(Graphics g)
	{
		// draw the edges as rectangles: left, right in blue then bottom in red
		g.setColor(Color.GRAY);
		g.fillRect(LEFT - BORDER, TOP, BORDER, HEIGHT * Square.HEIGHT);
		g.fillRect(LEFT + WIDTH * Square.WIDTH, TOP, BORDER, HEIGHT * Square.HEIGHT);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(LEFT - BORDER, TOP + HEIGHT * Square.HEIGHT, WIDTH * Square.WIDTH + 2 * BORDER, BORDER);

		// draw all the squares in the grid
		// empty ones first (to avoid masking the black lines of the pieces that have already fallen)
		for (int r = 0; r < HEIGHT; r++) {
			for (int c = 0; c < WIDTH; c++) {
				if (board[r][c].getColor().equals(EMPTY)) {
					board[r][c].draw(g);
				}
			}
		}
		for (int r = 0; r < HEIGHT; r++) {
			for (int c = 0; c < WIDTH; c++) {
				if (!board[r][c].getColor().equals(EMPTY)) {
					board[r][c].draw(g);
				}
			}
		}
	}
}
