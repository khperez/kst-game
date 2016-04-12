package tetris;
// SERGIO 4/7/16 //

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MenuButton extends JButton
{
	private static final long serialVersionUID = 1L;
	
	public String imageName;
	public String extension;

	public MenuButton(String imageName, String extension)
	{
		super(new ImageIcon(imageName+extension));
		
		this.imageName = imageName;
		this.extension = extension;
		
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
	
	public void setSelected(boolean state)
	{
		if (state == true)
		{
			setIcon(new ImageIcon(imageName+"_sel"+extension));
		}
		
		else
		{
			setIcon(new ImageIcon(imageName+extension));
		}
	}
}
