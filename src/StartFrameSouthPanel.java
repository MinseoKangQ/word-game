import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StartFrameSouthPanel extends JPanel {
	
	
	// 생성자에게 전달 위한 레퍼런스 선언
	private StartFrame startFrame;
	private Audio audio;
	
	// playersettingdialog 실행을 위한 레퍼런스 변수 선언
	private PlayerSettingDialog playersettingdialog;
	
	JButton startButton = new JButton("Enter To Start"); // 버튼 생성
	
	// 생성자
	public StartFrameSouthPanel(StartFrame startFrame, Audio audio) {
	
		this.startFrame = startFrame;
		this.audio = audio;
		
		setLayout(new FlowLayout());
		setBackground(Color.YELLOW);
		
		makeStartButton();
	
	} // 생성자 끝
	
	private void makeStartButton() {
		
		startButton.setPreferredSize(new Dimension(190, 50));
		startButton.setFont(new Font("Jokerman", Font.BOLD, 15));
			
		// 포커스 강제 설정
		startButton.setFocusable(true);
		startButton.requestFocus();
			
		startButton.addKeyListener(new KeyPressedListener());
		add(startButton);
		
	}
	
	private class KeyPressedListener extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
					
			if (e.getKeyChar() == '\n') { // 엔터를 받으면 
				
				playersettingdialog = new PlayerSettingDialog(startFrame, audio); // 모달 다이얼로그 생성
				playersettingdialog.setVisible(true); // 모달 다이얼로그 보이게


			}	
		}
	}
	
	
}