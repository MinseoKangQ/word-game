import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private StartFrame startFrame = new StartFrame();
	
	public GameFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("단어 게임"); // Title 설정
		setSize(800, 600); // 창 크기
		

		
		setSize(900, 680); // 창 크기
		setVisible(true); // 창 보이게
		setResizable(false); // 창 크기 변경 불가능하게	
	}
	
}
