import javax.swing.JLabel;


class BeforeGameStartThread extends Thread { // Thread 클래스 상속받는다
	
	private GamePanel gamePanel = null;
	private JLabel text = null;
	private Audio audio = null;
	private int count = 3;
		
	public BeforeGameStartThread(GamePanel gamePanel, JLabel timeLabel, Audio audio) {
		
		this.gamePanel = gamePanel;
		this.text = timeLabel;
		this.audio = audio;
		
	}
		
	@Override
	public void run() {
		
		while(true) {
			
			try {
				sleep(1000);
				count--;
				text.setText(Integer.toString(count));
				System.out.println("실행");
				if (count == 0) {
					text.setText("게임 시작");
					interrupt();
				}
			}
			catch (InterruptedException e) {
				gamePanel.startGame();
				gamePanel.startGameTimerThread();
				audio.playAudio("gameBackground");
				return;
			}
		}
	}
	
	
}