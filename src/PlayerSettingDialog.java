import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Enumeration;


public class PlayerSettingDialog extends JDialog {
	
	// 오디오 전달 받을 수 있게 레퍼런스 선언
	public Audio audio;
	
	Container contentPane;
	StartFrame startFrame = null;
	
	// 정보 저장하는 static 변수
	public static String name = "";
	public static String profile = "SpongebobSquarepants"; // 초기 설정
	public static String difficulty = "Easy"; // 초기 설정
	public static String pathName = "";
	public static String fileName = "";
	
	private Font defaultFont = new Font("Jokerman", Font.BOLD, 15); // 기본 폰트 설정
	
	// Player Name 부분
	private JLabel playerNameLabel = new JLabel("Player Name"); // Player Name 출력 레이블
	private JTextField inputPlayerNameField = new JTextField(20); // Player Name 입력받는 필드
	
	// Choose Profile 부분
	private JLabel chooseProfileLabel = new JLabel("Choose Profile"); // Choose Profile 출력 레이블
	private String profileArray [] = { "SpongebobSquarepants.png", "PatrickStar.png" };
	private ImageIcon profileImage [] = new ImageIcon[2];
	ButtonGroup profileButtonGroup = new ButtonGroup();
	JRadioButton profileButtonComponents[] = new JRadioButton[2];
	
	// Choose Difficulty 부분
	private JLabel difficultyLabel = new JLabel("Choose Difficulty"); // Choose Difficulty 출력 레이블
	private String difficultyArray [] = { "Easy", "Normal", "Hard" };
	private ButtonGroup difficultyButtonGroup = new ButtonGroup();
	private JRadioButton difficultyButtonComponents[] = new JRadioButton[3];
	
	// Choose Language 부분
	private JLabel languageLabel = new JLabel("Choose Language"); // Choose Language 출력 레이블
	private JButton OpenFileButton = new JButton("Open File");
	private JFileChooser chooser; // JFileChooser 레퍼런스 변수 선언
	
	// Complete Settings와 Cancel 부분
	private JButton completeSettingsButton = new JButton("Complete Settings");
	private JButton cancelButton = new JButton("Cancel");

	// 생성자
	public PlayerSettingDialog(StartFrame startFrame, Audio audio) {

		super(startFrame, "Setting Player", true);
		this.startFrame = startFrame;
		this.audio = audio;

		contentPane = getContentPane();
		
		// 배치관리자 제거
		contentPane.setLayout(null);
		
		setAllSizeAndLocation(); // 컴포넌트 부착을 위한 컴포넌트 크기 및 위치 설정
		setAllFont(); // 폰트 설정
		addAllComponents(); // 모든 컴포넌트들 부착
		addAllActionListener(); // 필요한 액션리스너들 부착
		
		setSize(550, 580); // 창 크기
		setVisible(false); // 창 보이게
		setResizable(false); // 창 크기 변경 불가능하게	
		
	} // 생성자 끝

	private void setAllSizeAndLocation() { // 컴포넌트 부착을 위한 컴포넌트 크기 및 위치 설정
		
		// PlayerName 부분
		playerNameLabel.setSize(100, 40);
		playerNameLabel.setLocation(30, 30);
		inputPlayerNameField.setSize(150, 40);
		inputPlayerNameField.setLocation(150, 30);

		// Choose Profile 부분 
		chooseProfileLabel.setSize(115, 40);
		chooseProfileLabel.setLocation(30, 90);
		
		for (int i = 0; i<profileButtonComponents.length; i++) {
			profileImage[i] = new ImageIcon(profileArray[i]);
			profileButtonComponents[i] = new JRadioButton(profileImage[i]);
			profileButtonComponents[i].setSize(60, 75); 			
			profileButtonComponents[i].setLocation(30+(i*80), 135);
		}
		profileButtonComponents[0].setSelected(true); // 스폰지밥 Default로 선택
		
		// Choose Difficulty 부분
		difficultyLabel.setSize(145, 40);
		difficultyLabel.setLocation(30, 230);
				
		for (int i = 0; i<difficultyButtonComponents.length; i++) {
			difficultyButtonComponents[i] = new JRadioButton(difficultyArray[i]);
			difficultyButtonComponents[i].setSize(100, 40); 
			difficultyButtonComponents[i].setLocation(30+(i*100), 270);
		}
		difficultyButtonComponents[0].setSelected(true); // Easy 모드 Default로 선택
				
		// Choose Language (by File) 부분
		languageLabel.setSize(145, 40);
		languageLabel.setLocation(30, 320);
				
		OpenFileButton.setSize(170, 40);
		OpenFileButton.setLocation(30, 365);
				
		// Complete Settings와 Cancel 부분
		completeSettingsButton.setSize(190, 50);
		completeSettingsButton.setLocation(65, 450);
		cancelButton.setSize(190, 50);
		cancelButton.setLocation(550/2+20, 450);
	
	} 
	

	private void setAllFont() { // 폰트 설정

		playerNameLabel.setFont(defaultFont);
		inputPlayerNameField.setFont(defaultFont);	
		chooseProfileLabel.setFont(defaultFont);
		difficultyLabel.setFont(defaultFont);
		for (int i = 0; i<difficultyButtonComponents.length; i++) {
			difficultyButtonComponents[i].setFont(defaultFont);
		}
		languageLabel.setFont(defaultFont);
		OpenFileButton.setFont(defaultFont);
		completeSettingsButton.setFont(defaultFont);
		cancelButton.setFont(defaultFont);
		
	}
	
