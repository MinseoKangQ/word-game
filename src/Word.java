import javax.swing.JLabel;

public class Word {
	
	// 멤버 변수 선언
	private String name;
    private double x;
    private double y;
    private JLabel label;
    private double speed;
    
    // 생성자
    public Word(String word, int x, int y, double speed) {
    	
        this.name = word;
        this.x = x;
        this.y = y;
        
        label = new JLabel(word);
       
        // 게임 난이도에 따라 단어가 떨어지는 속도 다름
        if (GameManagement.difficulty.equals("Normal"))
        	this.speed = speed + Math.random() * 0.3;
        else if (GameManagement.difficulty.equals("Hard")) 
        	this.speed = speed + (Math.random() * 0.5 + 0.2);
        else 
        	this.speed = speed; 
        
    }

    public String getName() { return name; }

    public double getX() { return x; }

    public double getY() { return y; }

    public double getSpeed() { return speed; }

    public JLabel getLabel() { return label; }

    public void setY(double y) { this.y = y; }
    
}