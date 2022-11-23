import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

// South에 붙여질 Panel
public class StartFrameSouthPanel extends JPanel {
		
	private PlayerSettingDialog playersettingdialog = new PlayerSettingDialog(); // 모달 다이얼로그 생성
	
	// 생성자
	public StartFrameSouthPanel() {
			
		setLayout(new FlowLayout()); // 배치관리자 설정
		setBackground(Color.YELLOW); // 배경색 설정
			
		JButton startButton = new JButton("Enter To Start"); // 버튼 생성
		
		startButton.setPreferredSize(new Dimension(190, 50)); // 버튼 크기 조절
		startButton.setFont(new Font("Jokerman", Font.BOLD, 15)); // 버튼 글씨체 설정
			
		// 포커스 강제 설정
		startButton.setFocusable(true);
		startButton.requestFocus();
			
		startButton.addKeyListener(new KeyPressedListener()); // 버튼에 KeyListener 부착
		startButton.addMouseListener(new MouseClickedListener()); // 버튼에 MouseListener 부착
			
		this.add(startButton); // 패널에 버튼 부착
			
	} // 생성자 끝
	
	// 버튼에 부착될 KeyPressedListener 작성
	private class KeyPressedListener extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
					
			if (e.getKeyChar() == '\n') { // 엔터를 받으면 
				
				playersettingdialog.setVisible(true); // 모달 다이얼로그 실행
				
			}	
		}
	}
	
	// 버튼에 부착될 MouseClickedListener 작성
	private class MouseClickedListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) { // 버튼이 클릭되면
		
			playersettingdialog.setVisible(true); // 모달 다이얼로그 실행
		
		}
	}
	
}