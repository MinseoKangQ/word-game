import java.awt.*;
import javax.swing.JFrame;

public class StartFrame extends JFrame {
	
	// 생성자에게 전달 위한 레퍼런스 선언
	private StartFrameNorthPanel startFrameNorthPanel = null;
	private StartFrameCenterPanel startFrameCenterPanel = null;
	private StartFrameSouthPanel startFrameSouthPanel = null;
	
	private Container contentPane;
	public Audio audio = new Audio(); 

	// 처음 실행되는 것인지 확인하는 변수
	private static boolean isFirstExecute = true; 
	
	// 생성자
	public StartFrame() {
		
		if (isFirstExecute) { // 처음 실행 된다면 StartFrame 출력
			
			setTitle("Typing Game"); // Title 설정
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫히면 프로그램 종료
		
			contentPane = getContentPane();
			contentPane.setLayout(new BorderLayout());
		
			makePanels(); // 패널들 부착
	
			setSize(900, 680); 
			setVisible(true); 
			setResizable(false);
		
			audio.playAudio("startFrame");
		
			isFirstExecute = false;
			
		}
		
	} 
	
	private void makePanels() {
		
		startFrameNorthPanel = new StartFrameNorthPanel();
		startFrameCenterPanel = new StartFrameCenterPanel(); 
		startFrameSouthPanel = new StartFrameSouthPanel(this, audio);
		
		contentPane.add(startFrameNorthPanel, BorderLayout.NORTH);
		contentPane.add(startFrameCenterPanel, BorderLayout.CENTER);
		contentPane.add(startFrameSouthPanel, BorderLayout.SOUTH);
		
	}
	
}
