package tetris;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class GameFont
{
	public static Font font;
	
	public GameFont(String fontPath)
	{
		try
		{
			File font_file = new File(fontPath);
			font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(12f);
		}
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (FontFormatException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Font fontWithSize(float size)
	{
		return font.deriveFont(size);
	}
	
	public static Font fontWithStyle(int style)
	{
		return font.deriveFont(style);
	}
	
	public static Font fontWithChanges(float size, int style)
	{
		return font.deriveFont(style, size);
	}
}
