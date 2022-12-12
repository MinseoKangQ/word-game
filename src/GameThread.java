 
public class GameThread extends Thread {

	private GamePanel gamePanel = null;
    private boolean stopFlag = false;
    public boolean getStopFlag() { return stopFlag; }
    
    public void stopGame() { stopFlag = true; }
    
    synchronized public void resumeGame() { 
		stopFlag = false;
		this.notify();
	}
    
    synchronized private void waitFlag() {
		try { this.wait(); } 
		catch (InterruptedException e) { } 
	}
    
    // 생성자
    public GameThread(GamePanel gamePanel) {
    	
 	   super("GameThread"); 
		this.gamePanel = gamePanel;
	}
    
	@Override
       public void run() {
    	   int count = 0;
           while (true) {
               try {
            	   if (stopFlag == true)
						waitFlag();
            	   
                   if (count % 1000 == 0) {
                	   gamePanel.addWord();
                   }
                   gamePanel.setWords();
                   count++;
                   sleep(1);
                   
                   
               } catch (InterruptedException e) { return; }
            }
        }
    }