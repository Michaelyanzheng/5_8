import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ReadByBufferedByteStream {

	public static void main(String[] args) {
		
		try {
			FileInputStream fis = new FileInputStream("12.mp4");
			FileOutputStream fos = new FileOutputStream("new_12.mp4");
			
			BufferedInputStream bis = new BufferedInputStream(fis,100000);
			BufferedOutputStream bos = new BufferedOutputStream(fos,100000);
									
			byte[] input = new byte[100000];
			int count = 0;
			long time = System.currentTimeMillis();
			
			int len = 0;
			while((len = bis.read(input)) != -1){
				
				bos.write(input,0,len);
				count++;
			}
			
			System.out.println(System.currentTimeMillis() - time);
			System.out.println(count);
			bos.flush();
			bos.close();
			bis.close();
			
			fos.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
