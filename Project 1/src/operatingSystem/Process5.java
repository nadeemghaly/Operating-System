package operatingSystem;
public class Process5  extends Thread{
	public static void main(String[] args) {
		
		int low= Integer.parseInt(SysCall.input());
    	
		int high=  Integer.parseInt(SysCall.input());
    	
    	int i=0;
    	while(low<=high) {
    		low++;
			i++;
    	}
		//fadel el writerSysCall.writer(data, path);
	     
		
	}
}
