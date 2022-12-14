import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;

public class PlayerSettingDialog extends JDialog {
	
	// 생성자에게 전달 위한 레퍼런스 선언
	StartFrame startFrame = null;
	public Audio audio = null;
	
	private Container contentPane;
	private Font defaultFont = new Font("Jokerman", Font.BOLD, 15);

	// 유저의 정보 저장하는 변수 (GameManagemet의 static 변수에 저장)
	private String name = null;
	private String profile = "SpongebobSquarepants"; // 초기 설정
	private String difficulty = "Easy"; // 초기 설정
	private String pathName = null;
	private String fileName = null;
	
	// Player Name 부분
	private JLabel playerNameLabel = new JLabel("Player Name"); 
	private JTextField inputPlayerNameField = new JTextField(20); // Player Name 입력받는 필드
	
	// Choose Profile 부분
	private JLabel chooseProfileLabel = new JLabel("Choose Profile"); 
	private String profileArray [] = { "image/settingProfile/SpongebobSquarepants.png", "image/settingProfile/PatrickStar.png" };
	private ImageIcon profileImage [] = new ImageIcon[2];
	ButtonGroup profileButtonGroup = new ButtonGroup();
	JRadioButton profileButtonComponents[] = new JRadioButton[2];
	
	// Choose Difficulty 부분
	private JLabel difficultyLabel = new JLabel("Choose Difficulty"); 
	private String difficultyArray [] = { "Easy", "Normal", "Hard" };
	private ButtonGroup difficultyButtonGroup = new ButtonGroup();
	private JRadioButton difficultyButtonComponents[] = new JRadioButton[3];
	
	// Choose File 부분
	private JLabel fileLabel = new JLabel("Choose File"); 
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
		contentPane.setLayout(null);
		
		setAllSizeAndLocation(); // 컴포넌트 부착을 위한 컴포넌트 크기 및 위치 설정
		setAllFont(); // 폰트 설정
		makeAllComponents(); // 모든 컴포넌트들 부착
		addAllActionListener(); // 필요한 액션리스너들 부착
		
		setSize(550, 580);
		setVisible(false);
		setResizable(false); // 창 크기 변경 불가능하게	
		
		if (GameManagement.fileName != null) {
			OpenFileButton.setText(GameManagement.fileName);
			fileName = GameManagement.fileName;
		}
		
	} 

	private void setAllSizeAndLocation() { 
		
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
				
		// Choose File 부분
		fileLabel.setSize(145, 40);
		fileLabel.setLocation(30, 320);
				
		OpenFileButton.setSize(170, 40);
		OpenFileButton.setLocation(30, 365);
				
		// Complete Settings와 Cancel 부분
		completeSettingsButton.setSize(190, 50);
		completeSettingsButton.setLocation(65, 450);
		cancelButton.setSize(190, 50);
		cancelButton.setLocation(550/2+20, 450);
	
	} 

	private void setAllFont() {

		playerNameLabel.setFont(defaultFont);
		inputPlayerNameField.setFont(defaultFont);	
		chooseProfileLabel.setFont(defaultFont);
		difficultyLabel.setFont(defaultFont);
		for (int i = 0; i<difficultyButtonComponents.length; i++) {
			difficultyButtonComponents[i].setFont(defaultFont);
		}
		fileLabel.setFont(defaultFont);
		OpenFileButton.setFont(defaultFont);
		completeSettingsButton.setFont(defaultFont);
		cancelButton.setFont(defaultFont);
		
	}
	
	private void makeAllComponents() {
		
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
		contentPane.add(fileLabel);
		contentPane.add(OpenFileButton);
		contentPane.add(completeSettingsButton);
		contentPane.add(cancelButton);
		
	}
	
	private void addAllActionListener() {
		
		// 버튼 클릭 시 파일 다이얼로그 오픈
		OpenFileButton.addActionListener(new ActionListener() {   
			
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
				pathName = chooser.getSelectedFile().getPath(); // 사용자가 선택한 파일 완전경로명
				fileName = chooser.getSelectedFile().getName(); // 사용자가 선택한 파일 이름
				OpenFileButton.setText(fileName); // 버튼 텍스트를 파일 이름으로 설정
						
			}
		});
		
		// 프로필 선택 버튼에 리스너 등록
		for (int i = 0; i<profileButtonComponents.length; i++) {
			profileButtonComponents[i].addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					if(e.getStateChange() == ItemEvent.DESELECTED) return;
					if (profileButtonComponents[0].isSelected()) {
						profile = "SpongebobSquarepants";
						GameManagement.profile = profile;
						GameManagement.profileImage = new ImageIcon("image/resultProfile/SpongebobSquarepantsprofile.png");
					}
					else if (profileButtonComponents[1].isSelected()) {
						profile = "PatrickStar";
						GameManagement.profile = profile;
						GameManagement.profileImage = new ImageIcon("image/resultProfile/PatrickStarprofile.png");
					}
			}});
		}
		
		// 난이도 선택 버튼에 리스너 등록
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
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { dispose(); }

		});
		
		
		// Complete Settings 버튼에 리스너 등록 
		// 게임을 시작하시겠습니까? 다이얼로그 출력하고 YES NO 띄우고, 
		// YES이면 게임 화면으로 넘어가기, NO이면 이전 상태로 돌아가기
		completeSettingsButton.addActionListener(new ActionListener() {
							
			// confirm Dialog 출력
			@Override
			public void actionPerformed(ActionEvent e) {
				
				name = inputPlayerNameField.getText();
				
				if (name.equals("") || name == null) // 이름이 입력되지 않은 경우 경고
					JOptionPane.showMessageDialog(null, "이름을 입력하세요!!", "경고", JOptionPane.ERROR_MESSAGE);
				
				else if (fileName == null) // 파일이 선택되지 않은 경우 경고
					JOptionPane.showMessageDialog(null, "파일을 선택하세요!!", "경고", JOptionPane.ERROR_MESSAGE);
				
				else { // 이름이 입력되었고 파일이 선택되었다면
					
					int confirmResult = JOptionPane.showConfirmDialog(contentPane, "입력한 정보가 맞습니까?", "Confirm Setting Infomation",
						JOptionPane.YES_NO_OPTION);
								
					if (confirmResult == JOptionPane.YES_OPTION) {
						
						GameManagement.name = name;
						GameManagement.pathName = pathName;
						GameManagement.fileName = fileName;
						
						if (GameManagement.fileName.equals("words.txt"))
							GameManagement.fileName = ("txt/" + fileName);
						else if (GameManagement.fileName.equals("toeicwords.txt"))
							GameManagement.fileName = ("txt/" + fileName);
						
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
