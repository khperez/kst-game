package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Handles events for the Tetris Game.  User events (key strokes) as well as periodic timer events.
 */
public class EventController implements ActionListener
{
	private Game game; // current game: grid and current piece
	private Timer timer;
	private double PIECE_MOVE_TIME = 0.6; // wait 0.8 s every time the piece moves down increase to slow it down
	private boolean gameOver;

	/**
	 * Creates an EventController to handle key and timer events
	 * @param game game to control
	 */
	public EventController(Game game)
	{
		this.game = game;
		gameOver = false;
		
		// set delay based on user-selected difficulty
		switch(Menu.difficulty)
		{
			case BEGINNER: PIECE_MOVE_TIME = 1.0; break;
			case NORMAL: PIECE_MOVE_TIME = 0.6; break;
			case ADVANCED: PIECE_MOVE_TIME = 0.3; break;
			case LEGEND: PIECE_MOVE_TIME = 0.05; break;
			default: PIECE_MOVE_TIME = 0.03; break;
		}
		
		double delay = 500 * PIECE_MOVE_TIME; // in milliseconds
		timer = new Timer((int) delay, this);
		timer.setCoalesce(true); // if multiple events pending, bunch them to 1 event
		timer.start();
	}

	/**
	 * Updates the game periodically based on a timer event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(!(game.isPaused())) handleMove(Direction.DOWN);
	}

	/**
	 * Update the game by moving in the given direction
	 */
	public void handleMove(Direction direction) {
		game.movePiece(direction);
		gameOver = game.isGameOver();
		if (gameOver)
			timer.stop();
	}
	
	/**
	 * Increase the timer speed (speed increment is automatically determined by user-selected difficulty)
	 */
	public void increaseTimerSpeed()
	{
		int newTime = timer.getDelay();
		int delay = 0;
		
		if ((Menu.difficulty == Difficulty.BEGINNER) || (Menu.difficulty == Difficulty.NORMAL)) delay = 5;
		else delay = 10;
		newTime = (newTime-delay) > 0 ? newTime-delay : 0;
		timer.setDelay(newTime);
	}

	/**
	 * Stop active game-play timer
	 */
	public void stopTimer()
	{
		timer.stop();
	}
}
