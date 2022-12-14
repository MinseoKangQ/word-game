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
	private JLabel difficultyLabel [] = new JLabel[3];
	private JLabel numberLabel [] = new JLabel[3];
	private JLabel nameScoreLabel = null;
	
	// 생성자
	public RankingPanel() {
		
		Color backgroundColor = new Color(184, 221, 207);
		setBackground(backgroundColor);
	
		setLayout(null);
		
		makeBasicLabels();
		addNameScore();
		
	}
	
	// 사각형 그리는 메소드
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
		add(rankingTextLabel);
		
		// 난이도 텍스트
		for (int i = 0; i<difficultyLabel.length; i++) {
			difficultyLabel[i] = new JLabel(difficulty[i]);
			difficultyLabel[i].setFont(defaultFont);
			difficultyLabel[i].setForeground(Color.WHITE);
			difficultyLabel[i].setSize(65, 50);
			difficultyLabel[i].setLocation(140, 115 + (180*i));
			add(difficultyLabel[i]);
		}
		
		// 숫자 텍스트
		for (int i = 0; i<numberLabel.length; i++) {
			for (int j = 0; j<numberLabel.length; j++) {
			numberLabel[j] = new JLabel(Integer.toString(i+1));
			numberLabel[j].setFont(defaultFont);
			numberLabel[j].setForeground(Color.WHITE);
			numberLabel[j].setSize(15, 50);
			numberLabel[j].setLocation(215, 115 + (50*i) + (180*j));
			add(numberLabel[j]);
			}
		}
	}
	
	private void addNameScore() {
		
		nameScoreLabel = new JLabel(GameManagement.name + " | " + GameManagement.score);
		nameScoreLabel.setFont(defaultFont);
		nameScoreLabel.setForeground(Color.WHITE);
		nameScoreLabel.setSize(100, 50);
		
		if (GameManagement.difficulty.equals("Easy"))
			nameScoreLabel.setLocation(240, 115); // 140, 115 + (180*i)
		else if (GameManagement.difficulty.equals("Normal"))
			nameScoreLabel.setLocation(240, 295);
		else if (GameManagement.difficulty.equals("Normal"))
			nameScoreLabel.setLocation(240, 475);
		
		add(nameScoreLabel);
		
		}
	
}
