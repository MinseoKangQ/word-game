import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

// StartFrame 시작
public class StartFrame extends JFrame {
	
	private StartFrameNorthPanel startFrameNorthPanel;
	private StartFrameCenterPanel startFrameCenterPanel;
	private StartFrameSouthPanel startFrameSouthPanel;
	private Container contentPane;
	private Clip clip;
	
	// StartFrame 생성자
	public StartFrame() {
		
		setTitle("Typing Game"); // Title 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫히면 프로그램 종료
		
		contentPane = getContentPane(); // 컨텐트팬 받아오기
		contentPane.setLayout(new BorderLayout()); // 컨테이너 배치관리자 설정
		
		makePanels();  // 컨테이너에 부착할 패널들 생성
		addPanels(); // 패널들 컨테이너에 부착
	
		setSize(900, 680); // 창 크기 설정
		setVisible(true); // 창 보이게
		setResizable(false); // 창 크기 변경 불가능하게	
		
		
		// **오디오 => Thread로 만들자
		try {
			clip = AudioSystem.getClip();
			File audioFile = new File("mainStartAudio.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream);
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		
		clip.start();
		
	} // StartFrame 생성자 끝
	
	
	private void makePanels() {
		
		startFrameNorthPanel = new StartFrameNorthPanel();
		startFrameCenterPanel = new StartFrameCenterPanel(); 
		startFrameSouthPanel = new StartFrameSouthPanel();
		
	}
	
	private void addPanels() {
		
		getContentPane().add(startFrameNorthPanel, BorderLayout.NORTH);
		getContentPane().add(startFrameCenterPanel, BorderLayout.CENTER);
		getContentPane().add(startFrameSouthPanel, BorderLayout.SOUTH);
		
	}
	
	
	
} // StartFrame 끝
