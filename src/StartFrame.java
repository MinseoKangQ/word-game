import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

// StartFrame 시작
public class StartFrame extends JFrame{
	
	private PlayerSettingDialog playersettingdialog; // 다이얼로그 레퍼런스
	
	private Clip clip;
	
	// StartFrame 생성자
	public StartFrame() {
		
		setTitle("Typing Game"); // Title 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫히면 프로그램 종료
		Container c = getContentPane();
		
		c.setLayout(new BorderLayout());
		
		c.add(new StartFrameNorthPanel(), BorderLayout.NORTH);
		c.add(new StartFrameCenterPanel(), BorderLayout.CENTER);
		c.add(new StartFrameSouthPanel(), BorderLayout.SOUTH);
		
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
	
	class StartFrameNorthPanel extends JPanel {
		
		public StartFrameNorthPanel() {
			
			setBackground(Color.YELLOW);
			setLayout(new FlowLayout());
			
			ImageIcon mainTextImage = new ImageIcon("spongebobMainText.png"); // 이미지 로딩
			JLabel mainTextImageLabel = new JLabel(mainTextImage);
//			System.out.println(mainTextImageLabel.getPreferredSize());
			
			this.add(mainTextImageLabel); // 패널에 mainTextImageLabel 부착
			
		}
		
	}
	
	class StartFrameCenterPanel extends JPanel {
		
		public StartFrameCenterPanel() {
			
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
