package tetris;

/**
 * Universal game score counter
 * Scoring algorithm based off of "Original Nintendo Scoring System" which can be
 * found at the following link: http://tetris.wikia.com/wiki/Scoring 
 */
public class ScoreCounter
{
	private static int score = 0;
	private static int previousBase = 0;
	private static int rowBonusMult = 0;
	private static int pieceDropCnt = 0;
	
	/**
	 * Add row removal bonus points
	 */
	public static void rowBonus()
	{
		int diffMult = 0;
		int base = 0;
	
		rowBonusMult++; // a row has been removed, increase row multiplier count
		
		// determine multiplier based on difficulty
		switch(Menu.difficulty)
		{
			case BEGINNER: 	diffMult = 0; break;
			case NORMAL: 	diffMult = 1; break;
			case ADVANCED: 	diffMult = 2; break;
			case LEGEND: 	diffMult = 3; break;
			default: 		diffMult = 0; break;
		}
		
		// scoring algorithm
		rowBonusMult = (rowBonusMult > 4) ? 4 : rowBonusMult;
		
		// amount of rows that were removed in total
		switch(rowBonusMult)
		{
			case 1:  base = 40;   break;
			case 2:  base = 100;  break;
			case 3:  base = 300;  break;
			case 4:  base = 600; break;
			default: base = 40;
		}
		
		// account for any previous rows for which points have already been granted, but are still part of the current bonus streak
		score = score + (base * (diffMult+1)) - (previousBase * (diffMult+1));
		previousBase = base;
		
		// update score on the main display
		updateScoreDisplay();
	}
	
	/**
	 * A piece has been added to the grid
	 */
	public static void pieceAdded()
	{
		rowBonusMult = 0;
		previousBase = 0;
		score += pieceDropCnt;
		pieceDropCnt = 0;
		updateScoreDisplay();
	}
	
	/**
	 * Piece has dropped one position
	 */
	public static void pieceDropped()
	{
		pieceDropCnt++;
	}
	
	/**
	 * Get current score
	 * @return current score
	 */
	public static int getScore()
	{
		return score;
	}
	
	/**
	 * Get current score in string format
	 * @return current score string
	 */
	public static String getScoreString()
	{
		return ((new StringBuilder().append(getScore()).toString()));
	}
	
	/**
	 * Update current score in the main Tetris game display
	 */
	public static void updateScoreDisplay()
	{
		Tetris.btn_score.setText(getScoreString());
	}
	
	/**
	 * Reset score and all respective variables back to defaults
	 */
	public static void resetScore()
	{
		score = 0;
		previousBase = 0;
		rowBonusMult = 0;
		pieceDropCnt = 0;
		updateScoreDisplay();
		
	}
}
