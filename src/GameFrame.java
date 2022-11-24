import javax.swing.JFrame;

//import java.awt.Color;
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JPanel;
//import javax.swing.JToolBar;
//
////import javax.swing.ImageIcon;
////import javax.swing.JButton;
////
////private ImageIcon normalPlayIcon = new ImageIcon("PlayButton.png");
////	private ImageIcon normalResetIcon = new ImageIcon("ResetButton.png");
////	private ImageIcon normalStopIcon = new ImageIcon("StopButton.png");
////	
//////	private ImageIcon overedIcon = new ImageIcon("PlayButton.png");
//////	private ImageIcon pressedIcon = new ImageIcon("PlayButton.png");
////	private JButton startBtn = new JButton(normalPlayIcon);
////	private JButton resetBtn = new JButton(normalResetIcon);
////	private JButton stopBtn = new JButton(normalStopIcon);
//
//
//class StartFrameNorthPanel extends JPanel {
//		
//		public StartFrameNorthPanel() {
//			
//			this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
////			this.setBackground(new Color(0, 189, 203)); // 패널의 Background
//			this.setBackground(Color.ORANGE); // 패널의 Background
//			// 툴바 만들기
//			JToolBar toolBar = new JToolBar();
//			
//			
//			// 시작 버튼의 이미지 설정
////			startBtn.setPressedIcon(pressedIcon);
////			startBtn.setRolloverIcon(overedIcon);
//			
//			// 액션 리스너 등록 
//			startBtn.addActionListener(new ActionListener() {
//				
//				public void actionPerformed(ActionEvent e) {
//					
//					System.out.println("눌림");
//				}
//			});
//			
////			toolBar.setBackground(new Color(0, 189, 203));
//			toolBar.setBackground(Color.ORANGE);
//			startBtn.setBackground(Color.WHITE);
//			resetBtn.setBackground(Color.WHITE);
//			stopBtn.setBackground(Color.WHITE);
//			
//			startBtn.setOpaque(true);
//			resetBtn.setOpaque(true);
//			stopBtn.setOpaque(true);
//			
//			startBtn.setBorderPainted(false);
//			resetBtn.setBorderPainted(false);
//			stopBtn.setBorderPainted(false);
//			
//			// 툴바에 버튼 달기
//			toolBar.add(startBtn);
//			toolBar.addSeparator();
//			toolBar.add(resetBtn);
//			toolBar.addSeparator();
//			toolBar.add(stopBtn);
//
//			startBtn.setFocusable(false);
//			resetBtn.setFocusable(false);
//			stopBtn.setFocusable(false);
//			// 툴바를 패널에 달기
//			this.add(toolBar);
//			
//			// ** PlayButton, StopButton, ResetButton 붙이기
//			// ** 
//		
//			toolBar.setFloatable(false); // 툴바 이동 못하게 설정
//			// ** 시작하면 포커스 ?		
//			
//			
//		}
//		
//	}
public class GameFrame extends JFrame {

	public GameFrame() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("단어 게임"); // Title 설정
		setSize(800, 600); // 창 크기
		

		
		setSize(900, 680); // 창 크기
		setVisible(true); // 창 보이게
		setResizable(false); // 창 크기 변경 불가능하게	
	}
	
}
