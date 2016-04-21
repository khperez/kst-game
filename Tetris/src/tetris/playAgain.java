package tetris;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class playAgain {

	public playAgain()
	{
		JFrame again = new JFrame("GAME OVER");
		
		again.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		again.setSize(500, 350);
		again.setResizable(false);
		
		
		//panel
		again.setBackground(Color.BLACK);
		again.setLayout(new GridLayout(3,1));
		
		//generate buttons
		float fontSize = 23f;
		Color textColor = Color.WHITE;
		
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
		String temp = "YOUR SCORE IS: " + ScoreCounter.getScoreString();
		finalScore.setText(temp);
		
		again.add(over);
		again.add(finalScore);
		
		// create layout for play and exit buttons at bottom
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,2));
		
		btnPanel.add(playAgain);
		btnPanel.add(exit);
		
		again.add(btnPanel);
		
		
		again.setVisible(true);
	}
}
