package tetris;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Custom JPanel with specified background image
 */
public class ImageBGPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private String imagePath;
	
	/**
	 * Create a new JPanel with a background image
	 * @param imagePath directory and filename of background image
	 */
	public ImageBGPanel(String imagePath)
	{
		super();
		setOpaque(false);
		this.imagePath = imagePath;
	}
	
	/**
	 * Custom drawing
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	    
	    try
	    {
		    Image bgImage = ImageIO.read(new File(imagePath));
		    if (bgImage != null) g.drawImage(bgImage,((this.getWidth()-bgImage.getWidth(null))/2),0,this);
	    }
	    
	    catch(IOException e) {}
	}
}
