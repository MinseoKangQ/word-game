import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

// StartFrame 시작
public class StartFrame extends JFrame {
	
	private PlayerSettingDialog playersettingdialog = new PlayerSettingDialog(this, "Player Setting"); 	// 모달 다이얼로그 생성
	
	private Clip clip;
	
	// StartFrame 생성자
	public StartFrame() {
		
		setTitle("Typing Game"); // Title 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫히면 프로그램 종료
		
		Container container = getContentPane(); // 컨텐트팬 받아오기
		container.setLayout(new BorderLayout()); // 컨테이너 배치관리자 설정
		
		// 컨테이너에 부착할 패널들 생성
		StartFrameNorthPanel startFrameNorthPanel = new StartFrameNorthPanel();
		StartFrameCenterPanel startFrameCenterPanel = new StartFrameCenterPanel(); 
		StartFrameSouthPanel startFrameSouthPanel = new StartFrameSouthPanel();
		
		// 패널들 컨테이너에 부착
		container.add(startFrameNorthPanel, BorderLayout.NORTH);
		container.add(startFrameCenterPanel, BorderLayout.CENTER);
		container.add(startFrameSouthPanel, BorderLayout.SOUTH);
		
	
		setSize(900, 680); // 창 크기
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
	
	// North에 붙여질 Panel 시작 (텍스트 이미지)
	class StartFrameNorthPanel extends JPanel {
		
		// 생성자
		public StartFrameNorthPanel() {
			
			setLayout(new FlowLayout()); // 배치관리자 설정
			setBackground(Color.YELLOW); // 배경색 설정
			
			ImageIcon mainTextImage = new ImageIcon("spongebobMainText.png"); // 이미지 로딩
			JLabel mainTextImageLabel = new JLabel(mainTextImage); // 레이블 생성
			
			this.add(mainTextImageLabel); // 패널에 레이블 부착
			
		}
	} // North에 붙여질 Panel 끝
	
	// Center에 붙여질 Panel (이미지)
	class StartFrameCenterPanel extends JPanel {
		
		// 생성자
		public StartFrameCenterPanel() {
		
			setLayout(new FlowLayout()); // 배치관리자 설정
			setBackground(Color.YELLOW); // 배경색 설정
			
			
			ImageIcon mainImage = new ImageIcon("spongebobMain.png"); // 이미지 로딩
			JLabel mainImageLabel = new JLabel(mainImage); // 레이블 생성
			
			this.add(mainImageLabel); // 패널에 레이블 부착
		
		}
	} // Center에 붙여질 Panel 끝
	
	// South에 붙여질 Panel (버튼)
	class StartFrameSouthPanel extends JPanel {
		
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
			
			// 버튼에 KeyListener 부착
			startButton.addKeyListener(new KeyAdapter() {
				 
				@Override
				public void keyPressed(KeyEvent e) {
					
					if (e.getKeyChar() == '\n') // 엔터를 받으면 
						playersettingdialog.setVisible(true); // 모달 다이얼로그 실행
					
				}
			});
			
			// 버튼에 ActionListener 부착
			startButton.addActionListener(new ActionListener() { // Action 리스너 작성
				
				@Override
				public void actionPerformed(ActionEvent e) { // 버튼이 눌러지면
					playersettingdialog.setVisible(true); // 모달 다이얼로그 실행
				}
				
			});
			
			this.add(startButton); // 패널에 startButton 부착
			
		} // South 생성자 끝
	} // South에 붙여질 Panel 끝		
} // StartFrame 끝
