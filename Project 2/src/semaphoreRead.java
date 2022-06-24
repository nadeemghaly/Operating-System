import java.util.LinkedList;
import java.util.Queue;

public class semaphoreRead {
    boolean flag;
    Queue<Thread> x1;

    public semaphoreRead() {
        flag=true;
        x1=new LinkedList<>();
    }
    
    public void semReadWait(Thread t) {

        x1.add(t);
        while(true) {
        	if (flag && x1.peek()== t){
        		flag=false;
        		return;
        		}
        }

        }



    public void semReadPost() {
        try {
        	flag=true;  
        	x1.remove();       	
 	
        
        }
        catch(Exception x) {
        	flag=true;
        }
    }

}