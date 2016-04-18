package tetris;

public class ScoreCounter
{
	private static int score;
	
	public static void increaseScore(int multiplier)
	{
		score = score + (multiplier * 10);
	}
	
	public static int getScore()
	{
		return score;
	}
	
	public static String getScoreString()
	{
		return ((new StringBuilder().append(getScore()).toString()));
	}
}
