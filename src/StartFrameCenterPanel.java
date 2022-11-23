import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Center에 붙여질 Panel
public class StartFrameCenterPanel extends JPanel {
		
	// 생성자
	public StartFrameCenterPanel() {
		
		setLayout(new FlowLayout()); // 배치관리자 설정
		setBackground(Color.YELLOW); // 배경색 설정
			
		ImageIcon mainImage = new ImageIcon("spongebobMain.png"); // 이미지 로딩
		JLabel mainImageLabel = new JLabel(mainImage); // 레이블 생성
			
		this.add(mainImageLabel); // 패널에 레이블 부착
		
	}
}
