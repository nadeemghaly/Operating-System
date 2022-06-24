import java.util.LinkedList;
import java.util.Queue;

public class SemaphoreWriter {
    Boolean free;
    Queue<Thread> q;

    public SemaphoreWriter() {
        free=true;
        q=   new LinkedList<>();
    }
    public void SemWritewait(Thread t){
        q.add(t);
        while(true) {
        	if (free && q.peek()== t){
        		free=false;
        		return;
        		}
        }
    }

    public void SemWritepost(){
        try {
        	free=true;  
        	q.remove();       	
 	
        
        }
        catch(Exception x) {
        	free=true;
        }
        }

}