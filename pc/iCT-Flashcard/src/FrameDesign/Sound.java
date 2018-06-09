package FrameDesign;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import sun.applet.Main;

public class Sound {
	
	private Clip clip;

	public Sound(String path) {
		try{
          AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(Main.class.getResource(path));
          Clip clip = AudioSystem.getClip();
          clip.loop(1);
          clip.open(audioInputStream);
          this.setClip(clip);
         }
        catch(Exception ex)
        { 
        	System.out.println(ex);
        }
	}
	
	public Sound(String path, int noOfLoops) {
		try{
          AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(Main.class.getResource(path));
          Clip clip = AudioSystem.getClip();
          clip.loop(noOfLoops);
          clip.open(audioInputStream);
          this.setClip(clip);
         }
        catch(Exception ex)
        { 
        	System.out.println(ex);
        }
	}
	
	public void play() {
		try {
			clip.setFramePosition(0);
	        getClip().start();
        }
        	catch(Exception ex)
        { 
        	System.out.println(ex);
        }
	}
	
	public void stop() {
		try {
	        getClip().stop();
        }
        	catch(Exception ex)
        { 
        	System.out.println(ex);
        }
	}
	
	public void setVolume(double gain) {
		// Get the gain control from clip
		FloatControl gainControl = (FloatControl) getClip().getControl(FloatControl.Type.MASTER_GAIN);
		// set the gain (between 0.0 and 1.0)  
		float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		gainControl.setValue(dB);
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}
	
	

}