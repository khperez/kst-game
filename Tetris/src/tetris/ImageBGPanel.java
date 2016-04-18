package tetris;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageBGPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private String imagePath;
	
	public ImageBGPanel(String imagePath)
	{
		super();
		setOpaque(false);
		this.imagePath = imagePath;
	}
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
