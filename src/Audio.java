import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	private Clip startFrameClip;
	private Clip beforeGameStartClip;
	private Clip gameBackgroundClip;
	
	public Audio() {
		startFrameClip = getClip("mainStartAudio.wav");
		beforeGameStartClip = getClip("beforeGameStartAudio.wav");
		gameBackgroundClip = getClip("gameBackgroundAudio.wav");
	}
	
	// 오디오 클립 가져오기
	private Clip getClip(String filePath) {
		
		Clip clip = null;
		
		try {
			clip = AudioSystem.getClip();
			File audioFile = new File(filePath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream);
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		
		return clip;
	}
	
	// 오디오 재생
	public void playAudio(String name) {
		switch(name) {
		case "startFrame":
			startFrameClip.start();
			break;
		case "beforeGameStart":
			beforeGameStartClip.start();
			break;
		case "gameBackground":
			gameBackgroundClip.start();
			break;
		}
	}
	
	// 오디오 정지
	public void stopAudio(String name) {
		switch(name) {
		case "startFrame":
			startFrameClip.stop();
			break;
		case "beforeGameStart":
			beforeGameStartClip.stop();
			break;
		case "gameBackground":
			gameBackgroundClip.stop();
			break;
		}
	}
	
	public void closeAudio(String name) {
		switch(name) {
		case "startFrame":
			startFrameClip.close();
			break;
		case "beforeGameStart":
			beforeGameStartClip.close();
			break;
		case "gameBackground":
			gameBackgroundClip.close();
			break;
		}
	}
	
	
}
