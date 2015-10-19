package a4.sounds;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Sound {
	private AudioClip myClip ;
	public Sound(String fileName) {
		try {
			String slash = File.separator ; // predefined Java constant
			String soundURL = "." + slash + "a4" + slash + "sounds" + slash + fileName;
			File file = new File(soundURL);
			if (file.exists()) {
				myClip = Applet.newAudioClip(file.toURI().toURL());
			} else {
				throw new RuntimeException("Sound: file not found: " + fileName);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Sound: malformed URL: " + e);
		}
	}
	
	public void play() {
		myClip.play();
	}
	public void loop() {
		myClip.loop();
	}
	public void stop() {
		myClip.stop();
	}
}