package ie.gmit.ai;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundManager {

	private Clip clip;
	
	public SoundManager()
	{		
		try 
		{
			URL url = this.getClass().getClassLoader().getResource("Sound/dungeon.mid");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);


			clip = AudioSystem.getClip();		
			clip.open(audioIn);	
		 
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		playMusic();
	}
	
	public void playMusic()
	{		
		if(GlobalsVars.MUSIC_PLAYING)
		{
			 clip.start();
			 clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else
			clip.stop();

		GlobalsVars.MUSIC_PLAYING = !GlobalsVars.MUSIC_PLAYING;
	}
	
}
