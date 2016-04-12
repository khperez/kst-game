package tetris;
// SERGIO 4/7/16 //

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.*;

public class Menu extends JPanel
{
	private static final long serialVersionUID = 1L;

	public Difficulty difficulty = Difficulty.BEGINNER;
	public Size boardSize = Size.SMALL;
	
	private Image bgImage = null;

	public Menu()
	{
		// frame
		JFrame f = new JFrame("TETRIS");
		f.add(this);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(700, 600);
		f.setResizable(false);
		
		// panel
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());

		// panel components
		MenuButton lvl_b = new MenuButton("images/btn_beginner", ".png");
		MenuButton lvl_i = new MenuButton("images/btn_normal", ".png");
		MenuButton lvl_a = new MenuButton("images/btn_advanced", ".png");
		MenuButton lvl_l = new MenuButton("images/btn_legendary", ".png");
		
		MenuButton size_s = new MenuButton("images/btn_small", ".png");
		MenuButton size_m = new MenuButton("images/btn_medium", ".png");
		MenuButton size_l = new MenuButton("images/btn_large", ".png");
		
		lvl_b.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
						difficulty = Difficulty.BEGINNER;
						lvl_b.setSelected(true);
						lvl_i.setSelected(false);
						lvl_a.setSelected(false);
						lvl_l.setSelected(false);
				}	
			});
	
		lvl_i.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
						difficulty = Difficulty.NORMAL;
						lvl_b.setSelected(false);
						lvl_i.setSelected(true);
						lvl_a.setSelected(false);
						lvl_l.setSelected(false);
				}	
			});
	
		lvl_a.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
						difficulty = Difficulty.ADVANCED;
						lvl_b.setSelected(false);
						lvl_i.setSelected(false);
						lvl_a.setSelected(true);
						lvl_l.setSelected(false);
				}	
			});
	
		lvl_l.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
						difficulty = Difficulty.LEGENDARY;
						lvl_b.setSelected(false);
						lvl_i.setSelected(false);
						lvl_a.setSelected(false);
						lvl_l.setSelected(true);
				}	
			});
		
		size_s.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						boardSize = Size.SMALL;
						size_s.setSelected(true);
						size_m.setSelected(false);
						size_l.setSelected(false);
					}
				});
		
		size_m.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boardSize = Size.MEDIUM;
				size_s.setSelected(false);
				size_m.setSelected(true);
				size_l.setSelected(false);
			}
		});
		
		size_l.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boardSize = Size.LARGE;
				size_s.setSelected(false);
				size_m.setSelected(false);
				size_l.setSelected(true);
			}
		});
		
		class emptyCell extends JPanel
		{
			private static final long serialVersionUID = 1L;

			public emptyCell()
			{
				super();
				setOpaque(false);
			}
		}
		
		JPanel layoutDiff = new JPanel();
		layoutDiff.setLayout(new GridLayout(1, 4));
		layoutDiff.setOpaque(false);
		
		layoutDiff.add(lvl_b);
		layoutDiff.add(lvl_i);
		layoutDiff.add(lvl_a);
		layoutDiff.add(lvl_l);
		
		JPanel layoutSize = new JPanel();
		layoutSize.setLayout(new GridLayout(1,3));
		layoutSize.setOpaque(false);
		
		layoutSize.add(size_s);
		layoutSize.add(size_m);
		layoutSize.add(size_l);
		
		JPanel layoutButtons = new JPanel();
		layoutButtons.setLayout(new GridLayout(4,1));
		layoutButtons.setOpaque(false);
		
		layoutButtons.add(new emptyCell());
		layoutButtons.add(layoutDiff);
		layoutButtons.add(new emptyCell());
		layoutButtons.add(layoutSize);
		
		MenuButton btnPlay = new MenuButton("images/btn_play", ".png");
		
		btnPlay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// start game
				f.setVisible(false);
				new Tetris(difficulty, boardSize);
			}
		});
		
		add(btnPlay, BorderLayout.SOUTH);
		add(layoutButtons, BorderLayout.NORTH);
		
		setVisible(true);
		f.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    
	    try
	    {
		    bgImage = ImageIO.read(new File("images/menu_bg.png"));
		    
		    if (bgImage != null)
		    {
		        g.drawImage(bgImage,0,0,this);
		    }
	    }
	    
	    catch(IOException e)
	    {
	    	
	    }

	}
	
	public static void main(String[] args)
	{	
		new Menu();
	}
}
