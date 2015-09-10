import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ReadByteStream {

	public static void main(String[] args) {

		
		try {
			FileInputStream fis = new FileInputStream("test.txt");
			byte [] input = new byte[21];
			fis.read(input);
			
			String inputString = new String(input,"utf-8");
			System.out.println(inputString);
			
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
