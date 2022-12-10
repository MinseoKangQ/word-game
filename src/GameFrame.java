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
		
		System.out.println("--GameFrame에서 출력된 것임--");
		// 플레이어 이름 출력
		System.out.println("Player Name : " + PlayerSettingDialog.name);
		// 프로필 선택한 것 출력
		System.out.println("Choosed Profile : " + PlayerSettingDialog.profile);
		// 난이도 선택한 것 출력
		System.out.println("난이도 : " + PlayerSettingDialog.difficulty);
		// 파일 이름 출력 
		System.out.println("File : " + PlayerSettingDialog.fileName);
		
		
	}
	
}
