package operatingSystem;

public class Process2  extends Thread {
public void run() {
		
		String path=SysCall.input();
    	
		String data=SysCall.input();
		
		SysCall.writer(data, path);
		  
	}
}
