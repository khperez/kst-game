package tetris;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class highestScore {
	
	private final int SIZE = 5;		// ONLY GET 5 HIGHEST SCORES
	
	protected int[] tempScores = new int[100];
	protected String[] playerNames = new String[100];
	
	protected int[] highestScores = new int[SIZE];
	protected String[] highestNames = new String[SIZE];
	
	private String fileName = "resources/highestScores.txt";
	private int counter = 0;

	public void saveScores(int score, String name)
	{
		PrintWriter textFile = null;
		
		try {
			//Append mode set here
			textFile = new PrintWriter(new FileWriter(fileName, true));
			
			//Write score
			String temp = score + " " + name;
			textFile.println(temp);
			textFile.close();
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "Something is wrong!", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
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
			JOptionPane.showMessageDialog(null, "Something is wrong!", "Error", JOptionPane.WARNING_MESSAGE);
		}
			
		
		//Bubble sorting using
		//Temporary variable to hold values in List
		int temp;
		String temp1;
		
		//Outer loop
		for(int a=1; a <= tempScores.length; a++)
		{
			//Inner loop
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
		
		//Set highest scores into an array
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
}