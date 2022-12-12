import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

public class GamePanel extends JPanel {
		
    private static int MAX_WORDS = 30;
    public static int fontRandSize = 10;
    private final int LABEL_WIDTH = 150;
    private final int LABEL_HEIGHT = 40;

    private ImageIcon icon;
    private Font defaultFont = new Font("Jokerman", Font.BOLD, 15);
    private JTextField inputField = new JTextField(MAX_WORDS);
    private JLabel timeLabel = new JLabel("3");
    
    // 생성자에게 전달 위한 레퍼런스 선언
    private GameFrame gameFrame = null;
    private WordList wordList = null;
    private ProfileAndScorePanel profileAndScorePanel = null;
    private Audio audio = null;
    
    private MainPlayPanel MainPlayPanel = new MainPlayPanel();
    
    // 단어 저장을 위한 벡터 생성
    private Vector<Word> currentWords = new Vector<>(MAX_WORDS);
    
    // 스레드 실행을 위한 레퍼런스 선언
    private BeforeGameStartThread beforeGameStartThread = null;
    private GameTimerThread gameTimerThread = null;
    private GameThread gameThread = null;
    
    // 랭킹 확인 프레임을 생성을 위한 레퍼런스 선언
    private UserRankingFrame userRankingFrame;
	
    // 생성자
    public GamePanel(GameFrame gameFrame, WordList wordList, ProfileAndScorePanel profileAndScorePanel, Audio audio) {
        
    	this.gameFrame = gameFrame;
        this.wordList = wordList;
        this.profileAndScorePanel = profileAndScorePanel;
        this.audio = audio;

        setLayout(new BorderLayout());
        
        makeMainPlayPanel(); // 플레이 영역 만들기
        makeTimePanel(); // 시간 영역 만들기
        makeInputPanel(); // 입력 영역 만들기
        addStartActionListener(); // 액션 리스너 등록
   
    }
    
    // 플레이 영역을 가운데에 부착
    private void makeMainPlayPanel() { add(MainPlayPanel, BorderLayout.CENTER); }
    
    // 시간 영역을 위쪽에 부착
    private void makeTimePanel() {
    	
    	JPanel timePanel = new JPanel();
        timePanel.setBackground(Color.YELLOW);
        timeLabel.setFont(defaultFont);
        timePanel.add(timeLabel);
        add(timePanel, BorderLayout.NORTH);
        
    }
    
    // 입력 영역을 아래쪽에 부착
    private void makeInputPanel() {
    	
    	 JPanel inputPanel = new JPanel();
    	 
    	 Color backgroundColor = new Color(221, 176, 141);
         inputPanel.setBackground(backgroundColor);
         
         inputPanel.add(inputField, BorderLayout.SOUTH);
         add(inputPanel, BorderLayout.SOUTH);
         
    }
    
