package tetris;
/**
 * Create and control the game Tetris.
 * 
 *
 *
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Game game;

	/** Sets up the parts for the Tetris game, display and user control
	 */
	public Tetris(Difficulty d, Size s)
	{
		game = new Game(this, s);
		JFrame f = new JFrame("TETRIS");
		f.add(this);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(900, 1000);
		f.setVisible(true);
		EventController ec = new EventController(game, d);
		f.addKeyListener(ec);
		setBackground(Color.GRAY);
	}

	/** Updates the display
	 */
	public void update()
	{
		repaint();
	}

	/** Paint the current state of the game
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		game.draw(g);
		if (game.isGameOver()) {
			g.setFont(new Font("Palatino", Font.BOLD, 40));
			g.setColor(Color.BLACK);
			g.drawString("GAME OVER", 80, 300);
		}
	}
}
