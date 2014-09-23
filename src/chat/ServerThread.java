package chat;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
	private Socket link;
	private Server server;
	
	public ServerThread(Socket link, Server s){
		this.link = link;
		this.server = s;
	}
	
	public void run() {
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
			System.out.println("About to read incoming message...");
			StringBuilder sb = new StringBuilder();
			
			while (!in.ready()){}
			while (in.ready()) {
				sb.append(in.readLine() + '\n');
			}
			System.out.println("From: " + link.getInetAddress() + ": " + sb);		
			server.addToQueue(sb.toString());
			
			link.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
