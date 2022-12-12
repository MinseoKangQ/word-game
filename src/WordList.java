import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class WordList {

	private Vector<String> wordVector = new Vector<String>();
	
	public WordList() {
		
		// words.txt가 없으면 catch로 이동해서 처리함
		try {
			Scanner scanner = new Scanner(new FileReader(GameManagement.fileName));
			// 성공적으로 읽혔다면 
			while(scanner.hasNext()) { // 파일 끝까지
				String word = scanner.nextLine(); // \n 빼고 리턴해줌 
				wordVector.add(word);
			}
			scanner.close();
		} catch (FileNotFoundException e) { e.printStackTrace(); }

	}
	
	public String getWord() { // wordVector 중 하나를 random하게 리턴
		
		int index = (int)(Math.random()*wordVector.size());
		return wordVector.get(index);
		
	}
}
