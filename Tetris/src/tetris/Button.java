package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton
{
	private static final long serialVersionUID = 1L;
	
	private boolean selected;
	private String buttonType;
	private Color colorUnselected;
	private Color colorSelected;
	private String imageSelectedPath;
	private String imageUnselectedPath;
	
	/**
	 * Creates a button with custom settings
	 * @param text the text to display
	 * @param font font to generate the text with
	 * @param fontSize custom size for font
	 * @param colorActive text color for button in active state
	 * @param colorInactive text color button in inactive state
	 */
	public Button(String text, Font font, float fontSize, Color colorActive, Color colorInactive)
	{
		super(text);
		
		setFont(font.deriveFont(fontSize));
		setForeground(colorInactive);
		
		setContentAreaFilled(false);
		setBorderPainted(false);
		setMargin(new Insets(0, 0, 0, 0));
		
		buttonType = "text";
		this.colorSelected = colorActive;
		this.colorUnselected = colorInactive;
	}
	
	/**
	 * Creates a button with images rather than text
	 * @param imageUnselectedPath unselected state image
	 * @param imageSelectedPath selected state image
	 */
	public Button(String imageUnselectedPath, String imageSelectedPath)
	{
		super(new ImageIcon(imageUnselectedPath));
		
		setContentAreaFilled(false);
		setBorderPainted(false);
		setMargin(new Insets(0, 0, 0, 0));
		
		buttonType = "image";
		this.imageSelectedPath = imageSelectedPath;
		this.imageUnselectedPath = imageUnselectedPath;
	}
	
	/**
	 * Returns if button is selected
	 */
	public boolean isSelected()
	{
		return selected;
	}
	
	/**
	 * Sets the buttons state
	 * @param state state to set button to
	 */
	public void setSelected(boolean state)
	{
		selected = state;
		
		if (selected == true)
		{
			if (buttonType.equals("text"))
			{
				setForeground(colorSelected);
				repaint();
			}
			
			else if (buttonType.equals("image"))
			{
				setIcon(new ImageIcon(imageSelectedPath));
			}
		}
		
		else
		{
			if (buttonType.equals("text"))
			{
				setForeground(colorUnselected);
				repaint();
			}
			
			else if (buttonType.equals("image"))
			{
				setIcon(new ImageIcon(imageUnselectedPath));
			}
		}
	}
	
	/**
	 * Change the button's displayed text
	 * @param text text to display
	 */
	public void changeText(String text)
	{
		setText(text);
	}
	
	/**
	 * Custom drawing components
	 */
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
        
	    if ((buttonType.equals("text")) && (selected == true))
	    {
	    	int width = 20;
	    	int height = 2;
	    	int yCenterOffset = 15;
	    	
	    	// draw a single bar underneath text when button is selected
	    	g.setColor(colorSelected);
			g.fillRect((int)this.getSize().getWidth()/2-(width/2), (int)this.getSize().getHeight()/2+yCenterOffset, width, height);
	    }
	}
}
