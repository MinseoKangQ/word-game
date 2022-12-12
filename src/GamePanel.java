import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Iterator;
import java.util.Vector;

public class GamePanel extends JPanel {
		
		
    private static int MAX_WORDS = 30; // 난이도에 따라 다르게 설정하기
    public static int fontRandSize = 10;
    private final int LABEL_WIDTH = 150;
    private final int LABEL_HEIGHT = 40;

    private Font defaultFont = new Font("Jokerman", Font.BOLD, 15); // 기본 폰트 설정
    private Audio audio = null;
    private JTextField inputField = new JTextField(MAX_WORDS);
    
    private JLabel timeLabel = new JLabel("3");
    WordList wordList;
    ProfileAndScorePanel profileAndScorePanel;
    private Vector<Word> currentWords = new Vector<>(MAX_WORDS);
    private MainPlayPanel MainPlayPanel = new MainPlayPanel();
    private BeforeGameStartThread beforeGameStartThread = null;
    private GameTimerThread gameTimerThread = null;
    private GameThread gameThread = null;
    private GameFrame gameFrame = null;
    private UserRankingFrame userRankingFrame;
//    private UserRankingManagement userRankingManagement = new UserRankingManagement();
    
    private ImageIcon icon;
	
    public GamePanel(GameFrame gameFrame, WordList wordList, ProfileAndScorePanel profileAndScorePanel, Audio audio) {
        
    	this.gameFrame = gameFrame;
        this.wordList = wordList;
        this.profileAndScorePanel = profileAndScorePanel;
        this.audio = audio;

        setLayout(new BorderLayout());
        
        makeMainPlayPanel();
        makeTimePanel();
        makeInputPanel();
        addStartActionListener();
   
    }

    
    private void makeMainPlayPanel() {
    	
    	add(MainPlayPanel, BorderLayout.CENTER);
    	
    }
    
    private void makeTimePanel() {
    	
    	JPanel timePanel = new JPanel();
        timePanel.setBackground(Color.YELLOW);
        timeLabel.setFont(defaultFont);
        timePanel.add(timeLabel);
        add(timePanel, BorderLayout.NORTH);
        
    }
    
    private void makeInputPanel() {
    	
    	 JPanel inputPanel = new JPanel();
    	 
    	 Color backgroundColor = new Color(221, 176, 141);
         inputPanel.setBackground(backgroundColor);
         
         inputPanel.add(inputField, BorderLayout.SOUTH);
         add(inputPanel, BorderLayout.SOUTH);
         
    }
    
    private void addStartActionListener() {
    	
    	inputField.addActionListener(new ActionListener() {
    		
    		
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                boolean isCorrect = false;
                for (Word word : currentWords) {
                    if (textField.getText().equals(word.getName())) {
                    	profileAndScorePanel.increase();
                        word.setY(MainPlayPanel.getHeight());
                        isCorrect = true;
                        profileAndScorePanel.setNormalProfileImage();
                        break;
                    }
                }
                if (!isCorrect) {
                	profileAndScorePanel.decrease();
                	profileAndScorePanel.setSadProfileImage();
                } 
                textField.setText("");
            }
        });
    	
    }
    
    public void startCountDown() {
    	if (beforeGameStartThread == null) {
    		beforeGameStartThread = new BeforeGameStartThread(this, timeLabel, audio);
    		beforeGameStartThread.start();
    	}
    }
    
    public void startGame() {
        if (gameThread == null) {
        	gameThread = new GameThread(this);
        	gameThread.start();
        }
    }
    
    public void startGameTimerThread() {
    	if (gameTimerThread == null) {
    		gameTimerThread = new GameTimerThread(this, timeLabel, audio);
    		gameTimerThread.start();
    	}
    }
    
    
    // 60초가 지나면
    public void gameEnd() {
    	
		if (gameThread != null) {
    		gameThread.interrupt();
    		this.removeAll(); // 화면 정지
    		audio.closeAudio("gameBackground"); // 오디오 종료
    		// 팝업 다이얼로그 띄우기
    		JOptionPane.showMessageDialog(gameFrame, "게임이 종료되었습니다. 결과를 확인하세요!", "Game Ended",
    				JOptionPane.ERROR_MESSAGE);
    		
    		gameFrame.dispose(); // gameFrame 닫기
    		userRankingFrame = new UserRankingFrame(profileAndScorePanel); // 랭킹 창 띄우기
//    		userRankingManagement.saveRanking(GameManagement.name, profileAndScorePanel.getScore());
    	}
		
	}

    public void stopGame() {
    	if(gameThread.getStopFlag() == false) {
    		gameThread.stopGame();
    		inputField.setEditable(false);
    	}
    	if(gameTimerThread.getStopFlag() == false) {
    		gameTimerThread.stopTimer();
    	}
    }
    
    public void resumeGame() {
    	if(gameThread.getStopFlag() == true) {
    		gameThread.resumeGame();
    		inputField.setEditable(true);
    	}
    	if(gameTimerThread.getStopFlag() == true) {
    		gameTimerThread.resumeTimer();
    	}
    }
    
    public void addWord() {
        int x = (int) (Math.random() * (MainPlayPanel.getWidth() - LABEL_WIDTH / 2));
        Word word = new Word(wordList.getWord(), x, 0, Math.random() / 20 + 0.1);
        currentWords.add(word);
        addLabel(word);
    }

    private void addLabel(Word word) {
    	
    	fontRandSize = (int)(Math.random()*10 + 10);
        JLabel label = word.getLabel();
        label.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        label.setLocation(getX(), 0);
        label.setFont(new Font("Jokerman", Font.BOLD, fontRandSize));
        MainPlayPanel.add(label);
        
    }

    public void setWords() {
        Iterator<Word> iterator = currentWords.iterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            word.setY(word.getY() + word.getSpeed());
            JLabel label = word.getLabel();
            label.setLocation((int) (word.getX()), (int) word.getY());
            if (label.getY() >= MainPlayPanel.getHeight()) {
                MainPlayPanel.remove(label);
                iterator.remove();
            }
        }
    }

    class MainPlayPanel extends JPanel {
    	
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);

    		g.drawImage(getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
    	}
    	
        public MainPlayPanel() { }
        
        public Image getBackgroundImage() {
        	
        	if(GameManagement.profile.equals("SpongebobSquarepants")) {
        		icon = new ImageIcon("SpongebobBackground.png");
        		return icon.getImage();
        	}
        	else {
        		icon = new ImageIcon("PatrickStarBackground.png");
        		return icon.getImage();
        	}
        	
        }
       
    }

   
}