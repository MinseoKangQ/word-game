import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ProfileAndScorePanel extends JPanel {

	private Font defaultFont = new Font("Jokerman", Font.BOLD, 15); // 기본 폰트 설정
	
	private String name = GameManagement.name;
	private String profile = GameManagement.profile;

	public static int score = 0;
	
	private JLabel nameLabel = new JLabel(name);
	private JLabel scoreLabel = new JLabel(Integer.toString(score)); // int형 score를 문자열로
	
	private ImageIcon profileImage;
	private JLabel profileLabel;
	
	private ImageIcon normalProfileImage;
	private JLabel normalProfileLabel;
	
	private ImageIcon sadProfileImage;
	private JLabel sadProfileLabel;
	
	// 생성자
	public ProfileAndScorePanel() {
		
		setOpaque(true); 
		
		Color backgroundColor = new Color(168, 200, 236);
		setBackground(backgroundColor);
		
		setLayout(null);
		
		makeNameLabel();
		makeProfileLabel();
		makeScoreLabel();
		
	}
	
	public void makeNameLabel() {
		
		nameLabel.setFont(defaultFont);
		nameLabel.setSize(100, 30);
		nameLabel.setLocation(100,30);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setOpaque(true);
		nameLabel.setBackground(Color.WHITE);
		add(nameLabel);
		
	}
	
	public void makeProfileLabel() {
		
		if(GameManagement.profile.equals("SpongebobSquarepants")) {
			profileImage = new ImageIcon("SpongebobNormal.png");
			this.normalProfileImage = new ImageIcon("SpongebobNormal.png");
			this.sadProfileImage = new ImageIcon("SpongebobSad.png");
    		profileLabel = new JLabel(profileImage);
    		profileLabel.setSize(150, 150);
    		profileLabel.setLocation(75 ,95);
    		profileLabel.setBorder(new LineBorder(Color.WHITE, 3));
    		add(profileLabel);
		}
		else if(GameManagement.profile.equals("PatrickStar")) {
			profileImage = new ImageIcon("PatrickStarNormal.png");
			this.normalProfileImage = new ImageIcon("PatrickStarNormal.png");
			this.sadProfileImage = new ImageIcon("PatrickStarSad.png");
    		profileLabel = new JLabel(profileImage);
    		profileLabel.setSize(150, 150);
    		profileLabel.setLocation(75 ,95);
    		profileLabel.setBorder(new LineBorder(Color.WHITE, 3));
    		add(profileLabel);
		}
		
	}
	
	public void makeScoreLabel() {
		
		JLabel scoreLabelText = new JLabel("Score");
		scoreLabelText.setFont(defaultFont);
		scoreLabelText.setSize(100, 30);
		scoreLabelText.setLocation(100 ,290);
		scoreLabelText.setHorizontalAlignment(JLabel.CENTER);
		scoreLabelText.setOpaque(true);
		scoreLabelText.setBackground(Color.WHITE);
		add(scoreLabelText);
		
		scoreLabel.setFont(defaultFont);
		scoreLabel.setSize(100, 30);
		scoreLabel.setLocation(100 ,320);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setOpaque(true);
		scoreLabel.setBackground(Color.WHITE);
		add(scoreLabel);
		
	}

	public void setNormalProfileImage() {
		
		profileLabel.setIcon(normalProfileImage);
		
	}
	
	public void setSadProfileImage() { 
    
    	profileLabel.setIcon(sadProfileImage);
    	
    }
	
	
	// 점수 올리는 메소드
	public void increase() { 
		
		if (GameManagement.difficulty.equals("Normal")) {
			score += 15;
			scoreLabel.setText(Integer.toString(score)); 
		}
		
		else if (GameManagement.difficulty.equals("Hard")) {
			score += 20;
			scoreLabel.setText(Integer.toString(score)); 
		}
		
		else {
			score += 10;
			scoreLabel.setText(Integer.toString(score));
		}
	}
	
	public void decrease() { 
		
		if (GameManagement.difficulty.equals("Normal")) {
			score -= 15;
			scoreLabel.setText(Integer.toString(score)); 
		}
		
		else if (GameManagement.difficulty.equals("Hard")) {
			score -= 20;
			scoreLabel.setText(Integer.toString(score)); 
		}
		
		else {
			score -= 10;
			scoreLabel.setText(Integer.toString(score));
		}
		
	}
	
	public int getScore() { return this.score; }
}