    // 액션 리스너 등록
    private void addStartActionListener() {
    	
    	inputField.addActionListener(new ActionListener() {
    		
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource(); // 입력받은 것 전달받음
                boolean isCorrect = false;
                // 비교 시작
                for (Word word : currentWords) {
                    if (textField.getText().equals(word.getName())) { // 단어가 일치한다면
                    	profileAndScorePanel.increase(); // 점수 증가
                        word.setY(MainPlayPanel.getHeight()); // 단어를 아래에 배치
                        isCorrect = true; // 단어가 일치함을 표현
                        profileAndScorePanel.setNormalProfileImage(); // 표정 바꾸기
                        break;
                    }
                }
                if (!isCorrect) { // 단어가 일치하지 않는다면
                	profileAndScorePanel.decrease(); // 점수 감소
                	profileAndScorePanel.setSadProfileImage(); // 표정 바꾸기
                } 
                textField.setText(""); // 텍스트 필드 초기화
            }
        });
    	
    }
    
    // 3초 카운트 다운 메소드
    public void startCountDown() {
    	if (beforeGameStartThread == null) {
    		beforeGameStartThread = new BeforeGameStartThread(this, timeLabel, audio);
    		beforeGameStartThread.start();
    	}
    }
    
    // 게임 시작 메소드
    public void startGame() {
        if (gameThread == null) {
        	gameThread = new GameThread(this);
        	gameThread.start();
        }
    }
    
    // 게임 타이머 시작 메소드
    public void startGameTimerThread() {
    	if (gameTimerThread == null) {
    		gameTimerThread = new GameTimerThread(this, timeLabel, audio);
    		gameTimerThread.start();
    	}
    }
    
    // 게임 종료 메소드
    public void gameEnd() {
    	
		if (gameThread != null) {
    		gameThread.interrupt(); // 게임 스레드 종료
    		this.removeAll(); // 화면 정지
    		audio.closeAudio("gameBackground"); 
    		JOptionPane.showMessageDialog(gameFrame, "Game ended. Confirm result!", "Game Ended", JOptionPane.ERROR_MESSAGE); // 팝업 띄우기
    		gameFrame.dispose(); // gameFrame 종료
    		userRankingFrame = new UserRankingFrame(profileAndScorePanel); // 랭킹 확인 프레임 띄우기
    	}
		
	}

    // 게임 중지 메소드
    public void stopGame() {
    	
    	if(gameThread.getStopFlag() == false) {
    		gameThread.stopGame();
    		inputField.setEditable(false);
    	}
    	if(gameTimerThread.getStopFlag() == false) {
    		gameTimerThread.stopTimer();
    	}
    	
    }
    
    // 게임 재개 메소드
    public void resumeGame() {
    	
    	if(gameThread.getStopFlag() == true) {
    		gameThread.resumeGame();
    		inputField.setEditable(true);
    	}
    	if(gameTimerThread.getStopFlag() == true) {
    		gameTimerThread.resumeTimer();
    	}
    	
    }
    
    // 단어 추가 메소드
    public void addWord() {
        int x = (int) (Math.random() * (MainPlayPanel.getWidth() - LABEL_WIDTH / 2));
        Word word = new Word(wordList.getWord(), x, 0, Math.random() / 20 + 0.1);
        currentWords.add(word);
        addLabel(word);
    }

    // 레이블을 패널에 부착하는 메소드
    private void addLabel(Word word) {
    
        JLabel label = word.getLabel();
        label.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        label.setLocation(getX(), 0);
        fontRandSize = (int)(Math.random()*10 + 10); // 글자 크기 랜덤
        label.setFont(new Font("Jokerman", Font.BOLD, fontRandSize));
        MainPlayPanel.add(label);
        
    }

    // 단어 세팅 메소드
    public void setWords() {
    	
        Iterator<Word> iterator = currentWords.iterator(); // currentWords의 요소를 순차 검색할 Iterator 객체 리턴
        while (iterator.hasNext()) { // 방문할 요소가 남아있다면
            Word word = iterator.next(); // iterator가 가리키는 요소 리턴
            word.setY(word.getY() + word.getSpeed()); // Y 좌표 설정
            JLabel label = word.getLabel(); // 단어 받아오기
            label.setLocation((int) (word.getX()), (int) word.getY()); // 레이블 위치 지정
            if (label.getY() >= MainPlayPanel.getHeight()) { // 영역을 벗어나면
                MainPlayPanel.remove(label); // 패널에서 레이블 제거
                iterator.remove(); // iterator에서 제거
            }
        }
        
    }

    class MainPlayPanel extends JPanel {
    	
    	// 게임 배경 화면 그리는 메소드
    	@Override
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		g.drawImage(getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
    	}
    	
    	// 생성자
        public MainPlayPanel() { }
        
        // 게임 배경 화면 받아오는 메소드
        public Image getBackgroundImage() {
        	
        	if(GameManagement.profile.equals("SpongebobSquarepants")) {
        		icon = new ImageIcon("image/background/SpongebobBackground.png");
        		return icon.getImage();
        	}
        	else {
        		icon = new ImageIcon("image/background/PatrickStarBackground.png");
        		return icon.getImage();
        	}
        }
       
    }

}