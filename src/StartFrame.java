import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

// StartFrame 시작
public class StartFrame extends JFrame{
	
	private PlayerSettingDialog playersettingdialog; // 다이얼로그 레퍼런스
	
//	private ImageIcon normalIcon = new ImageIcon("PlayButton.png");
//	private ImageIcon overedIcon = new ImageIcon("PlayButton.png");
//	private ImageIcon pressedIcon = new ImageIcon("PlayButton.png");
//	private JButton startBtn = new JButton(normalIcon);
	
	
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
		
		// startFrameCenterPanel에 부착할 패널들 생성 
		StartFrameCenterNorthPanel startFrameCenterNorthPanel = new StartFrameCenterNorthPanel();
		StartFrameCenterCenterPanel startFrameCenterCenterPanel = new StartFrameCenterCenterPanel();
		
		// 패널들 startFrameCenterPanel에 부착
		startFrameCenterPanel.add(startFrameCenterNorthPanel, BorderLayout.NORTH);
		startFrameCenterPanel.add(startFrameCenterCenterPanel, BorderLayout.CENTER);
		
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
		
		// 모달 다이얼로그 생성
		playersettingdialog = new PlayerSettingDialog(this, "Player Setting");

	}
	
	// 새로 만들었음
	class StartFrameNorthPanel extends JPanel {
		
		public StartFrameNorthPanel() {
			
			// 툴바 만들기
			JToolBar toolBar = new JToolBar();
			
			toolBar.add(new JButton("NEW"));
			toolBar.addSeparator();
			this.add(toolBar, new FlowLayout());
			
			// ** 툴바 레이아웃 null?
			// ** PlayButton, StopButton, ResetButton 붙이기
			// **
		
			toolBar.setFloatable(false); // 툴바 이동 못하게 설정
			// ** 시작하면 포커스 ??
			//toolBar.setFocusable(false); -> ?
//			
			// 액션 리스너 등록 
//			startBtn.addActionListener(new ActionListener() {
//				
//				public void actionPerformed(ActionEvent e) {
//					
//					// 소리 세팅
//				}
//			});
//			
			// 툴바에 버튼 달기
//			tBar.add(startBtn);
			
			
		}
		
	}
	
	class StartFrameCenterPanel extends JPanel {
		
		public StartFrameCenterPanel() {
			
			setLayout(new BorderLayout());
			
		}
	}
	// 수정됨
	class StartFrameCenterNorthPanel extends JPanel {
		
		public StartFrameCenterNorthPanel() {
			
			setBackground(Color.YELLOW);
			setLayout(new FlowLayout());
			
			ImageIcon mainTextImage = new ImageIcon("spongebobMainText.png"); // 이미지 로딩
			JLabel mainTextImageLabel = new JLabel(mainTextImage);
			
			this.add(mainTextImageLabel); // 패널에 mainTextImageLabel 부착
			
		}
		
	}
	
	// 수정됨
	class StartFrameCenterCenterPanel extends JPanel {
		
		public StartFrameCenterCenterPanel() {
			
			setBackground(Color.YELLOW);
			setLayout(new FlowLayout());
			
			ImageIcon mainImage = new ImageIcon("spongebobMain.png"); // 이미지 로딩
			JLabel mainImageLabel = new JLabel(mainImage);
			
			this.add(mainImageLabel); // 패널에 mainImageLabel 부착
		
		}
	}
	
	class StartFrameSouthPanel extends JPanel {
		
		public StartFrameSouthPanel() {
			
			setBackground(Color.YELLOW);
			setLayout(new FlowLayout());
			
			JButton startButton = new JButton("Press Enter To Start");
			startButton.setPreferredSize(new Dimension(190, 50)); // 버튼 크기 조절
			startButton.setFont(new Font("Jokerman", Font.BOLD, 15));
			
			startButton.setFocusable(true);
			startButton.requestFocus();
			
			startButton.addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyPressed(KeyEvent e) {
					
					if (e.getKeyChar() == '\n')
						playersettingdialog.setVisible(true); // 모달 다이얼로그 실행
				}
			});
			
			startButton.addActionListener(new ActionListener() { // Action 리스너 작성
				
				@Override
				public void actionPerformed(ActionEvent e) { // 버튼이 눌러지면
//					JButton b = (JButton)e.getSource();
//					System.out.println("Clicked!!"); // 플레이어 설정 다이얼로그 출력
					
					playersettingdialog.setVisible(true); // 모달 다이얼로그 실행
				}
				
			});
			
			this.add(startButton); // 패널에 startButton 부착
			
		}
	}

			
}
		
// StartFrame 끝
