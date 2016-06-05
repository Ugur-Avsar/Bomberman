package sound;

import static sun.audio.AudioPlayer.player;

import java.io.FileInputStream;
import java.io.IOException;

import sun.audio.AudioStream;
import toolbox.TimeManager;

public class SoundPlayer {
	private AudioStream stream;
	private String source;
	private boolean playing;

	public SoundPlayer(String soundName) {
		source = soundName;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void play() {
		try {
			player.stop(stream);
			stream = new AudioStream(new FileInputStream("./res/sounds/" + source + ".wav"));
			System.out.println(TimeManager.getCurrentTime() + "... Audio '" + source + "' playing.");
			player.start(stream);
			playing = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		if (playing) {
			System.out.println(TimeManager.getCurrentTime() + "... Audio '" + source + "' paused.");
			player.stop(stream);
			playing = false;
		}
	}

	public void stop() {
		try {
			System.out.println(TimeManager.getCurrentTime() + "... Audio '" + source + "' stopped.");
			stream.close();
			playing = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}