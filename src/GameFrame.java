import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class GameFrame extends JFrame {

	private Container contentPane;
	
	public Audio audio = new Audio();
	
	private WordList wordList = new WordList();
	
	// 패널 부착을 위한 패널 생성
	private ProfileAndScorePanel profileAndScorePanel = new ProfileAndScorePanel();   
	private GamePanel gamePanel = new GamePanel(this, wordList, profileAndScorePanel, audio);
	private SettingPanel settingPanel = new SettingPanel(audio, gamePanel);
	
	// 생성자
	public GameFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("단어 게임"); 
		setSize(900, 680); 
		
		contentPane = getContentPane();
	
		makeSplitPane();
		
		setVisible(true);  
		setResizable(false);
		
		gamePanel.startCountDown(); // 3초 카운트 다운 시작
		audio.playAudio("beforeGameStart"); // 카운트 다운 음악 시작
		
	}
	
	// 스플릿팬 만들기 (영역 만들고 패널 부착)
	private void makeSplitPane() {
		
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 수평으로 분할
		hPane.setDividerLocation(600); // 디바이더 초기 위치 설정
		hPane.setDividerSize(0); // 스플릿팬 선 안보이게
		contentPane.add(hPane, BorderLayout.CENTER); // ContentPane 불러서 가운데에 부착

		JSplitPane vPane = new JSplitPane();
		vPane.setOrientation(JSplitPane.VERTICAL_SPLIT); // 수직으로 분할
		vPane.setDividerLocation(400); // 디바이더 초기 위치 설정
		vPane.setDividerSize(0); // 스플릿팬 선 안보이게
		hPane.setRightComponent(vPane); // vPane을 hPane 오른쪽에 부착
		hPane.setLeftComponent(gamePanel); // gamePanel을 hPane 왼쪽에 부착
		vPane.setTopComponent(profileAndScorePanel); // profileAndScorePanel을 vPane 위쪽에 부착
		vPane.setBottomComponent(settingPanel); // settingPanel을 vPane 아래쪽에 부착
		
	}
	
}

