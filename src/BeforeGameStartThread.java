import javax.swing.JLabel;

class BeforeGameStartThread extends Thread {
	
	// 생성자에게 전달 위한 레퍼런스 선언
	private GamePanel gamePanel = null;
	private JLabel timeLabel = null;
	private Audio audio = null;
	
	 // 게임 시작 전 3초 카운트 다운을 위한 변수
	private int count = 3;
	
	// 생성자
	public BeforeGameStartThread(GamePanel gamePanel, JLabel timeLabel, Audio audio) {
		
		this.gamePanel = gamePanel;
		this.timeLabel = timeLabel;
		this.audio = audio;
		
	}
	
	// 스레드 코드
	@Override
	public void run() {
		
		while(true) {
			
			try { // 3초 카운트 다운
				sleep(1000);
				count--;
				timeLabel.setText(Integer.toString(count));
				System.out.println("실행");
				if (count == 0) { 
					timeLabel.setText("게임 시작");
					interrupt(); 
				}
			}
			catch (InterruptedException e) { // interrupt 받으면
				gamePanel.startGame(); // 게임 시작 
				gamePanel.startGameTimerThread(); // 게임 타이머 스레드 시작
				audio.playAudio("gameBackground"); // 게임 배경 음악 시작
				return;
			}
		}
	}
	
	
}