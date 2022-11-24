import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;


public class PlayerSettingDialog extends JDialog {

	private Font defaultFont = new Font("Jokerman", Font.BOLD, 15); // 기본 폰트 설정
	
	// Player Name 부분
	private JLabel playerNameLabel = new JLabel("Player Name"); // Player Name 출력 레이블
	private JTextField inputPlayerNameField = new JTextField(20); // Player Name 입력받는 필드
	
	// Choose Profile 부분
	// ** JRadioButton으로 만들기 
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
	private JButton OpenFileButton = new JButton("Open File");
	private JFileChooser chooser; // JFileChooser 레퍼런스 변수 선언
	private String pathName = null;
	private String fileName = null;
	
	// Complete Settings와 Cancel 부분
	private JButton completeSettingsButton = new JButton("Complete Settings");
	private JButton cancelButton = new JButton("Cancel");
	
	
	// ** 플레이어 정보 저장
	// 플레이어 이름 / 프로필 사진 / 난이도 / 파일 / 오디오
	private String settingDifficulty = null;
	
	public PlayerSettingDialog() {
		
		Container c = getContentPane();
		setTitle("Setting Player");
		
		// 배치관리자 제거
		c.setLayout(null);
		
		// 폰트 설정
		playerNameLabel.setFont(defaultFont);
		inputPlayerNameField.setFont(defaultFont);
		chooseProfileLabel.setFont(defaultFont);
		difficultyLabel.setFont(defaultFont);
		languageLabel.setFont(defaultFont);
		OpenFileButton.setFont(defaultFont);
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
//			profileImageLabel[i].setBorder(new LineBorder(Color.RED)); 
			c.add(profileImageLabel[i]);
			// 마우스 올라가면 주위에 파란색 테두리 생기게 마우스 리스너,
			// 마우스 클릭하면 주위에 빨간색 테두리 생기게 마우스 리스너
		}
		
		// **이미지 선택하는 것을 기다리는 스레드 생성
		
		
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
		
		// Choose Language (by File) 부분
		languageLabel.setSize(145, 40);
		languageLabel.setLocation(30, 320);
		
		OpenFileButton.setSize(170, 40);
		OpenFileButton.setLocation(30, 365);
		
		OpenFileButton.addActionListener(new ActionListener() { // 버튼 클릭되면 파일 선택 다이얼로그 나타남
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				chooser = new JFileChooser(); // 파일 다이얼로그 생성
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"PNG images", // 파일 이름난에 출력될 문자열
						"png"); // 파일 필터로 사용되는 확장자, *.png만 나열됨 -> **txt로 바꾸기

				
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
		
		// Complete Settings와 Cancel 부분
		completeSettingsButton.setSize(190, 50);
		completeSettingsButton.setLocation(65, 450);
		cancelButton.setSize(190, 50);
		cancelButton.setLocation(550/2+20, 450);
		
		// Complete Settings가 눌리면, 게임을 시작하시겠습니까? 다이얼로그 출력하고 YES NO 띄우고, 
		// YES 이면 게임 시작 창으로 넘어가기, NO 이면 이전 상태로 돌아가기
		completeSettingsButton.addActionListener(new ActionListener() {
			
			// confirm Dialog 출력
			public void actionPerformed(ActionEvent e) {
				int confirmResult = JOptionPane.showConfirmDialog(c, "입력한 정보가 맞습니까?", "Confirm Setting Infomation",
						JOptionPane.YES_NO_OPTION);
				
				if (confirmResult == JOptionPane.YES_OPTION) {
					
					// 플레이어 이름 출력
					System.out.println("Player Name : " + inputPlayerNameField.getText());
					
					// 파일 이름 출력 
					System.out.println("File : " + fileName );
					
					// **플레이어가 설정한 정보 저장하기
					setVisible(false);
					// 게임 화면으로 넘어가는 것 
					App.run();
					dispose();
//					// StartFrame 없애기
					
				}

			}
		});
	
		// 크기와 위치 설정 끝
		
		// 컴포넌트들 부착
		c.add(playerNameLabel);
		c.add(inputPlayerNameField);
		c.add(chooseProfileLabel);
		c.add(difficultyLabel);
		c.add(languageLabel);
		c.add(OpenFileButton);
		c.add(completeSettingsButton);
		c.add(cancelButton);
		
		setSize(550, 580); // 창 크기
		setVisible(false); // 창 보이게
		setResizable(false); // 창 크기 변경 불가능하게	
		
	}

}
