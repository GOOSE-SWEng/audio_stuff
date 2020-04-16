package audio_testing;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * The {@code AudioHandler} class provides the ability to store, play and stop audio files.
 * @author Goose Software Design on behalf of CUBIXEL.
 * 
 */
public class AudioHandler {
	private AudioList audios;
	
	public AudioHandler() {
		audios = new AudioList();
	}
	/**
	 * The {@code AudioFile} class is a construct made by the {@code AudioHandler} class to store registered audio files,
	 * as well as generate {@code MediaPlayer} for each file stored.
	 * @author Goose Software Design on behalf of CUBIXEL
	 *
	 */
	private class AudioFile {
		private String id; //the unique identifier assigned to the audio file
		private MediaPlayer player; //the player object for the audio file
		
		private AudioFile(String url, Boolean looping, String id) {
			this.id = id;
			//Generate a player for the Media object generated from the url given by the parameter
			this.player = new MediaPlayer(new Media(new File(url).toURI().toString()));
			
			//If the audio should loop, make the player cycle indefinitely
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
		
		/**
		 * 
		 * @param newFile An AudioFile object to be added to the list. 
		 * @return True if the ID is unique, False if the ID is already in use
		 * and thus the object not added
		 */
		private Boolean addAudioFile(AudioFile newFile) {
		
			for (int i = 0; i < this.size(); i++) {
				if (newFile.getId() == this.get(i).getId()) {
					System.err.print("\nCould not register audio: ID " + newFile.getId() + " is already used.");
					return false;
				}
			}
			
			this.add(newFile);
			return true;
		}
		
		/**
		 * Finds and returns the list entry with a matching ID
		 * 
		 * @param id The ID being searched for in the audio list
		 * @return Returns the AudioFile object with the same ID parameter, or a null pointer if there is
		 * no list entry with that ID
		 */
		private AudioFile getFromId(String id) {
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
			System.err.print("\n Cannot stop audio: " + id + " is not a registered audio ID. (" + e + ")");
		}
	}
	
	public Boolean deregisterAudio(String id) {
		for (int i = 0; i < audios.size(); i++) {
			if (id == audios.get(i).getId()) {
				audios.get(i).player.stop();
				audios.remove(i);
				return true;
			}
		}
		System.err.print("\nCannot deregister audio: " + id + " is not a registered ID");
		return false;
	}
}