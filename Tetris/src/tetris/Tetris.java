package tetris;

/**
 * Create and control the game Tetris.
 * 
 *
 *
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;

public class Tetris extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int IFW = JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
	
	public static int WINDOWWIDTH = 1200;
	public static int WINDOWHEIGHT = 1000;
	
	private Game game;
	private JFrame f;
	private EventController ec;

	/** Sets up the parts for the Tetris game, display and user control
	 * @param f frame to draw the game on
	 */
	public Tetris()
	{
		// class to construct a panel with an image for background
		class BGPanel extends JPanel
		{
			private static final long serialVersionUID = 1L;

			public BGPanel()
			{
				super();
			}
			
			public void paintComponent(Graphics g)
			{
			    super.paintComponent(g);
			    
			    try
			    {
			    	Image bgImage = ImageIO.read(new File("images/tetris_bg.png"));
				    
				    if (bgImage != null) g.drawImage(bgImage,0,0,this);
			    }
			    
			    catch(IOException e){}
			}
		}
		
		// create game
		game = new Game(this);
		
		// generate window
		f = new JFrame("Tetris");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(WINDOWWIDTH, WINDOWHEIGHT);
		f.setVisible(true);
		f.setLayout(new BorderLayout());
		f.setResizable(false);

		// generate background panel
		BGPanel bg = new BGPanel();
		bg.setLayout(new BorderLayout());
		f.add(bg, BorderLayout.CENTER);
		
		// generate event controller
		ec = new EventController(game);
		
		// set active game play panel transparent
		setOpaque(false);
		
		// pause button
		JButton btn_Pause = new JButton("Pause");
		btn_Pause.addActionListener(ec);
		btn_Pause.setOpaque(false);
		
		// right panel
		JPanel panel_Right = new JPanel();
		panel_Right.setPreferredSize(new Dimension(300, WINDOWHEIGHT));
		panel_Right.setOpaque(false);		
		panel_Right.setLayout(new GridLayout(10,1));
		
		panel_Right.add(new EmptyPanel());
		panel_Right.add(btn_Pause);
		
		attachActions(btn_Pause);
		
		bg.add(this, BorderLayout.CENTER);
		bg.add(panel_Right, BorderLayout.EAST);
	}

	/** Updates the display
	 */
	public void update()
	{
		repaint();
	}

	/** Paint the current state of the game
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	    
		game.draw(g);
		
		if (game.isGameOver()) {
			g.setFont(new Font("Palatino", Font.BOLD, 40));
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER YOU SUCK", (WINDOWWIDTH/2)-250, 60);
		}
	}
	
	public void attachActions(JButton btn)
	{
		// key actions, had to remove from EventController because KeyListener does not work with multiple panels within the same frame
		
		// up key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), "key_up");
		btn.getActionMap().put("key_up", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) { if(!game.isGameOver()) game.rotatePieceCW(); }
		});
		
		// kp up key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("KP_UP"), "key_kp_up");
		btn.getActionMap().put("key_kp_up", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) { if(!game.isGameOver())game.rotatePieceCW(); }
		});
		
		// down key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), "key_down");
		btn.getActionMap().put("key_down", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) { if(!game.isGameOver())ec.handleMove(Direction.DOWN); }
		});
		
		// kp down key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("KP_DOWN"), "key_kp_down");
		btn.getActionMap().put("key_kp_down", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) { if(!game.isGameOver())ec.handleMove(Direction.DOWN);  }
		});
		
		// right key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), "key_right");
		btn.getActionMap().put("key_right", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) { if(!game.isGameOver())ec.handleMove(Direction.RIGHT); }
		});
		
		// kp right key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("KP_RIGHT"), "key_kp_right");
		btn.getActionMap().put("key_kp_right", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) { if(!game.isGameOver())ec.handleMove(Direction.RIGHT); }
		});
		
		// left key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), "key_left");
		btn.getActionMap().put("key_left", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) { if(!game.isGameOver())ec.handleMove(Direction.LEFT); }
		});
		
		// kp left key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("KP_LEFT"), "key_kp_left");
		btn.getActionMap().put("key_kp_left", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) { if(!game.isGameOver())ec.handleMove(Direction.LEFT); }
		});
		
		// z key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("Z"), "key_Z");
		btn.getActionMap().put("key_Z", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e)
			{
				if(!game.isGameOver())
					game.rotatePieceCCW();
			}
		});
		
		// x key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("X"), "key_X");
		btn.getActionMap().put("key_X", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e)
			{
				if(!game.isGameOver())
					game.rotatePieceCW();
			}
		});

		// q (quit) key
		btn.getInputMap(IFW).put(KeyStroke.getKeyStroke("Q"), "key_Q");
		btn.getActionMap().put("key_Q", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e)
			{
				ec.stopTimer();
				f.dispose();
			}
		});
	}
}
