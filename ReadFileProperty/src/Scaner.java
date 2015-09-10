import java.io.File;


public class Scaner {

	public static void main(String[] args) {

		printFile(new File("C:\\ProgramData\\Microsoft\\NetFramework"),1);
		
	}
	
	public static void printFile(File dir,int tab){
		
		if(dir.isDirectory()){
			
			File next[] = dir.listFiles();
			for (int i = 0; i < next.length; i++) {
				
				for (int j = 0; j < tab; j++) {
					System.out.print("--|");
				}
				System.out.println(next[i].getName());
				if(next[i].isDirectory()){
					printFile(next[i],tab+1);
				}
				
			}
		}
		
	}

}










