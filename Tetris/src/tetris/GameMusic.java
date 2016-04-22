package tetris;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class GameMusic implements LineListener
{
	public static Clip themeSong;
	private static boolean loopEffect = true;

	public GameMusic()
	{
		// play tetris theme song
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
	
	public static void beginThemeSong()
	{
		themeSong.start();
	}
	
	public static void resumeThemeSong()
	{
		if (!(themeSong.isRunning()))
			{
				loopEffect = true;
				themeSong.start();
			}
	}
	
	public static void pauseThemeSong()
	{
		if (themeSong.isRunning())
			{
				loopEffect = false;
				themeSong.stop();
			}
	}

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
	
	public static void disposeMusic()
	{
		loopEffect = false;
		themeSong.stop();
		themeSong.drain();
	}
	
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
