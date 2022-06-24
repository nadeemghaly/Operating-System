
import java.io.*;
import java.util.Scanner;

public class Systemcalls {
	
	public static void main(String[] args) {

	}
	

	public static void write(File filename, String data) {
		try {
			FileWriter myWriter = new FileWriter(filename, true);
			myWriter.write(data);
			myWriter.close();
		} catch (IOException e) {
			System.out.print("Error Has Occurred");
		}
	}
	
	
	public static void read(File filename) {
		try {
			
			Scanner x=new Scanner(filename);
			while(x.hasNextLine()){
				String data=x.nextLine();
				print(data);
			}
			x.close();
		} catch (Exception e) {
			System.out.print("Error has occured");
//			e.printStackTrace();
		
	}

		
	}
	

	public static void print (Object a) {
		System.out.println(a);	
	}
	
	public static String input () {
		Scanner inp=  new Scanner(System.in);
		String data = inp.nextLine();
		//inp.close();
		return data;
	}

}
