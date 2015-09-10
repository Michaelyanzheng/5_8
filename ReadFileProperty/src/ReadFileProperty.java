import java.io.File;


public class ReadFileProperty {

	public static void main(String[] args) {

		
		File file = new File("test.txt");
		
		System.out.println(file.exists());
		System.out.println(file.getName());
		System.out.println(file.getPath());
		System.out.println(file.getAbsolutePath());
		File newFile = new File(file.getAbsolutePath());
		System.out.println(newFile.getParent());
		System.out.println((float)file.length()/1000+"KB");
		System.out.println(file.isHidden());
		System.out.println(file.canWrite());
		System.out.println(file.canRead());
		System.out.println(file.isDirectory());
	}

}














