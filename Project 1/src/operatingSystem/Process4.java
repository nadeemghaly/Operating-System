package operatingSystem;

public class Process4  extends Thread {
	public static void main(String[] args) {
		for(int i=500;i<=1000;i++)
			SysCall.printer(""+i);
	}
}
