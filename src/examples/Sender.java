package examples;
import java.io.*;
import java.net.*;

public class Sender extends Thread {
	String username;
	String message;
	String hostname;
	int portNumber;
	Socket throughThisSocket;
	
	public Sender(String hostname, String username, String message, int portNumber ) {
		this.hostname = hostname;
		this.username = username;
		this.message = message;
		this.portNumber = portNumber;
		
	}
	
	public void run()
	{
		try{
			throughThisSocket = new Socket(hostname, portNumber);
			DataOutputStream output = new DataOutputStream(throughThisSocket.getOutputStream());
			output.writeUTF(message);
			output.flush();
			throughThisSocket.close();
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
