package sound;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import sun.audio.AudioStream;

import static sun.audio.AudioPlayer.player;

public class SoundPlayer {
	private static Map<String, AudioStream> loadedSounds = new HashMap<String, AudioStream>();
	private AudioStream stream;
	private String source;
	private boolean playing;

	public SoundPlayer(String soundName) {
		stream = loadedSounds.get(soundName);
		source = soundName;
		if (stream == null) {
			try {
				stream = new AudioStream(new FileInputStream("./res/sounds/" + soundName + ".wav"));
				loadedSounds.put(soundName, stream);
				System.out.println(soundName + ".wav ... Sound loaded");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			printInfo();
		}
	}

	public boolean isPlaying() {
		return playing;
	}

	public void play() {
		player.start(stream);
		playing = true;
	}

	public void pause() {
		if (playing) {
			player.stop(stream);
			playing = false;
		}
	}

	public void stop() {
		try {
			stream.close();
			loadedSounds.remove(source);
		} catch (IOException e) {
			e.printStackTrace();
		}
		printInfo();
	}

	public void printInfo() {

		for (Entry<String, AudioStream> entry : loadedSounds.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}

	}

}
