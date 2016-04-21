package tetris;

public class ScoreCounter
{
	private static int score = 0;
	private static int previousBase = 0;
	private static int rowBonusMult = 0;
	private static int pieceDropCnt = 0;
	
	public static void rowBonus()
	{
		int diffMult = 0;
		int base = 0;
		
		rowBonusMult++;
		
		switch(Menu.difficulty)
		{
			case BEGINNER: 	diffMult = 0; break;
			case NORMAL: 	diffMult = 1; break;
			case ADVANCED: 	diffMult = 2; break;
			case LEGEND: 	diffMult = 3; break;
			default: 		diffMult = 0; break;
		}
		
		rowBonusMult = (rowBonusMult > 4) ? 4 : rowBonusMult;
		
		switch(rowBonusMult)
		{
			case 1:  base = 40;   break;
			case 2:  base = 100;  break;
			case 3:  base = 300;  break;
			case 4:  base = 600; break;
			default: base = 40;
		}
		
		score = score + (base * (diffMult+1)) - (previousBase * (diffMult+1));
		previousBase = base;
		
		updateScoreDisplay();
	}
	
	public static void pieceAdded()
	{
		rowBonusMult = 0;
		previousBase = 0;
		score += pieceDropCnt;
		pieceDropCnt = 0;
		updateScoreDisplay();
	}
	
	public static void pieceDropped()
	{
		pieceDropCnt++;
	}
	
	public static int getScore()
	{
		return score;
	}
	
	public static String getScoreString()
	{
		return ((new StringBuilder().append(getScore()).toString()));
	}
	
	public static void updateScoreDisplay()
	{
		Tetris.btn_score.setText(getScoreString());
	}
}
