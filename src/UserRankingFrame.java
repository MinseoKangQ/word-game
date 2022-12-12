import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class UserRankingFrame extends JFrame {

	private ProfileAndScorePanel profileAndSocorePanel;
	private PlayResultPanel playResultPanel;
	private RankingPanel rankingPanel;
	
	private Container contentPane;
	
	public UserRankingFrame(ProfileAndScorePanel profileAndSocorePanel) {
		
		this.profileAndSocorePanel = profileAndSocorePanel;
		playResultPanel = new PlayResultPanel(this, profileAndSocorePanel);
		rankingPanel = new RankingPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("게임 결과 확인"); // Title 설정
		setSize(900, 680); // 창 크기
		
		contentPane = getContentPane();
		makeSplitPane();

		setVisible(true); // 창 보이게 
		setResizable(false);

	}
	
	// 스플릿팬 만들기
	private void makeSplitPane() {
			
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 수평으로 분할해 주세요
		hPane.setDividerLocation(400);// 디바이더 초기 위치 설정하기 
		hPane.setDividerSize(0); // 스플릿팬 선 없애기
		contentPane.add(hPane, BorderLayout.CENTER); // ContentPane 불러서 가운데에 붙이기
		
		
		hPane.setLeftComponent(playResultPanel); // playResultPanel을 hPane의 왼쪽에 붙이기
		hPane.setRightComponent(rankingPanel); // vPane을 hPane의 오른쪽에 붙이기
			
		}
}
