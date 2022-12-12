import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartFrameNorthPanel extends JPanel {
		
	// 생성자
	public StartFrameNorthPanel() {
			
		setLayout(new FlowLayout());
		setBackground(Color.YELLOW);
			
		ImageIcon mainTextImage = new ImageIcon("image/main/spongebobMainText.png");
		JLabel mainTextImageLabel = new JLabel(mainTextImage);
		add(mainTextImageLabel);
			
	}
}