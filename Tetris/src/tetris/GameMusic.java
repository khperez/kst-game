package tetris;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Game theme music and sounds effects
 */
public class GameMusic implements LineListener
{
	public static Clip themeSong;
	private static boolean loopEffect = true;

	/**
	 * Load game music
	 */
	public GameMusic()
	{
		// play Tetris theme song
		try
	    {
			themeSong = AudioSystem.getClip();
			themeSong.open(AudioSystem.getAudioInputStream(new File("resources/tetris.wav")));
			themeSong.addLineListener(this);
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	
	/**
	 * Begin playing theme song
	 */
	public static void beginThemeSong()
	{
		themeSong.start();
	}
	
	/**
	 * Resume playing theme song
	 */
	public static void resumeThemeSong()
	{
		if (!(themeSong.isRunning()))
			{
				loopEffect = true;
				themeSong.start();
			}
	}
	
	/**
	 * Pause playing of theme song
	 */
	public static void pauseThemeSong()
	{
		if (themeSong.isRunning())
			{
				loopEffect = false;
				themeSong.stop();
			}
	}
	
	/**
	 * Song updated
	 */
	public void update(LineEvent event)
	{
		LineEvent.Type t = event.getType();
		if (loopEffect == true)
		{
			// if song was ended and can be looped, set to beginning position and play again
			if (t == LineEvent.Type.STOP)
			{
				themeSong.setFramePosition(0);
				themeSong.start();
			}
		}
	}
	
	/**
	 * Destroy loaded song from memory
	 */
	public static void disposeMusic()
	{
		loopEffect = false;
		themeSong.stop();
		themeSong.drain();
	}
	
	/**
	 * Load sound effect for button presses
	 */
	public static void buttonPress()
	{
		Clip sound;
		
		try
	    {
			sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(new File("resources/buttonClick.wav")));
			sound.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		
	}
}
