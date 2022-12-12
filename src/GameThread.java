// 게임 스레드
public class GameThread extends Thread {

	private GamePanel gamePanel = null;
	
	// 게임 일시 중지 확인 Flag 관련 변수 및 메소드
    private boolean stopFlag = false;
    public boolean getStopFlag() { return stopFlag; }
    public void stopGame() { stopFlag = true; }
    
    // 게임 이어서 하는 메소드
    // 스레드 깨우기
    synchronized public void resumeGame() { 
    	
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
    public GameThread(GamePanel gamePanel) {
    	
    	super("GameThread"); 
    	this.gamePanel = gamePanel;
    	
	}
    
    // 스레드 코드
	@Override
    public void run() {
		int count = 0;
        while (true) {
        	try {
        		if (stopFlag == true) waitFlag();
        		if (count % 1000 == 0) gamePanel.addWord();
        		gamePanel.setWords();
        		count++;
        		sleep(1);
        	} catch (InterruptedException e) { return; }
        }
	}
	
}