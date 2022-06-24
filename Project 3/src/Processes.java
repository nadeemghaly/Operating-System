

import java.io.File;

public class Processes extends Thread {
	int ID;
	
	public Processes(int ID) {
		//super(); 
		this.ID=ID;
	}
	
	
	
	public static void process1(){
		try {
			
			Systemcalls.print("Please enter file name to read");
				String filename=Systemcalls.input();
				File file=new File(filename);
				Systemcalls.read(file);
			}catch(Exception e) {
				Systemcalls.print("Error has occured");
			}
	}
	public static void process2() {
		try {

		Systemcalls.print("Please enter file name to append data to and data");
		String filename=Systemcalls.input()+"";
		File file=new File(filename);
		//Systemcalls.print("Please enter data to append");
		String data=Systemcalls.input();
		Systemcalls.write(file, data);
		}
		catch(Exception e) {
			Systemcalls.print("Error has occured");
		}
	}
	public static void process3(){
		for (int i=0; i<=300;i++) {

			Systemcalls.print(i);	
	}
	}
		public static void process4(){
			for (int i=500;i<=1000;i++) {

				Systemcalls.print(i);
			}
		}
			public static void process5(){
				try {
					Systemcalls.print("Please enter first and second number");
					int x=Integer.parseInt(Systemcalls.input());
					//Systemcalls.print("Please enter second number");
					int y=Integer.parseInt(Systemcalls.input());
					int ln;
					int hn;
					if(x<y) {
						ln=x;
						hn=y;
					}
					else {
						hn=x;
						ln=y;
					}

					
					File file = new File( ln +"to"+ hn + ".txt");
					int i=0;
					while(file.exists()) {
						i++;
						file=new File( ln +"to" + hn + "("+i+")"+".txt");
					}

					if (file.createNewFile())
						Systemcalls.print("File created");
					else
						Systemcalls.print("File already exists");
					

					for (int j = ln; j <= hn; j++) {
						String data = j + "";
						Systemcalls.write(file, data);

					}
				} catch(Exception e) {
					Systemcalls.print("Error has occured");
				}
			
		
		}
	
	
	public void run() {
		
		if (this.ID==1) {
			process1();
		}
		if (this.ID==2) {
			process2();
		}
		if (this.ID==3) {
			process3();
		}
		
		if (this.ID==4) {
			process4();
		}
		if (this.ID==5) {
			process5();
		}
	
	}
	
	
	

}
