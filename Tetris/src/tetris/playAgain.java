package tetris;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class playAgain {
	
	private static highestScore highestScore = new highestScore();
	private static String temp= "";
	protected static String saveName = "";

	public playAgain()
	{
		JFrame again = new JFrame("GAME OVER");
		
		again.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		again.setSize(500, 400);
		again.setResizable(false);
		
		
		//panel
		again.setBackground(Color.BLACK);
		again.setLayout(new GridLayout(5,1));
		
		//generate buttons
		float fontSize = 23f;
		Color textColor = Color.BLACK;
		
		Button playAgain = new Button("PLAY AGAIN", GameFont.font, fontSize, Color.BLUE, textColor);
		Button exit = new Button("EXIT", GameFont.font, fontSize, Color.RED, textColor);
		
		// action listener for buttons
		ActionListener btnListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String name = e.getActionCommand();
					
					if(name.equals("PLAY AGAIN"))
					{
						// RESET SCORE TO 0 
						ScoreCounter.resetScore();
						
						again.setVisible(false);
						
						//ADD SOME CODE HERE TO STOP THE MUSIC
						
						new Menu();
					}
					
					else if(name.equals("EXIT"))
					{
						System.exit(0);
					}
				}
			};
			
		playAgain.addActionListener(btnListener);
		exit.addActionListener(btnListener);
		
		// create GAME OVER LABEL
		JLabel over = new JLabel("GAME OVER", JLabel.CENTER);
		over.setFont(GameFont.fontWithSize(30f));
		
		// create label display player's score
		JLabel finalScore = new JLabel("");
		//String temp = "YOUR SCORE IS: " + ScoreCounter.getScoreString();
		
		
	
		
		// create panel for player enter name
		JPanel playerName = new JPanel();
		playerName.setLayout(new GridLayout(1,3));
		
		// create label "Enter name: "
		JLabel enterName = new JLabel("ENTER 3 LETTERS NAME: ");
		
		// create text field for player to enter name
		JTextField name = new JTextField();
		
		// create button to save name
		Button save = new Button("SAVE", GameFont.font, fontSize, Color.BLUE, textColor);
		save.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						saveName = name.getText();
						
						// save score and name to text file
						highestScore.saveScores(ScoreCounter.getScore(), saveName);
					}
				});
		
		
		
		playerName.add(enterName);
		playerName.add(name);
		playerName.add(save);
		
		
		
		//load highest scores
		highestScore.getScores();
		
		//compare player's score with the top
		if((ScoreCounter.getScore()) >= (highestScore.highestScores[0]))
		{
			temp = "YOUR SCORE : " + ScoreCounter.getScoreString() + "  NEW HIGHEST SCORE! CONGRATULATION!";
			finalScore.setText(temp);
		}
		else
		{
			temp = "YOUR SCORE IS: " + ScoreCounter.getScoreString();
			finalScore.setText(temp);
		}
		
		
		// create layout for labels to display highest scores
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(6,1));
		
		// create labels for highest scores
		JLabel highest = new JLabel("Highest Scores");
		
		String string1 = "1. " + highestScore.highestScores[0] + "  -  " + highestScore.highestNames[0];
		JLabel firstS = new JLabel(string1);
		
		String string2 = "2. " + highestScore.highestScores[1] + "  -   " + highestScore.highestNames[1];
		JLabel secondS = new JLabel(string2);
		
		String string3 = "3. " + highestScore.highestScores[2] + "  -   " + highestScore.highestNames[2];
		JLabel thirdS = new JLabel(string3);
		
		String string4 = "4. " + highestScore.highestScores[3] + "  -   " + highestScore.highestNames[3];
		JLabel fourthS = new JLabel(string4);
		
		String string5 = "5. " + highestScore.highestScores[4] + "  -   " + highestScore.highestNames[4];
		JLabel fifthS = new JLabel(string5);
		
		scorePanel.add(highest);
		scorePanel.add(firstS);
		scorePanel.add(secondS);
		scorePanel.add(thirdS);
		scorePanel.add(fourthS);
		scorePanel.add(fifthS);
	
		
		again.add(over);
		again.add(scorePanel);
		again.add(finalScore);
		again.add(playerName);
		
		// create layout for play and exit buttons at bottom
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,2));
		
		btnPanel.add(playAgain);
		btnPanel.add(exit);
		
		again.add(btnPanel);
		
		
		again.setVisible(true);
	}
}
