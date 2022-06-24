package operatingSystem;

public class Process3  extends Thread{
	public static void main(String[] args) {
		for(int i=0;i<=300;i++)
			SysCall.printer(""+i);
	}
}
