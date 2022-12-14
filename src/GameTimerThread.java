import javax.swing.JLabel;

// 게임 타이머 스레드
public class GameTimerThread extends Thread{

	// 생성자에게 전달 위한 레퍼런스 선언
	private Audio audio = null;
	private GamePanel gamePanel = null;
	private JLabel timeLabel = null;
	
	// 게임 시작 후 45초 카운트 다운을 위한 변수
	private int count = 45;
	
	// 게임 일시 중지 확인 Flag 관련 변수 및 메소드
	private boolean stopFlag = false;
	public boolean getStopFlag() { return stopFlag; }
	public void stopTimer() { stopFlag = true; }
	
	// 타이머 이어서 동작하는 메소드
    // 스레드 깨우기
	synchronized public void resumeTimer() { 
		
		stopFlag = false;
		this.notify(); 
		
	}

	// 대기 상태 걸어놓는 메소드
    // 스레드 대기
	synchronized private void waitFlag() {
		
		try { this.wait(); } 
		catch (InterruptedException e) { } 
		
	}
	
	// 생성자
	public GameTimerThread(GamePanel gamePanel, JLabel timeLabel, Audio audio) {
		
		this.gamePanel = gamePanel;
		this.timeLabel = timeLabel;
		this.audio = audio;
		
	}
	
	// 스레드 코드
	@Override
	public void run() {
		
		timeLabel.setText(Integer.toString(count));
		while(true) {
			try {
				sleep(1000);
				if (stopFlag == true) waitFlag();
				count--;
				timeLabel.setText(Integer.toString(count));
				if (count == -1) { 
					timeLabel.setText("게임 종료"); 
					gamePanel.gameEnd(); 
					interrupt();
				}
			} catch (InterruptedException e) { return; }
		}
	}

}
