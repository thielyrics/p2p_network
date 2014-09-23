package examples;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class asktime {
	public static void main(String [] args) throws Exception {
	    Scanner sc = new Scanner(System.in);
		String machine = sc.nextLine();
		final int daytimeport =8888;
		Socket so = new Socket(machine,daytimeport);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
		String time = br.readLine();
		System.out.println(machine +  " says it is" + time);
		 
	}
}
