package operatingSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class SysCall {
	public static void main(String []args) {
		Process1 p1= new Process1();
		p1.run();
		Process2 p2= new Process2();
		p2.run();
		Process3 p3= new Process3();
		p3.run();
		Process4 p4= new Process4();
		p4.run();
		Process5 p5= new Process5();
		p5.run();
	}
	public static String reader(String path) {
		 File file = new File(path); 
		  
		  BufferedReader br;
		  String out = "";
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) 
			    out+=st;
			
		} catch (FileNotFoundException e1) {
			System.out.print("file is not found");
			return null;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
		  
	}
	public static void writer(String data,String path) {
		File file = new File(path);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
	}
	
	public static void printer(String data) {
		System.out.println(data);
	}
	
	public static String input() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter text: ");
    	String myString = input.next();
    	input.close();
		return myString;
	}
}
