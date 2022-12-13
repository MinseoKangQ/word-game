import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ProfileAndScorePanel extends JPanel {

	private Font defaultFont = new Font("Jokerman", Font.BOLD, 15); 

	public static int score = 0;
	
	private JLabel nameLabel = new JLabel(GameManagement.name);
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	
	private ImageIcon profileImage;
	private JLabel profileLabel;
	
	private ImageIcon normalProfileImage;
	private ImageIcon sadProfileImage;
	
	// 생성자
	public ProfileAndScorePanel() {
		
		setOpaque(true); 
		Color backgroundColor = new Color(168, 200, 236);
		setBackground(backgroundColor);
		
		setLayout(null);
		
		makeNameLabel(); // 이름 레이블 부착
		makeProfileLabel(); // 프로필 레이블 부착
		makeScoreLabel(); // 점수 레이블 부착
		
	}
	
	public void makeNameLabel() {
		
		nameLabel.setFont(defaultFont);
		nameLabel.setSize(100, 30);
		nameLabel.setLocation(100,30);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setOpaque(true);
		nameLabel.setBackground(Color.WHITE);
		this.add(nameLabel);
		
	}
	
	public void makeProfileLabel() {
		
		// 프로필에 따라 나타나는 이미지가 다르다
		if(GameManagement.profile.equals("SpongebobSquarepants")) {
			profileImage = new ImageIcon("image/expression/SpongebobNormal.png");
//	 원래		this.normalProfileImage = new ImageIcon("image/expression/SpongebobNormal.png");
			this.normalProfileImage = profileImage;
			this.sadProfileImage = new ImageIcon("image/expression/SpongebobSad.png");
    		profileLabel = new JLabel(profileImage);
    		profileLabel.setSize(150, 150);
    		profileLabel.setLocation(75 ,95);
    		profileLabel.setBorder(new LineBorder(Color.WHITE, 3));
    		this.add(profileLabel);
		}
		else if(GameManagement.profile.equals("PatrickStar")) {
			profileImage = new ImageIcon("image/expression/PatrickStarNormal.png");
			this.normalProfileImage = new ImageIcon("image/expression/PatrickStarNormal.png");
			this.sadProfileImage = new ImageIcon("image/expression/PatrickStarSad.png");
    		profileLabel = new JLabel(profileImage);
    		profileLabel.setSize(150, 150);
    		profileLabel.setLocation(75 ,95);
    		profileLabel.setBorder(new LineBorder(Color.WHITE, 3));
    		this.add(profileLabel);
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
		this.add(scoreLabelText);
		
		scoreLabel.setFont(defaultFont);
		scoreLabel.setSize(100, 30);
		scoreLabel.setLocation(100 ,320);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setOpaque(true);
		scoreLabel.setBackground(Color.WHITE);
		this.add(scoreLabel);
		
	}

	// 단어가 일치할 때 호출되는 메소드
	public void setNormalProfileImage() { profileLabel.setIcon(normalProfileImage); }
	
	// 단어가 일치하지 않을 때 호출되는 메소드
	public void setSadProfileImage() { profileLabel.setIcon(sadProfileImage); }
	
	// 점수 증가 메소드
	public void increase() { 
		
		// 난이도에 따라 증가하는 점수가 다름
		if (GameManagement.difficulty.equals("Normal")) {
			score += 15;
			scoreLabel.setText(Integer.toString(getScore())); 
		}
		else if (GameManagement.difficulty.equals("Hard")) {
			score += 20;
			this.scoreLabel.setText(Integer.toString(getScore())); 
		}
		else {
			score += 10;
			scoreLabel.setText(Integer.toString(getScore()));
		}
		
	}
	
	// 점수 감소 메소드
	public void decrease() { 
		
		// 난이도에 따라 감소하는 점수가 다름
		if (GameManagement.difficulty.equals("Normal")) {
			score -= 15;
			scoreLabel.setText(Integer.toString(getScore())); 
		}
		else if (GameManagement.difficulty.equals("Hard")) {
			score -= 20;
			scoreLabel.setText(Integer.toString(getScore())); 
		}
		else {
			score -= 10;
			scoreLabel.setText(Integer.toString(getScore()));
		}
		
	}
	
	// 점수 리턴 메소드
	public int getScore() { return score; }
	
}