package tetris;
/**
 * Handles events for the Tetris Game.  User events (key strokes) as well as periodic timer
 * events.
 * 
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class EventController extends KeyAdapter implements ActionListener {

	private Game game; // current game: grid and current piece
	private Timer timer;

	private double PIECE_MOVE_TIME = 0.6; // wait 0.8 s every time
														// the piece moves down
														// increase to slow it
														// down

	private boolean gameOver;

	/**
	 * Creates an EventController to handle key and timer events.
	 * 
	 * @param game
	 *            the game this is controlling
	 */
	public EventController(Game game, Difficulty d) {
		this.game = game;
		gameOver = false;
		
		// SERGIO 4/7/16 //
		switch(d)
		{
			case BEGINNER: PIECE_MOVE_TIME = 1.0; break;
			case NORMAL: PIECE_MOVE_TIME = 0.6; break;
			case ADVANCED: PIECE_MOVE_TIME = 0.3; break;
			case LEGENDARY: PIECE_MOVE_TIME = 0.05; break;
			default: PIECE_MOVE_TIME = 0.03; break;
		}
		
		// SERGIO 4/7/16 //
		
		double delay = 500 * PIECE_MOVE_TIME; // in milliseconds
		timer = new Timer((int) delay, this);
		timer.setCoalesce(true); // if multiple events pending, bunch them to
		// 1 event
		timer.start();
	}

	/**
	 * Responds to special keys being pressed.
	 * 
	 * Currently just responds to the space key and the q(uit) key
	 */
	public void keyPressed(KeyEvent e) {
		// if 'Q', quit the game
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			timer.stop();
			((JFrame) e.getSource()).dispose();
		}
		if (!gameOver) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN: handleMove(Direction.DOWN); break;
			case KeyEvent.VK_KP_DOWN: handleMove(Direction.DOWN); break;
            case KeyEvent.VK_LEFT: handleMove(Direction.LEFT); break;
            case KeyEvent.VK_KP_LEFT: handleMove(Direction.LEFT); break;
            case KeyEvent.VK_RIGHT: handleMove(Direction.RIGHT); break;
            case KeyEvent.VK_KP_RIGHT: handleMove(Direction.RIGHT); break;
			//case KeyEvent.VK_SPACE: game.rotatePiece(); break;
            case KeyEvent.VK_UP: game.rotatePiece(); break;					// Katherine 4/8/16 //
            case KeyEvent.VK_KP_UP: game.rotatePiece(); break;
			}
		}
	}

	/** Updates the game periodically based on a timer event */
	public void actionPerformed(ActionEvent e) {
		handleMove(Direction.DOWN);
	}

	/**
	 * Update the game by moving in the given direction
	 */
	private void handleMove(Direction direction) {
		game.movePiece(direction);
		gameOver = game.isGameOver();
		if (gameOver)
			timer.stop();
	}
}
