package tetris;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class HighestScore
{
	private final int SIZE = 5;		// list of 5 high scores
	
	protected int[] tempScores = new int[100];
	protected int[] highestScores = new int[SIZE];
	private String fileName = "resources/highestScores.dat";
	private int counter = 0;

	/**
	 * Append and save current high score to data file
	 * @param score to save
	 */
	public void saveScores(int score)
	{
		PrintWriter textFile = null;
		
		try {
			//Append mode set here
			textFile = new PrintWriter(new FileWriter(fileName, true));
			
			//Write score
			textFile.println(score);
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
				if((inputStream.hasNextInt()) && (counter < 100))
				{
					tempScores[counter] = inputStream.nextInt();
					counter++;
				}
				else
					break;
			}
			inputStream.close();
		}
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Something went wrong!", "Error", JOptionPane.WARNING_MESSAGE);
		}
			
		
		// bubble sorting using
		// temporary variable to hold values in List
		int temp;
		
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
				}
			}
		}
		
		// set highest scores into an array
		for(int x=0; x < SIZE; x++)
		{
			highestScores[x] = tempScores[x];
		}
	}
}
