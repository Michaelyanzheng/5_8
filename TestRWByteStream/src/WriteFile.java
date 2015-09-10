import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class WriteFile extends Thread {
	
	File file;
	int block;
	
	int L = 100;
	
	
	public WriteFile(File file,int block){
		this.file = file;
		this.block = block;
	}

	public void run(){
		try {
			RandomAccessFile raf = new RandomAccessFile(this.file, "rw");
			
			raf.seek((this.block-1)*L);
			raf.writeBytes("this is a block"+block);
			raf.writeBytes("\n");
			
			for(int i = 0; i < 10; i++){
				
				raf.writeBytes("-");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
