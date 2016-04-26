package tetris;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class HighestScore
{
	private final int SIZE = 5;		// list of 5 high scores
	
	protected int[] tempScores = new int[100];
	protected String[] playerNames = new String[100];
	
	protected String[] highestNames = new String[SIZE];
	protected int[] highestScores = new int[SIZE];
	private String fileName = "resources/highestScores.txt";
	private int counter = 0;

	/**
	 * Append and save current high score to data file
	 * @param score to save
	 */
	public void saveScores(int score, String name)
	{
		PrintWriter textFile = null;
		
		try
		{
			// append mode set here
			textFile = new PrintWriter(new FileWriter(fileName, true));
			
			// write score
			String temp = score + " " + name;
			textFile.println(temp);
			textFile.close();
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "Something went wrong!", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Load array of high scores from data file
	 */
	public void getScores()
	{
		Scanner inputStream = null;
		
		try {
			inputStream = new Scanner(new FileInputStream(fileName));
			
			
			while(inputStream.hasNextLine())
			{
				if(inputStream.hasNextInt())
				{
					tempScores[counter] = inputStream.nextInt();
				}
				else break;
							
				if(inputStream.hasNext())
				{
					playerNames[counter] = inputStream.next();
					counter++;
				}
				else break;
			}
			inputStream.close();
		}
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Something went wrong! 123", "Error", JOptionPane.WARNING_MESSAGE);
		}
			
		
		// bubble sorting using
		// temporary variable to hold values in List
		int temp;
		String temp1;
		
		// outer loop
		for(int a=1; a <= tempScores.length; a++)
		{
			// inner loop
			for(int b=0; b < ((tempScores.length)-1); b++)
			{
				if(tempScores[b+1] > tempScores[b])
				{
					temp = tempScores[b];
					tempScores[b] = tempScores[b+1];
					tempScores[b+1] = temp;
					
					
					// sort name
					temp1 = playerNames[b];
					playerNames[b] = playerNames[b+1];
					playerNames[b+1] = temp1;
				}
			}
		}
		
		// set highest scores into an array
		for(int x=0; x < SIZE; x++)
		{
			highestScores[x] = tempScores[x];
			
			if(playerNames[x] == null)
			{
				highestNames[x] = "";
			}
			else highestNames[x] = playerNames[x];
		}
	}
	
	/**
	 * Clears all high score data in  memory
	 */
	public void clearAll()
	{
		Arrays.fill(highestScores, 0);
		Arrays.fill(tempScores, 0);
		Arrays.fill(highestNames, null);
	}
}
