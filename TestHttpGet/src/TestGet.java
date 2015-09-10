import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestGet {

	public static void main(String[] args) {
		
		String urlString = "http://fanyi.youdao.com/openapi.do?keyfrom=zhengwen&key=1318083984&type=data&doctype=xml&version=1.1&q=love";
		
		new MyThread(urlString).start();
	}
}

class MyThread extends Thread{
	
	public String uriString = "";
	
	public MyThread(String uriString) {
		this.uriString = uriString;
	}
	
	@Override
	public void run() {
		
		try {
			URL url = new URL(uriString);
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			PrintWriter printWriter = new PrintWriter(new File("love.xml"));
			
			String line = "";
			StringBuffer result = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
				printWriter.write(line+"\n");
			}
			System.out.println(result.toString());
			
			printWriter.flush();
			printWriter.close();
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}





