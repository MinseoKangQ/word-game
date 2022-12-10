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
	public Audio audio = new Audio();

	private static boolean isFirstExecute = true; // 처음 실행되는 것인지
	
	// StartFrame 생성자
	public StartFrame() {
		
		if (isFirstExecute) { // 처음 실행 된다면 StartFrame 출력
			
			setTitle("Typing Game"); // Title 설정
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫히면 프로그램 종료
		
			contentPane = getContentPane(); // 컨텐트팬 받아오기
			contentPane.setLayout(new BorderLayout()); // 컨테이너 배치관리자 설정
		
			makePanels();  // 컨테이너에 부착할 패널들 생성
			addPanels(); // 패널들 컨테이너에 부착
	
			setSize(900, 680); // 창 크기 설정
			setVisible(true); // 창 보이게
			setResizable(false); // 창 크기 변경 불가능하게
		
			audio.playAudio("startFrame");
		
			isFirstExecute = false;
			
		}
		
	} // StartFrame 생성자 끝
	
	
	private void makePanels() {
		
		startFrameNorthPanel = new StartFrameNorthPanel();
		startFrameCenterPanel = new StartFrameCenterPanel(); 
		startFrameSouthPanel = new StartFrameSouthPanel(this, audio);
		
	}
	
	private void addPanels() {
		
		getContentPane().add(startFrameNorthPanel, BorderLayout.NORTH);
		getContentPane().add(startFrameCenterPanel, BorderLayout.CENTER);
		getContentPane().add(startFrameSouthPanel, BorderLayout.SOUTH);
		
	}
	
	
	
} // StartFrame 끝
