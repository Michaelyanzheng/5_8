import java.io.File;


public class SetFileProperty {
	
	public static void main(String[] args) {
		
		
		File file = new File("test.txt");
		
		
		if(file.exists()){
			
//			file.setWritable(true);
//			file.setReadable(true);
			;
			;
			System.out.println(file.setWritable(false));
			System.out.println(file.setReadable(false));
			System.out.println("false");
		}
	}

}






















