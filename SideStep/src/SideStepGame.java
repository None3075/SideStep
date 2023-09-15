import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SideStepGame {
	public static void main(String[] args) {
		
		Usernamewindow user = new Usernamewindow();
	}
	public static void music() {
		try {
			File musicpath = new File("Resources/Misc/music.wav");
			if (musicpath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-20.0f);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else {
				System.out.println("No music file detected");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
 }