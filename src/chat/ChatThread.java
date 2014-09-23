package chat;
import java.io.*;
import java.net.*;

public class ChatThread extends Thread {
	String message;
	String hostname;
	String username;
	int portnumber;
	Socket linker;
	
	public ChatThread(String username, String message, String hostname, int portnumber){
		this.username = username;
		this.message = message;
		this.hostname = hostname;
		this.portnumber = portnumber;
	}
	
	public void run(){
		try{
			linker = new Socket(hostname,portnumber);
			System.out.println("now the socket is open. ");
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(linker.getOutputStream()));
			out.write(username + ":" + message);
			out.flush();
			linker.close();
			
		}
		catch(UnknownHostException e){
			System.out.println("The host is not recognized");
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
