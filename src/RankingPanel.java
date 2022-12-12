import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RankingPanel extends JPanel {

	private final int RECT_WIDTH = 260;
	private final int RECT_HEIGHT = 150;
	
	
	private Font defaultFont = new Font("Jokerman", Font.BOLD, 15); // 기본 폰트 설정
	private LineBorder defaultLineBorder = new LineBorder(Color.WHITE, 5);
	private String difficulty [] = { "Easy", "Normal", "Hard" };
	private JLabel rankingTextLabel = new JLabel(" ★ Ranking ★ ");
	private JLabel backGroundLabel [] = new JLabel[3];
	private JLabel difficultyLabel [] = new JLabel[3];
	private JLabel numberLabel [] = new JLabel[3];
	
	private JLabel easyRankingLabel [] = new JLabel[3];
	
//	private UserRankingManagement userRankingManagement = new UserRankingManagement();
	
	public RankingPanel() {
		
		Color backgroundColor = new Color(184, 221, 207);
		setBackground(backgroundColor);
	
		setLayout(null);
		
		makeBasicLabels();
//		makeEasyRankingLabels();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(157, 203, 189));
		
		for (int i = 0; i<3; i++) {
			g.fillRect(120, 115 + (180*i), RECT_WIDTH, RECT_HEIGHT);
		}

	}
	
	private void makeBasicLabels() {
		
		// Ranking 텍스트
		rankingTextLabel.setFont(new Font("Jokerman", Font.BOLD, 20));
		rankingTextLabel.setForeground(Color.WHITE);
		rankingTextLabel.setSize(220, 45);
		rankingTextLabel.setLocation(140, 35);
		rankingTextLabel.setOpaque(true);
		rankingTextLabel.setBackground(new Color(157, 203, 189));
		rankingTextLabel.setHorizontalAlignment(JLabel.CENTER);
		rankingTextLabel.setBorder(defaultLineBorder);
		this.add(rankingTextLabel);
		
		
		for (int i = 0; i<difficultyLabel.length; i++) {
			difficultyLabel[i] = new JLabel(difficulty[i]);
			difficultyLabel[i].setFont(defaultFont);
			difficultyLabel[i].setForeground(Color.WHITE);
			difficultyLabel[i].setSize(65, 50);
			difficultyLabel[i].setLocation(140, 115 + (180*i));
			difficultyLabel[i].setOpaque(true);
			difficultyLabel[i].setBackground(Color.BLUE);
			this.add(difficultyLabel[i]);
		}
		
		for (int i = 0; i<numberLabel.length; i++) {
			for (int j = 0; j<numberLabel.length; j++) {
			numberLabel[j] = new JLabel(Integer.toString(i+1));
			numberLabel[j].setFont(defaultFont);
			numberLabel[j].setForeground(Color.WHITE);
			numberLabel[j].setSize(15, 50);
			numberLabel[j].setLocation(215, 115 + (50*i) + (180*j));
			numberLabel[j].setOpaque(true);
			numberLabel[j].setBackground(Color.BLUE);
			this.add(numberLabel[j]);
			}
		}
	}
	
	
//	private void makeEasyRankingLabels() {
//		for (int i = 0; i<easyRankingLabel.length; i++) {
//			String name = userRankingManagement.getUser(i).name;
//			easyRankingLabel[i] = new JLabel(name);
//			easyRankingLabel[i].setFont(defaultFont);
//			easyRankingLabel[i].setForeground(Color.WHITE);
//			easyRankingLabel[i].setSize(80, 50);
//			easyRankingLabel[i].setLocation(235, 115 + (50*i));
//			easyRankingLabel[i].setOpaque(true);
//			easyRankingLabel[i].setBackground(Color.BLUE);
//			this.add(easyRankingLabel[i]);
//		}
//	}
	
	
}
