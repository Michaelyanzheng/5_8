import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;


public class ReadFile {

	public static void main(String[] args) {

		File file = new File("test.txt");
		if(file.exists()){
			
			try {
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
				BufferedReader br = new BufferedReader(isr);
				
				String line;
				while((line = br.readLine()) != null){
					System.out.println(line);
				}
				br.close();
				isr.close();
				fis.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			File newFile = new File("newFile.txt");
			
			FileOutputStream fos = new FileOutputStream(newFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("长歌行 （汉乐府）\n");
			bw.write("青青园中葵，朝露待日晞。\n");
			bw.write("阳春布德泽，万物生光辉。\n");
			bw.write("常恐秋节至，焜黄华叶衰。\n");
			bw.write("百川东到海，何时复西归？\n");
			bw.write("少壮不努力，老大徒伤悲。\n");
			
			
			bw.close();
			osw.close();
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
