package operatingSystem;
public class Process1 extends Thread {

	public void run() {
		
		String path=SysCall.input();
		
		String data=SysCall.reader(path) ;
		  
		SysCall.printer(data);
	}
}
