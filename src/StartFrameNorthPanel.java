import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

// North에 붙여질 Panel
public class StartFrameNorthPanel extends JPanel {
		
	// 생성자
	public StartFrameNorthPanel() {
			
		setLayout(new FlowLayout()); // 배치관리자 설정
		setBackground(Color.YELLOW); // 배경색 설정
			
		ImageIcon mainTextImage = new ImageIcon("spongebobMainText.png"); // 이미지 로딩
		JLabel mainTextImageLabel = new JLabel(mainTextImage); // 레이블 생성
			
		this.add(mainTextImageLabel); // 패널에 레이블 부착
			
	}
}