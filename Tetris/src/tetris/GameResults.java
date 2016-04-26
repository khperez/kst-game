package tetris;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Displays the results at the of a game and provides user with different options
 * Class previously titled 'PlayAgain'
 */
public class GameResults
{	
	private static HighestScore highestScore = new HighestScore();
	private static String temp= "";
	
	protected static String saveName = "";
	private JTextField playerName;

	/** Setup game results window with the following
	 * - High Scores List
	 * - Play Again Button
	 * - Exit Button
	 */
	public GameResults()
	{
		super();
		
		JFrame f = new JFrame("Game Over");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 700);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		
		// background panel
		ImageBGPanel again = new ImageBGPanel("images/tetris_go.png");
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
						
						
						// save score and name to text file
						if(playerName.getText().equals(""))
						{
							saveName = "???";
						}
						else saveName = playerName.getText();
						
						highestScore.saveScores(ScoreCounter.getScore(), saveName);
						
						// reset score to 0
						ScoreCounter.resetScore();
						
						// bring back the menu
						f.setVisible(false);
						f.dispose();
						new Menu();
					}
					
					else if(name.equals("EXIT"))
					{
						// save score and name to text file
						if(playerName.getText().equals(""))
						{
							saveName = "???";
						}
						else saveName = playerName.getText();
						
						highestScore.saveScores(ScoreCounter.getScore(), saveName);
						
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
		finalScore.setFont(GameFont.fontWithSize(20f));
		finalScore.setForeground(textColor);
		
		JLabel hsS = new JLabel("", JLabel.CENTER);
		hsS.setFont(GameFont.fontWithSize(20f));
		hsS.setForeground(textColor);
				
		//load highest scores
		highestScore.getScores();
		
		//compare player's score with the top
		if((ScoreCounter.getScore()) >= (highestScore.highestScores[0]))
		{
			temp = "YOUR SCORE IS: " + ScoreCounter.getScoreString();
			finalScore.setText(temp);
			hsS.setText("NEW HIGH SCORE!");
		}
		else
		{
			temp = "YOUR SCORE IS: " + ScoreCounter.getScoreString();
			finalScore.setText(temp);
			hsS.setText("");
		}
		
		// create panel for player name
		JPanel name = new JPanel();
		name.setLayout(new GridLayout(1, 2, 5, 0));
		
		// create label "Enter name:" 
		JLabel enterName = new JLabel("ENTER NAME: ", JLabel.CENTER);
		enterName.setFont(GameFont.fontWithSize(fontSize));
		enterName.setForeground(textColor);
		
		// create text field for player enter name
		playerName = new JTextField();
		playerName.setForeground(Color.RED);
		playerName.setOpaque(false);
		playerName.setFont(GameFont.fontWithSize(25f));
		
		// force all caps
		playerName.addKeyListener(new KeyAdapter()
		{
			  public void keyTyped(KeyEvent e)
			  {
				    char keyChar = e.getKeyChar();
				    if (Character.isLowerCase(keyChar))
				    {
				    	e.setKeyChar(Character.toUpperCase(keyChar));
				    }
			  }
		});

		name.add(enterName);
		name.add(playerName);
		name.setOpaque(false);		
		
		// create layout for labels to display highest scores
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(6,1));
		scorePanel.setOpaque(false);
		
		// create labels for highest scores
		JLabel highest = new JLabel("HIGH SCORES", JLabel.CENTER);
		highest.setFont(GameFont.fontWithSize(fontSize));
		highest.setForeground(textColor);
		
		String string1 = " 1) " + highestScore.highestNames[0] + "  |  " + highestScore.highestScores[0];
		JLabel firstS = new JLabel(string1);
		firstS.setFont(GameFont.fontWithSize(fontSize));
		firstS.setForeground(textColor);
		
		String string2 = " 2) " + highestScore.highestNames[1] + "  |  " + highestScore.highestScores[1];
		JLabel secondS = new JLabel(string2);
		secondS.setFont(GameFont.fontWithSize(fontSize));
		secondS.setForeground(textColor);
		
		String string3 = " 3) " + highestScore.highestNames[2] + "  |  " + highestScore.highestScores[2];
		JLabel thirdS = new JLabel(string3);
		thirdS.setFont(GameFont.fontWithSize(fontSize));
		thirdS.setForeground(textColor);
		
		String string4 = " 4) " + highestScore.highestNames[3] + "  |  " + highestScore.highestScores[3];
		JLabel fourthS = new JLabel(string4);
		fourthS.setFont(GameFont.fontWithSize(fontSize));
		fourthS.setForeground(textColor);
		
		String string5 = " 5) " + highestScore.highestNames[4] + "  |  " + highestScore.highestScores[4];
		JLabel fifthS = new JLabel(string5);
		fifthS.setFont(GameFont.fontWithSize(fontSize));
		fifthS.setForeground(textColor);
		
		// remove high score data from memory in case user plays again
		highestScore.clearAll();
		
		scorePanel.add(highest);
		scorePanel.add(firstS);
		scorePanel.add(secondS);
		scorePanel.add(thirdS);
		scorePanel.add(fourthS);
		scorePanel.add(fifthS);
		
		
		JPanel sP = new JPanel();
		sP.setLayout(new GridLayout(3,1));
		sP.setOpaque(false);
		
		sP.add(finalScore);
		sP.add(hsS);
		sP.add(name);
		
		again.add(over);
		again.add(scorePanel);
		again.add(sP);
		
		// create layout for play and exit buttons at bottom
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(2,1));
		btnPanel.setOpaque(false);
		
		btnPanel.add(layoutPlay);
		btnPanel.add(layoutExit);
		
		again.add(btnPanel);
		
		f.add(again);
	
		f.setVisible(true);
	}
}
