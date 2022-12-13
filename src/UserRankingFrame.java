import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class UserRankingFrame extends JFrame {

	// 생성자에게 전달 위한 레퍼런스 선언
	private ProfileAndScorePanel profileAndSocorePanel = null;
	
	// PlayResultPanel와 RankingPanel 사용하기 위해 레퍼런스 변수 선언
	private PlayResultPanel playResultPanel;
	private RankingPanel rankingPanel;
	
	private Container contentPane;
	
	public UserRankingFrame(ProfileAndScorePanel profileAndSocorePanel) {
		
		this.profileAndSocorePanel = profileAndSocorePanel;
		
		playResultPanel = new PlayResultPanel(this, profileAndSocorePanel);
		rankingPanel = new RankingPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("게임 결과 확인");
		setSize(900, 680);
		
		contentPane = getContentPane();
		makeSplitPane();

		setVisible(true);
		setResizable(false);

		
	}
	
	// 스플릿팬 만들기
	private void makeSplitPane() {
			
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 수평으로 분할
		hPane.setDividerLocation(400);// 디바이더 초기 위치 설정
		hPane.setDividerSize(0); // 스플릿팬 선 안보이게
		contentPane.add(hPane, BorderLayout.CENTER); // ContentPane 불러서 가운데에 부착
		
		hPane.setLeftComponent(playResultPanel); // playResultPanel을 hPane 왼쪽에 부착
		hPane.setRightComponent(rankingPanel); // rankingPanel을 hPane 오른쪽에 부착
			
		}
}
