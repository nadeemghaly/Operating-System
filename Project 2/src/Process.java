//import java.util.concurrent.Semaphore;


public class Process extends Thread {
	
	public int processID;
    ProcessState status=ProcessState.New;	
   static semaphoreRead semR ;
    static semaphorePrint semP ;
   static SemaphoreScanner semS ;
   static SemaphoreWriter semW ;
	
	public Process(int m) {
		processID = m;
		semR=new semaphoreRead();
		semP=new semaphorePrint();
		semS=new SemaphoreScanner();
		semW=new SemaphoreWriter();
	}
	@Override
	public void run() {
		
		switch(processID)
		{
		case 1:process1();break;
		case 2:process2();break;
		case 3:process3();break;
		case 4:process4();break;
		case 5:process5();break;
		}

	}
	
	private void process1() {
		semP.semPrintWait(this);
		OperatingSystem.printText("Enter File Name: ");
		semP.semPrintPost();
		
		semR.semReadWait(this);
		OperatingSystem.printText(OperatingSystem.readFile(OperatingSystem.TakeInput()));
		semR.semReadPost();
		
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process2() {
		semP.semPrintWait(this);
		OperatingSystem.printText("Enter File Name: ");
		semP.semPrintPost();
		
		semS.SemScannerwait(this);
		String filename= OperatingSystem.TakeInput();
		semS.SemScannerpost();
		
		semP.semPrintWait(this);
		OperatingSystem.printText("Enter Data: ");
		semP.semPrintPost();
		
		semS.SemScannerwait(this);
		String data= OperatingSystem.TakeInput();
		semS.SemScannerpost();
		
		semW.SemWritewait(this);
		OperatingSystem.writefile(filename,data);
		semW.SemWritepost();
		
		setProcessState(this,ProcessState.Terminated);
		}
	private void process3() {
		int x=0;
		semP.semPrintWait(this);
		while (x<301)
		{ 
			OperatingSystem.printText(x+"\n");
			x++;
		}
		semP.semPrintPost();
		
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process4() {
	
		int x=500;
		semP.semPrintWait(this);
		while (x<1001)
		{
			OperatingSystem.printText(x+"\n");
			x++;
		}	
		semP.semPrintPost();
		setProcessState(this,ProcessState.Terminated);
		}
	private void process5() {

		semP.semPrintWait(this);
		OperatingSystem.printText("Enter LowerBound: ");
		semP.semPrintPost();
		
		semS.SemScannerwait(this);
		String lower= OperatingSystem.TakeInput();
		semS.SemScannerpost();
		
		semP.semPrintWait(this);
		OperatingSystem.printText("Enter UpperBound: ");
		semP.semPrintPost();

		semS.SemScannerwait(this);
		String upper= OperatingSystem.TakeInput();
		semS.SemScannerpost();
		
		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
		String data="";
		
		while (lowernbr<=uppernbr)
		{
			data+=lowernbr++ +"\n";
		}	
		
		semW.SemWritewait(this);
		OperatingSystem.writefile("P5.txt", data);
		semW.SemWritepost();
		
		setProcessState(this,ProcessState.Terminated);
	}
	
	 public static void setProcessState(Process p, ProcessState s) {
		 p.status=s;
		 if (s == ProcessState.Terminated)
		 {
			 OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
		 }
	}
	 
	 public static ProcessState getProcessState(Process p) {
		 return p.status;
	}
}
