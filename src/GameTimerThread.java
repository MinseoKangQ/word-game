import javax.swing.JLabel;

public class GameTimerThread extends Thread{

	private boolean stopFlag = false;
	public boolean getStopFlag() { return stopFlag; }
	public void stopTimer() { stopFlag = true; }
	
	synchronized public void resumeTimer() { 
		stopFlag = false;
		this.notify(); 
	}

	synchronized private void waitFlag() {
		try { this.wait(); } 
		catch (InterruptedException e) { } 
	}
	
	private Audio audio = null; // 빼도 될듯?
	private GamePanel gamePanel = null;
	private JLabel timeLabel = null;
	
	private int count = 60;
		
	public GameTimerThread(GamePanel gamePanel, JLabel timeLabel, Audio audio) {
		
		this.gamePanel = gamePanel;
		this.timeLabel = timeLabel;
		this.audio = audio;
		
	}

	public int getCount() { return count; }
		
	@Override
	public void run() {
		timeLabel.setText(Integer.toString(count));
		while(true) {
			try {
				sleep(1000);
				if (stopFlag == true)
					waitFlag();
				count--;
				timeLabel.setText(Integer.toString(count));
				if (count == -1) { timeLabel.setText("게임 종료"); gamePanel.gameEnd(); interrupt();}
			}
			catch (InterruptedException e) { return; }
				
		}
	}
	
	
}
