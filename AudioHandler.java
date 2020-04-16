package audio_testing;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioHandler {
	private AudioList audios = new AudioList();
	
	public class AudioFile {
		private String id;
		private MediaPlayer player;
		
		public AudioFile(String url, Boolean looping, String id) {
			this.id = id;
			this.player = new MediaPlayer(new Media(new File(url).toURI().toString()));
			if (looping == true) {
				this.player.setCycleCount(MediaPlayer.INDEFINITE);
			} else {
				this.player.setCycleCount(1);
			}
			
		}
		
		public String getId() {
			return id;
		}
	}
	
	private class AudioList extends ArrayList<AudioFile> {
		
		AudioList() {
			super();
		}
		public Boolean addAudioFile(AudioFile newFile) {
			for (int i = 0; i < this.size(); i++) {
				if (newFile.getId() == this.get(i).getId()) {
					return false;
				}
			}
			
			this.add(newFile);
			return true;
		}
		
		public AudioFile getFromId(String id) {
			for (int i = 0; i < this.size(); i++) {
				if (id == this.get(i).getId()) {
					return this.get(i);
				}
			}
			System.err.print("\nWarning: Requested AudioFile ID not found. Expect a NullPointerException");
			return null;
		}
	}
	public Boolean registerAudio(String url, Boolean looping, String id) {
		return audios.addAudioFile(new AudioFile(url, looping, id));
	}
	
	public void startAudio(String id) throws NullPointerException {
		try {
			audios.getFromId(id).player.play();
		} catch (NullPointerException e) {
			System.err.print("\nCannot play audio: "+ id +" is not a registered audio ID. (" + e + ")");
		}
	}
	
	public void stopAudio(String id) {
		try {
			audios.getFromId(id).player.stop();
		} catch (NullPointerException e) {
			
		}
	}
	
	public void deregisterAudio(String id) {
		for (int i = 0; i < audios.size(); i++) {
			if (id == audios.get(i).getId()) {
				audios.get(i).player.stop();
				audios.remove(i);
			}
		}
	}
}