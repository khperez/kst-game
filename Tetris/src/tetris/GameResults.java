package tetris;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Displays the results at the of a game and provides user with different options
 * Class previously titled 'PlayAgain'
 */
public class GameResults
{	
	private static HighestScore highestScore = new HighestScore();
	private static String temp= "";

	/** Setup game results window with the following
	 * - High Scores List
	 * - Play Again Button
	 * - Exit Button
	 */
	public GameResults()
	{
		super();
		
		JFrame again = new JFrame("Game Over");
		
		again.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		again.setSize(500, 700);
		again.setResizable(false);
		again.setLocationRelativeTo(null);
		
		//panel
		again.setBackground(Color.BLACK);
		again.setLayout(new GridLayout(4,1));
		
		//generate buttons
		float fontSize = 26f;
		Color textColor = Color.WHITE;
		
		Button playAgain = new Button("PLAY AGAIN", GameFont.font, fontSize, Color.BLUE, textColor);
		Button exit = new Button("EXIT", GameFont.font, fontSize, Color.RED, textColor);
		
		ImageBGPanel layoutPlay = new ImageBGPanel("images/menu_btn.png");
		layoutPlay.setLayout(new GridLayout(1,1));
		layoutPlay.add(playAgain);
		
		ImageBGPanel layoutExit = new ImageBGPanel("images/menu_btn.png");
		layoutExit.setLayout(new GridLayout(1,1));
		layoutExit.add(exit);
		
		// action listener for buttons
		ActionListener btnListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// stop music
					GameMusic.disposeMusic();
					
					
					String name = e.getActionCommand();
					
					if(name.equals("PLAY AGAIN"))
					{
						// reset score to 0
						ScoreCounter.resetScore();
						
						// bring back the menu
						again.setVisible(false);
						again.dispose();
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
		
		// create "Game Over" label
		JLabel over = new JLabel("GAME OVER", JLabel.CENTER);
		over.setFont(GameFont.fontWithSize(42f));
		over.setForeground(Color.RED);
		
		// create label display player's score
		JLabel finalScore = new JLabel("", JLabel.CENTER);
		finalScore.setFont(GameFont.fontWithSize(36f));
		
		// save score to text file
		highestScore.saveScores(ScoreCounter.getScore());
		
		//load highest scores
		highestScore.getScores();
		
		//compare player's score with the top
		if((ScoreCounter.getScore()) >= (highestScore.highestScores[0]))
		{
			temp = "YOUR SCORE IS: " + ScoreCounter.getScoreString() + "! NEW HIGH SCORE!";
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
		scorePanel.setOpaque(false);
		
		// create labels for highest scores
		JLabel highest = new JLabel("HIGH SCORES", JLabel.CENTER);
		highest.setFont(GameFont.fontWithSize(fontSize));
		
		String string1 = " 1. " + highestScore.highestScores[0];
		JLabel firstS = new JLabel(string1);
		firstS.setFont(GameFont.fontWithSize(fontSize));
		
		String string2 = " 2. " + highestScore.highestScores[1];
		JLabel secondS = new JLabel(string2);
		secondS.setFont(GameFont.fontWithSize(fontSize));
		
		String string3 = " 3. " + highestScore.highestScores[2];
		JLabel thirdS = new JLabel(string3);
		thirdS.setFont(GameFont.fontWithSize(fontSize));
		
		String string4 = " 4. " + highestScore.highestScores[3];
		JLabel fourthS = new JLabel(string4);
		fourthS.setFont(GameFont.fontWithSize(fontSize));
		
		String string5 = " 5. " + highestScore.highestScores[4];
		JLabel fifthS = new JLabel(string5);
		fifthS.setFont(GameFont.fontWithSize(fontSize));
		
		scorePanel.add(highest);
		scorePanel.add(firstS);
		scorePanel.add(secondS);
		scorePanel.add(thirdS);
		scorePanel.add(fourthS);
		scorePanel.add(fifthS);
		
		again.add(over);
		again.add(scorePanel);
		again.add(finalScore);
		
		// create layout for play and exit buttons at bottom
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(2,1));
		btnPanel.setOpaque(false);
		
		btnPanel.add(layoutPlay);
		btnPanel.add(layoutExit);
		
		again.add(btnPanel);
	
		again.setVisible(true);
	}
}
