import java.util.LinkedList;
import java.util.Queue;

public class SemaphoreScanner {
    Boolean free;
    Queue<Thread> q;

    public SemaphoreScanner() {
        free=true;
        q=   new LinkedList<>();
    }
    public void SemScannerwait(Thread t){
        q.add(t);
        while(true) {
        	if (free && q.peek()== t){
        		free=false;
        		return;
        		}
        }
    }
    public void SemScannerpost(){
    	try {
    	free=true;  
    	q.remove();       	
	
    
    }
    catch(Exception x) {
    	free=true;
    }
        }
}
