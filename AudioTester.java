package audio_testing;

import javafx.application.Application;
import javafx.stage.Stage;

public class AudioTester extends Application{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		AudioHandler audioTester = new AudioHandler();
		audioTester.registerAudio("C:\\Users\\alex.THEKNELLERS\\Documents\\Multitrack Things\\please hold on.mp3", true, "abba");
		audioTester.registerAudio("C:\\Users\\alex.THEKNELLERS\\Documents\\Multitrack Things\\Apple Blossom Time 2nd Edit.wav",false,"blossom");
		audioTester.registerAudio("C:\\Users\\alex.THEKNELLERS\\Documents\\Multitrack Things\\Apple Blossom Time 2nd Edit.wav",false,"blossom");
		audioTester.startAudio("abba");
		audioTester.startAudio("blossom");
		System.out.print("Returning");
		Thread.sleep(4000);
		audioTester.stopAudio("blossom");
		audioTester.stopAudio("blossom");
		audioTester.startAudio("abba");
		Thread.sleep(4000);
		audioTester.startAudio("bla");
		audioTester.startAudio("blossom");
		audioTester.deregisterAudio("bla");
		while (true);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
