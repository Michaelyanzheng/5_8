import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class WriteByteStream {

	public static void main(String[] args) {

		
		try {
			FileOutputStream fos = new FileOutputStream("testw.txt");
			
			String outString = "write 1234 钟衍徵";
			byte output[] = outString.getBytes("utf-8");
			fos.write(output);

			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
