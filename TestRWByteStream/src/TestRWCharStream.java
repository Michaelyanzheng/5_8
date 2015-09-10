import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;


public class TestRWCharStream {

	public static void main(String[] args) {
		
		
		File file = new File("sex.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			
			FileOutputStream fos = new FileOutputStream("sex_new.txt");
			
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			
			char [] input = new char[100];
			int len = 0;
			while((len =isr.read(input)) != -1){
				System.out.println(new String(input,0,len));
//				osw.write(new String(input,0,len));
			}
			
			System.out.println("done");
			osw.close();
			isr.close();
			fos.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
