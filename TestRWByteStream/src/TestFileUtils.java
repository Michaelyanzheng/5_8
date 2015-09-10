import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class TestFileUtils {

	public static void main(String[] args) {
		
		File file = new File("sex12.txt");
		
		File newFile = new File("sex34.txt");
		try {
//			String input = FileUtils.readFileToString(file,"utf-8");
//			System.out.println(input);
			
			FileUtils.copyFile(file, newFile);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
