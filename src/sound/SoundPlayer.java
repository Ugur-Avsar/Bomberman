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
		try {
			stream = new AudioStream(new FileInputStream("./res/sounds/" + soundName + ".wav"));
			System.out.println(soundName + ".wav ... Sound loaded");
		} catch (IOException e) {
			System.err.println("Sound-File: '" + soundName + ".wav' not found");
			e.printStackTrace();
		}
	}

	public boolean isPlaying() {
		return playing;
	}

	public void play() {
		if (!playing) {
			System.out.println(TimeManager.getCurrentTime() + "... Audio '" + source + "' playing.");
			player.start(stream);
			playing = true;
		}
	}

	public void restart() {
		try {
			System.out.println(TimeManager.getCurrentTime() + "... Audio '" + source + "' restarting...");
			player.stop(stream);
			stream.close();
			stream = new AudioStream(new FileInputStream("./res/sounds/" + source + ".wav"));
			play();
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