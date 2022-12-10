import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	private Clip startFrameClip;
	
	public Audio() {
		startFrameClip = getClip("mainStartAudio.wav");
	}
	
	// 음악 파일과 오디오 클립 연결
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
		}
	}
	
	// 오디오 정지
	public void stopAudio(String name) {
		switch(name) {
		case "startFrame":
			startFrameClip.close();
			break;
		}
	}
	
}
