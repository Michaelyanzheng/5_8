import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyByByteStream {

	public static void main(String[] args) {

		
		try {
			FileInputStream fis = new FileInputStream("12.mp4");
			FileOutputStream fos = new FileOutputStream("new.mp4");
			
			byte input [] = new byte[50];
			
			long time = System.currentTimeMillis();
			
			int count = 0;
			int len = 0;
			while((len = fis.read(input)) != -1){
				count++;
				fos.write(input,0,len);
			}
			
			System.out.println(System.currentTimeMillis() - time);
			
			System.out.println(count);
			System.out.println("done");
			
			fis.close();
			fos.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
