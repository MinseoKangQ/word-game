import javax.swing.*;


import java.awt.*;
import java.awt.event.*;


public class PlayerSettingDialog extends JDialog {
	
	private Font defaultFont = new Font("Jokerman", Font.BOLD, 15); // 기본 폰트 설정
	
	// Player Name 부분
	private JLabel playerNameLabel = new JLabel("Player Name"); // Player Name 출력 레이블
	private JTextField inputPlayerNameField = new JTextField(20); // Player Name 입력받는 필드
	
	// Choose Profile 부분
	private JLabel chooseProfileLabel = new JLabel("Choose Profile"); // Choose Profile 출력 레이블
	private String profileArray [] = { 
			"SpongebobSquarepants.png", "PatrickStar.png", "Squidward Tentacles.png",
			"SandraCheeks.png", "EugeneHaroldKrabs.png", "SheldonJanetPlankton.png"
	};
	private ImageIcon profileImage [] = new ImageIcon[6];
	private JLabel profileImageLabel [] = new JLabel[6];
	
	// Choose Difficulty 부분
	private JLabel difficultyLabel = new JLabel("Choose Difficulty"); // Choose Difficulty 출력 레이블
	private String difficultyArray [] = { "Easy", "Hard", "Normal" };
	private ButtonGroup difficultyButtonGroup = new ButtonGroup();
	private JRadioButton difficultyButtonComponents[] = new JRadioButton[3];
	
	// Choose Language 부분
	private JLabel languageLabel = new JLabel("Choose Language"); // Choose Language 출력 레이블
	private String languageArray [] = { "English", "Korean", "Both" };
	private ButtonGroup languageButtonGroup = new ButtonGroup();
	private JRadioButton languageButtonComponents[] = new JRadioButton[3];
	
	// Sound 부분
	private JLabel soundLabel = new JLabel("Sound"); // Sound 출력 레이블
	private String soundArray [] = { "Play", "Mute"};
	private ButtonGroup soundButtonGroup = new ButtonGroup();
	private JRadioButton soundButtonComponents[] = new JRadioButton[2];
	
	// Complete Settings와 Cancel 부분
	private JButton completeSettingsButton = new JButton("Complete Settings");
	private JButton cancelButton = new JButton("Cancel");
	
	public PlayerSettingDialog(StartFrame frame, String title) {
		
		super(frame, title, true);
//		setTitle(); // Title 설정
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫히면 프로그램 종료 --> ?
		
		Container c = getContentPane();
		
		// 배치관리자 제거
		c.setLayout(null);
		
		// 폰트 설정
		playerNameLabel.setFont(defaultFont);
		inputPlayerNameField.setFont(defaultFont);
		chooseProfileLabel.setFont(defaultFont);
		difficultyLabel.setFont(defaultFont);
		languageLabel.setFont(defaultFont);
		soundLabel.setFont(defaultFont);
		completeSettingsButton.setFont(defaultFont);
		cancelButton.setFont(defaultFont);
		
		// 크기와 위치 설정 시작 
		// PlayerName 부분
		playerNameLabel.setSize(100, 40);
		playerNameLabel.setLocation(30, 30);
		inputPlayerNameField.setSize(150, 40);
		inputPlayerNameField.setLocation(150, 30);

		// Choose Profile 부분 
		chooseProfileLabel.setSize(115, 40);
		chooseProfileLabel.setLocation(30, 90);
		// 이미지 레이블 생성
		for (int i = 0; i<profileArray.length; i++) {
			profileImage[i] = new ImageIcon(profileArray[i]);
			profileImageLabel[i] = new JLabel(profileImage[i]);
			profileImageLabel[i].setSize(60, 75); 
			profileImageLabel[i].setLocation(30+(i*80), 135);
			c.add(profileImageLabel[i]);
			// 마우스 올라가면 주위에 파란색 테두리 생기게 마우스 리스너,
			// 마우스 클릭하면 주위에 빨간색 테두리 생기게 마우스 리스너
		}
		
		// Choose Difficulty 부분
		difficultyLabel.setSize(145, 40);
		difficultyLabel.setLocation(30, 230);
		
		for (int i = 0; i<difficultyButtonComponents.length; i++) {
			difficultyButtonComponents[i] = new JRadioButton(difficultyArray[i]);
			difficultyButtonGroup.add(difficultyButtonComponents[i]);
			difficultyButtonComponents[i].setSize(100, 40); 
			difficultyButtonComponents[i].setLocation(30+(i*80), 270);
			difficultyButtonComponents[i].setFont(defaultFont);
			c.add(difficultyButtonComponents[i]);
		}
		
		difficultyButtonComponents[0].setSelected(true); // Easy 모드 Default로 선택
		
		// Choose Language 부분
		languageLabel.setSize(145, 40);
		languageLabel.setLocation(30, 320);
		
		for (int i = 0; i<languageButtonComponents.length; i++) {
			languageButtonComponents[i] = new JRadioButton(languageArray[i]);
			languageButtonGroup.add(languageButtonComponents[i]);
			languageButtonComponents[i].setSize(100, 40); 
			languageButtonComponents[i].setLocation(30+(i*100), 360);
			languageButtonComponents[i].setFont(defaultFont);
			c.add(languageButtonComponents[i]);
		}
		
		languageButtonComponents[0].setSelected(true); // English 모드 Default로 선택
		
		// Sound 부분
		soundLabel.setSize(145, 40);
		soundLabel.setLocation(30, 410);
		
		for (int i = 0; i<soundButtonComponents.length; i++) {
			soundButtonComponents[i] = new JRadioButton(soundArray[i]);
			soundButtonGroup.add(soundButtonComponents[i]);
			soundButtonComponents[i].setSize(100, 40); 
			soundButtonComponents[i].setLocation(30+(i*80), 450);
			soundButtonComponents[i].setFont(defaultFont);
			c.add(soundButtonComponents[i]);
		}
		
		soundButtonComponents[0].setSelected(true); // Play 모드 Default로 선택

		// Complete Settings와 Cancel 부분
		completeSettingsButton.setSize(190, 50);
		completeSettingsButton.setLocation(65, 520);
		cancelButton.setSize(190, 50);
		cancelButton.setLocation(550/2+20, 520);
		
		
		
		// 크기와 위치 설정 끝
		
		// 컴포넌트들 부착
		c.add(playerNameLabel);
		c.add(inputPlayerNameField);
		c.add(chooseProfileLabel);
		c.add(difficultyLabel);
		c.add(languageLabel);
		c.add(soundLabel);
		c.add(completeSettingsButton);
		c.add(cancelButton);
		
		setSize(550, 620); // 창 크기
		setVisible(true); // 창 보이게
		setResizable(false); // 창 크기 변경 불가능하게	
		
	}


}