	private void addAllComponents() { // 모든 컴포넌트들 부착
		
		contentPane.add(playerNameLabel);
		contentPane.add(inputPlayerNameField);
		contentPane.add(chooseProfileLabel);
		for (int i = 0; i<profileArray.length; i++) {
			profileButtonGroup.add(profileButtonComponents[i]);
			contentPane.add(profileButtonComponents[i]);
		}
		contentPane.add(difficultyLabel);
		for (int i = 0; i<difficultyButtonComponents.length; i++) {
			difficultyButtonGroup.add(difficultyButtonComponents[i]);
			contentPane.add(difficultyButtonComponents[i]);
		}
		contentPane.add(languageLabel);
		contentPane.add(OpenFileButton);
		contentPane.add(completeSettingsButton);
		contentPane.add(cancelButton);
		
	}
	
	private void addAllActionListener() {
		
		OpenFileButton.addActionListener(new ActionListener() { // 버튼 클릭되면 파일 선택 다이얼로그 나타남
			
			@Override
			public void actionPerformed(ActionEvent e) {
						
				chooser = new JFileChooser(); // 파일 다이얼로그 생성
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"TXT files", // 파일 이름난에 출력될 문자열
						"txt"); // 파일 필터로 사용되는 확장자, *.txt만 나열됨

						
				chooser.setFileFilter(filter);// 파일 다이얼로그에 파일 필터 설정
						
				int ret = chooser.showOpenDialog(null); // 파일 열기 다이얼로그 출력
				if(ret != JFileChooser.APPROVE_OPTION) { // 파일 선택이 정상적으로 수행된 경우
					JOptionPane.showMessageDialog(null, "파일을 선택하세요", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
						
				// 파일 선택이 정상적으로 수행된 경우 
				pathName = chooser.getSelectedFile().getPath(); // 사용자가 선택한 파일의 완전경로명 알아내기
				fileName = chooser.getSelectedFile().getName(); // 사용자가 선택한 파일의 이름 알아내기
				OpenFileButton.setText(fileName); // 버튼 텍스트를 파일 이름으로 설정
						
			}
		});
		
		for (int i = 0; i<profileButtonComponents.length; i++) {
			profileButtonComponents[i].addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					if(e.getStateChange() == ItemEvent.DESELECTED) return;
					if (profileButtonComponents[0].isSelected()) {
						profile = "SpongebobSquarepants";
						GameManagement.profile = profile;
						GameManagement.profileImage = new ImageIcon("SpongebobSquarepantsprofile.png");
					}
					else if (profileButtonComponents[1].isSelected()) {
						profile = "PatrickStar";
						GameManagement.profile = profile;
						GameManagement.profileImage = new ImageIcon("PatrickStarprofile.png");
					}
			}});
		}
		
		for (int i = 0; i<difficultyButtonComponents.length; i++) {
			difficultyButtonComponents[i].addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					if (difficultyButtonComponents[0].isSelected()) {
						difficulty = "Easy";
						GameManagement.difficulty = difficulty;
					}
					else if (difficultyButtonComponents[1].isSelected()) {
						difficulty = "Normal";
						GameManagement.difficulty = difficulty;
					}
					else if (difficultyButtonComponents[2].isSelected()) {
						difficulty = "Hard";
						GameManagement.difficulty = difficulty;
					}
			}});
		}
		
		// Complete Settings가 눌리면, 게임을 시작하시겠습니까? 다이얼로그 출력하고 YES NO 띄우고, 
		// YES 이면 게임 시작 창으로 넘어가기, NO 이면 이전 상태로 돌아가기
		completeSettingsButton.addActionListener(new ActionListener() {
							
			// confirm Dialog 출력
			public void actionPerformed(ActionEvent e) {
				
				name = inputPlayerNameField.getText();
				
				if (name.equals("")) 
					JOptionPane.showMessageDialog(null, "이름을 입력하세요!!", "경고", JOptionPane.ERROR_MESSAGE);
				
				else if (fileName.equals(""))
					JOptionPane.showMessageDialog(null, "파일을 선택하세요!!", "경고", JOptionPane.ERROR_MESSAGE);
				
				else {
					
					int confirmResult = JOptionPane.showConfirmDialog(contentPane, "입력한 정보가 맞습니까?", "Confirm Setting Infomation",
						JOptionPane.YES_NO_OPTION);
								
				if (confirmResult == JOptionPane.YES_OPTION) {
					
					GameManagement.name = name;
					GameManagement.pathName = pathName;
					GameManagement.fileName = fileName;
					
					// 플레이어 이름 출력
					System.out.println("Player Name : " + name);
					// 프로필 선택한 것 출력
					System.out.println("Choosed Profile : " + profile);
					// 난이도 선택한 것 출력
					System.out.println("난이도 : " + difficulty);
					// 파일 이름 출력 
					System.out.println("File : " + fileName);
										
					audio.stopAudio("startFrame"); // 시작 음악 정지
					dispose(); // PlayerSettingDialog 종료
					startFrame.setVisible(false); // StartFrame 안보이게
					startFrame.dispose(); // StartFrame 종료
					App.run(); // 게임 화면으로 넘어가기 
					
					}
				}
			}
		});
		
		
		
	}
	
}
