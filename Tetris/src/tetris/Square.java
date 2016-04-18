package tetris;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * One Square on our Tetris Grid or one square in our Tetris game piece 
 */
public class Square {
	private Grid grid; // the environment where this Square is
	private int row, col; // the grid location of this Square
	private boolean ableToMove; // true if this Square can move
	private Color color; // the color of this Square

	// possible move directions are defined by the Game class

	// dimensions of a Square
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;

	//Coordinate of the nextShape box
	private int coordX = 175;
	private int coordY = 250;

	/** Creates a square
	 * @param g the Grid for this Square
	 * @param row the row of this Square in the Grid
	 * @param col the column of this Square in the Grid
	 * @param c the Color of this Square
	 * @param mobile true if this Square can move
	 * @throws IllegalArgumentException if row and col not within the Grid
	 */
	public Square(Grid g, int row, int col, Color c, boolean mobile) {
		if (row < 0 || row > Grid.HEIGHT - 1)
			throw new IllegalArgumentException("Invalid row =" + row);
		if (col < 0 || col > Grid.WIDTH - 1)
			throw new IllegalArgumentException("Invalid column  = " + col);

		// initialize instance variables
		grid = g;
		this.row = row;
		this.col = col;
		color = c;
		ableToMove = mobile;

	}

	/**
	 * Returns the row for this Square
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Sets the row for this Square
	 */
	public void setRow(int r) {
		row = r;
	}
	
	/**
	 * Returns the column for this Square
	 */
	public int getCol() {
		return col;
	}
	/**
	 * Sets the column for this Square
	 */
	public void setCol(int c) {
		col = c;
	}

	/**
	 * Returns true if this Square can move 1 spot in direction d 
	 * @param direction the direction to test for possible move
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		boolean move = true;
		// if the given direction is blocked, we can't move
		// remember to check the edges of the grid
		switch (direction) {
		case DOWN:
			if (row == (Grid.HEIGHT - 1) || grid.isSet(row + 1, col))
				move = false;
			break;

		// currently doesn't support checking LEFT or RIGHT
		// MODIFY so that it correctly returns if it can move left or right
		case LEFT:
                        if(col == 0 || grid.isSet(row, col-1)) 
                            move = false;
                        break;
		case RIGHT: // CODE ADDED FOR PART 1
                        if((col == Grid.WIDTH) || grid.isSet(row, col+1))
                        	move = false;
                        break;
		}
		return move;
	}

	/** moves this square in the given direction if possible.
	 * 
	 * The square will not move if the direction is blocked, or if the square is
	 * unable to move.
	 * 
	 * If it attempts to move DOWN and it can't, the square is frozen and cannot
	 * move anymore
	 * 
	 * @param direction the direction to move
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			switch (direction) {
			case DOWN: row++; break;
            case LEFT: col--; break;
            case RIGHT: col++; break; // CODE ADDED FOR PART 1
			}
		}
	}

	/**
	 * Changes the color of this square 
	 * @param c the new color
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * Gets the color of this square
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Draws this square on the given graphics context
	 */
	public void draw(Graphics g) {

		int actualX = Grid.LEFT + col * WIDTH;
		int actualY = Grid.TOP + row * HEIGHT;
		
		g.setColor(color);
		
		String squareFile = null;
		
		if(color.equals(Grid.EMPTY))
		{
			g.fillRect(actualX, actualY, WIDTH, HEIGHT);
		}
		
		else
		{
			try
			{
				if (color.equals(Color.RED)) squareFile = "images/square_red.png";
				else if (color.equals(Color.BLUE)) squareFile = "images/square_blue.png";
				else if (color.equals(Color.ORANGE)) squareFile = "images/square_orange.png";
				else if (color.equals(Color.YELLOW)) squareFile = "images/square_yellow.png";
				else if (color.equals(Color.GREEN)) squareFile = "images/square_green.png";
				else if (color.equals(Color.MAGENTA)) squareFile = "images/square_magenta.png";
				else if (color.equals(Color.CYAN)) squareFile = "images/square_cyan.png";
				
				Image bgImage = ImageIO.read(new File(squareFile));
			    if (bgImage != null)
			    {
			    	g.drawImage(bgImage,actualX,actualY, WIDTH, HEIGHT, null);
			    }
			}
			
			catch(IOException e){}
		}

//		// black border (if not empty)
//		if (!color.equals(Grid.EMPTY)) {
//			g.setColor(Color.BLACK);
//			//g.drawRect(actualX, actualY, WIDTH, HEIGHT);
//		}
	}
	
	public void drawNext(Graphics g)
	{
		String squareFile = null;
		
		if (color.equals(Color.RED)) squareFile = "images/square_red.png";
		else if (color.equals(Color.BLUE)) squareFile = "images/square_blue.png";
		else if (color.equals(Color.ORANGE)) squareFile = "images/square_orange.png";
		else if (color.equals(Color.YELLOW)) squareFile = "images/square_yellow.png";
		else if (color.equals(Color.GREEN)) squareFile = "images/square_green.png";
		else if (color.equals(Color.MAGENTA)) squareFile = "images/square_magenta.png";
		else if (color.equals(Color.CYAN)) squareFile = "images/square_cyan.png";
		
		Image bgImage;
		try {
			bgImage = ImageIO.read(new File(squareFile));
			
			if(Game.nextShape=="ZShape")
	    	{
	    		g.drawImage(bgImage,coordX,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY+20, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+40,coordY+20, WIDTH, HEIGHT, null);
	    	}
	    	// J Shape
	    	else if(Game.nextShape=="JShape")
	    	{
	    		g.drawImage(bgImage,coordX+20,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY+20, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX,coordY+40, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY+40, WIDTH, HEIGHT, null);
	    	}
	    	// L Shape
	    	else if(Game.nextShape=="LShape")
	    	{
	    		g.drawImage(bgImage,coordX,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX,coordY+20, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX,coordY+40, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY+40, WIDTH, HEIGHT, null);
	    	}
	    	// Square Shape
	    	else if(Game.nextShape=="SquareShape")					
	    	{
	    		g.drawImage(bgImage,coordX,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX,coordY+20, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY+20, WIDTH, HEIGHT, null);
	    	}
	    	// S Shape
	    	else if(Game.nextShape=="SShape")					
	    	{
	    		g.drawImage(bgImage,coordX+20,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+40,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX,coordY+20, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY+20, WIDTH, HEIGHT, null);
	    	}
	    	// T Shape
	    	else if(Game.nextShape=="TShape")					
	    	{
	    		g.drawImage(bgImage,coordX,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+40,coordY, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY+20, WIDTH, HEIGHT, null);
	    	}
	    	// I Shape
	    	else if(Game.nextShape=="IShape")					
	    	{
	    		g.drawImage(bgImage,coordX,coordY+20, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+20,coordY+20, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+40,coordY+20, WIDTH, HEIGHT, null);
	    		g.drawImage(bgImage,coordX+60,coordY+20, WIDTH, HEIGHT, null);
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	    
	    
	    //Drawing next piece - TY - 4/16/16
	    // Z Shape
    	
	}

}
