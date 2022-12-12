import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class GameFrame extends JFrame {

	private Container contentPane;
	public Audio audio = new Audio();
	
	private WordList wordList = new WordList(); // 단어 생성
	
	private ProfileAndScorePanel profileAndScorePanel = new ProfileAndScorePanel();   
	private GamePanel gamePanel = new GamePanel(this, wordList, profileAndScorePanel, audio); // GamePanel에게 매개변수 전달
	private SettingPanel settingPanel = new SettingPanel(audio, gamePanel);
	
	// 생성자
	public GameFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("단어 게임"); // Title 설정
		setSize(900, 680); // 창 크기
		
		contentPane = getContentPane();
	
		makeSplitPane();
		
		setVisible(true); // 창 보이게 
		setResizable(false);
		
		gamePanel.startCountDown();
		audio.playAudio("beforeGameStart");
	}
	
	// 스플릿팬 만들기
	private void makeSplitPane() {
		
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 수평으로 분할해 주세요
		hPane.setDividerLocation(600);// 디바이더 초기 위치 설정하기 
		hPane.setDividerSize(0); // 스플릿팬 선 없애기
		contentPane.add(hPane, BorderLayout.CENTER); // ContentPane 불러서 가운데에 붙이기

		JSplitPane vPane = new JSplitPane();
		vPane.setOrientation(JSplitPane.VERTICAL_SPLIT); // 수직으로 분할해 주세요
		vPane.setDividerLocation(400);
		vPane.setDividerSize(0); // 스플릿팬 선 없애기
		hPane.setRightComponent(vPane); // vPane을 hPane의 오른쪽에 붙이기
		hPane.setLeftComponent(gamePanel); // gamePanel을 hPane의 왼쪽에 붙이기 
		vPane.setTopComponent(profileAndScorePanel);
		vPane.setBottomComponent(settingPanel); // editPanel을 vPane 아래쪽에 붙이기 
		
	}
	
}

