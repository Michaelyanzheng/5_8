import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class RWByBufferedCharStream {

public static void main(String[] args) {
		
		
		File file = new File("sex.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			
			FileOutputStream fos = new FileOutputStream("sex_new.txt");
			
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			
			BufferedReader br = new BufferedReader(isr);
			BufferedWriter bw = new BufferedWriter(osw, 1000);
			
			PrintWriter pw = new PrintWriter(osw,true);
			
			String input = null;
			while((input = br.readLine()) != null){
				
				pw.println(input);
			}
			pw.close();
			br.close();
			bw.close();
			
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
