package tetris;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 * Universal font
 */
public class GameFont
{
	public static Font font;
	
	/**
	 * Load a font from a specified file path
	 * @param fontPath the directory and filename of the font to load
	 */
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
	
	/**
	 * Font with custom size
	 * @param size desired size
	 * @return modified font
	 */
	public static Font fontWithSize(float size)
	{
		return font.deriveFont(size);
	}
	
	/**
	 * Font with custom style
	 * @param style desired style
	 * @return modified font
	 */
	public static Font fontWithStyle(int style)
	{
		return font.deriveFont(style);
	}
	
	/**
	 * Font with custom size and style
	 * @param size desired size
	 * @param style desired style
	 * @return modified font
	 */
	public static Font fontWithChanges(float size, int style)
	{
		return font.deriveFont(style, size);
	}
}
