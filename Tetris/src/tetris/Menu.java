package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a menu for a game of Tetris from which user can specify different configurations
 */
public class Menu extends JPanel
{
	private static final long serialVersionUID = 1L;

	public static Difficulty difficulty = Difficulty.NORMAL;
	public static Size boardSize = Size.MEDIUM;

	/**
	 * Create menu panel
	 */
	public Menu()
	{
		// window frame
		JFrame f = new JFrame("TETRIS");
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(Tetris.WINDOWWIDTH, Tetris.WINDOWHEIGHT);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		
		// panel
		setBackground(Color.BLACK);
		setLayout(new GridLayout(12,1));

		// generate buttons
		float btnFontSize = 23f;
		Color btnTextColor = Color.WHITE;
		
		Button btn_lvlB = new Button("BEGINNER", GameFont.font, btnFontSize, Color.YELLOW, btnTextColor);
		Button btn_lvlN = new Button("NORMAL", GameFont.font, btnFontSize, Color.GREEN, btnTextColor);
		Button btn_lvlA = new Button("ADVANCED", GameFont.font, btnFontSize, Color.ORANGE, btnTextColor);
		Button btn_lvlL = new Button("LEGEND", GameFont.font, btnFontSize, Color.RED, btnTextColor);
		
		Button btn_sizeS = new Button("SMALL", GameFont.font, btnFontSize, Color.CYAN, btnTextColor);
		Button btn_sizeM = new Button("MEDIUM", GameFont.font, btnFontSize, Color.BLUE, btnTextColor);
		Button btn_sizeL = new Button("LARGE", GameFont.font, btnFontSize, Color.MAGENTA, btnTextColor);
		
		Button btn_play = new Button("PLAY", GameFont.font, btnFontSize, Color.GREEN, btnTextColor);
		
		// set default selected
		btn_lvlN.setSelected(true);
		btn_sizeM.setSelected(true);
		
		// action listener for all button events
		ActionListener btnListen = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String src = e.getActionCommand();
				
				GameMusic.buttonPress();
				
				if (src.equals("PLAY"))
				{
					// start game, pass active window along
					f.setVisible(false);
					new Tetris();
				}
				
				else if (src.equals("BEGINNER"))
				{
					difficulty = Difficulty.BEGINNER;
					btn_lvlB.setSelected(true);
					btn_lvlN.setSelected(false);
					btn_lvlA.setSelected(false);
					btn_lvlL.setSelected(false);
				}
				
				else if (src.equals("NORMAL"))
				{
					difficulty = Difficulty.NORMAL;
					btn_lvlB.setSelected(false);
					btn_lvlN.setSelected(true);
					btn_lvlA.setSelected(false);
					btn_lvlL.setSelected(false);
				}
				
				else if (src.equals("ADVANCED"))
				{
					difficulty = Difficulty.ADVANCED;
					btn_lvlB.setSelected(false);
					btn_lvlN.setSelected(false);
					btn_lvlA.setSelected(true);
					btn_lvlL.setSelected(false);
				}
				
				else if (src.equals("LEGEND"))
				{
					difficulty = Difficulty.LEGEND;
					btn_lvlB.setSelected(false);
					btn_lvlN.setSelected(false);
					btn_lvlA.setSelected(false);
					btn_lvlL.setSelected(true);
				}
				
				else if (src.equals("SMALL"))
				{
					boardSize = Size.SMALL;
					btn_sizeS.setSelected(true);
					btn_sizeM.setSelected(false);
					btn_sizeL.setSelected(false);
				}
				
				else if (src.equals("MEDIUM"))
				{
					boardSize = Size.MEDIUM;
					btn_sizeS.setSelected(false);
					btn_sizeM.setSelected(true);
					btn_sizeL.setSelected(false);
				}
				
				else if (src.equals("LARGE"))
				{
					boardSize = Size.LARGE;
					btn_sizeS.setSelected(false);
					btn_sizeM.setSelected(false);
					btn_sizeL.setSelected(true);
				}
			}
		};
		
		// associate action listener to buttons
		btn_lvlB.addActionListener(btnListen);
		btn_lvlN.addActionListener(btnListen);
		btn_lvlA.addActionListener(btnListen);
		btn_lvlL.addActionListener(btnListen);
		btn_sizeS.addActionListener(btnListen);
		btn_sizeM.addActionListener(btnListen);
		btn_sizeL.addActionListener(btnListen);
		btn_play.addActionListener(btnListen);
	
		// group difficulty buttons
		ImageBGPanel layoutDiff = new ImageBGPanel("images/menu_bar.png");
		layoutDiff.setLayout(new GridLayout(1, 6));
		
		layoutDiff.add(new EmptyPanel());
		layoutDiff.add(btn_lvlB);
		layoutDiff.add(btn_lvlN);
		layoutDiff.add(btn_lvlA);
		layoutDiff.add(btn_lvlL);
		layoutDiff.add(new EmptyPanel());
		
		// group board size buttons
		ImageBGPanel layoutSize = new ImageBGPanel("images/menu_bar.png");
		layoutSize.setLayout(new GridLayout(1,7));
		
		layoutSize.add(new EmptyPanel());
		layoutSize.add(new EmptyPanel());
		layoutSize.add(btn_sizeS);
		layoutSize.add(btn_sizeM);
		layoutSize.add(btn_sizeL);
		layoutSize.add(new EmptyPanel());
		layoutSize.add(new EmptyPanel());
		
		// group play button
		ImageBGPanel layoutPlay = new ImageBGPanel("images/menu_btn.png");
		layoutPlay.setLayout(new GridLayout(1,1));
		layoutPlay.add(btn_play);
		
		// create group labels
		JLabel lbl_diff = new JLabel("DIFFICULTY", JLabel.CENTER);
		lbl_diff.setFont(GameFont.fontWithSize(26f));
		lbl_diff.setVerticalAlignment(JLabel.BOTTOM);
		lbl_diff.setForeground(new Color(255, 255, 255, 80));
		
		JLabel lbl_size = new JLabel("BOARD SIZE", JLabel.CENTER);
		lbl_size.setFont(GameFont.fontWithSize(26f));
		lbl_size.setVerticalAlignment(JLabel.BOTTOM);
		lbl_size.setForeground(new Color(255, 255, 255, 80));
		
		// setup all panels on main panel, add play button
		add(new EmptyPanel());
		add(new EmptyPanel());
		add(new EmptyPanel());
		add(new EmptyPanel());
		add(new EmptyPanel());
		add(lbl_diff);
		add(layoutDiff);
		add(lbl_size);
		add(layoutSize);
		add(new EmptyPanel());
		add(layoutPlay);
		
		// add main panel to frame, show window
		f.add(this);
		f.setVisible(true);
		
		// allocate the game theme music and sound effects
		new GameMusic();
	}

	/**
	 * Custom background image drawing on panel
	 */
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    
	    try
	    {
		    Image bgImage = ImageIO.read(new File("images/menu_bg.png"));
		    if (bgImage != null) g.drawImage(bgImage,0,0,this);
	    }
	    
	    catch(IOException e) {}
	}
	
	public static void main(String[] args)
	{	
		new GameFont("resources/ARCADEPI.TTF"); // allocate font
		new ScoreCounter(); // allocate score counter
		new Menu(); // allocate and display menu
	}
}
