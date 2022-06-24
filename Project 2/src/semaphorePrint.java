import java.util.LinkedList;
import java.util.Queue;

public class semaphorePrint {
    boolean free;
    Queue<Thread> q;

    public semaphorePrint() {
        free=true;
        q=new LinkedList<>();
    }
    
    public void semPrintWait(Thread t){
        q.add(t);
        while(true) {
        	Thread temp=q.peek();
        	if (free && temp== t){
        		free=false;
        		return;
        		}
        }
    }



    public void semPrintPost() {        try {
    	free=true;  
    	q.remove();       	
	
    
    }
    catch(Exception x) {
    	free=true;
    }
    }

}