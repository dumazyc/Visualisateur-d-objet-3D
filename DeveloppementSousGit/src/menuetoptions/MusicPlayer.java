package menuetoptions;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * Cette classe permet de lire des fichiers .wav tout en continuant a executer
 * le programme.
 */
public class MusicPlayer {
	private PlayThread t;
	private AudioFormat audioFormat;
	private AudioInputStream audioInputStream;
	private SourceDataLine sourceDataLine;
	private boolean stopPlayback = false;
	private String musicFile;

	/**
	 * Constructeur de la classe MusicPlayer
	 * 
	 * @param musicFile
	 *            addresse du fichier .wav
	 */
	public MusicPlayer(String musicFile) {
		this.musicFile = musicFile;
	}

	/**
	 * Permet de lancer la lecture du fichier. Permet aussi de reprendre la
	 * lecture en cas de pause.
	 */
	@SuppressWarnings("deprecation")
	public void lecture() {
		if (t == null) {

			try {
				File soundFile = new File(musicFile);
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
				audioFormat = audioInputStream.getFormat();

				DataLine.Info dataLineInfo = new DataLine.Info(
						SourceDataLine.class, audioFormat);
				sourceDataLine = (SourceDataLine) AudioSystem
						.getLine(dataLineInfo);
				t = new PlayThread();
				t.start();
			} catch (Exception e) {

			}
		} else {
			t.resume();
		}
	}

	/**
	 * Permet de mettre en pause la lecture.
	 */
	@SuppressWarnings("deprecation")
	public void pause() {
		if (t != null) {
			t.suspend();
		}
	}

	/**
	 * Permet d'arreter la lecture. Attention, pas de possibilite de reprise.
	 */
	public void stop() {
		stopPlayback = true;
	}

	/**
	 * Cette classe permet la lecture d'un fichier .wav en tache de fond (sans
	 * gener le programme principal).
	 */
	private class PlayThread extends Thread {
		byte tempBuffer[] = new byte[10000];

		public void run() {
			try {
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();
				int cnt;

				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1 && stopPlayback == false) {
					if (cnt > 0) {
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
			} catch (Exception e) {

			} finally {
				try {
					sourceDataLine.drain();
					sourceDataLine.close();
					stopPlayback = false;
					t = null;

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		}
	}
}
