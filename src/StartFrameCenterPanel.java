import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartFrameCenterPanel extends JPanel {
		
	// 생성자
	public StartFrameCenterPanel() {
		
		setLayout(new FlowLayout()); 
		setBackground(Color.YELLOW);
			
		ImageIcon mainImage = new ImageIcon("image/main/mainImage.png");
		JLabel mainImageLabel = new JLabel(mainImage);
		add(mainImageLabel);
		
	}
}
