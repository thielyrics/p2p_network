package chat;
import gui.PaintMessage;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class UpdateManager extends Thread {
	Server server;
	String newmessage;
	PaintMessage messagePainter;
	LinkedBlockingQueue<String> outbox;
	String [] computers = {"boromir.cs.hendrix.edu","legolas.cs.hendrix.edu","gimli.cs.hendrix.edu"};

	public UpdateManager(){
		outbox = new LinkedBlockingQueue<String>();
	}
	public void run(){
		try{
			server = new Server(8888);
			server.start();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		while(server.isAlive()) {
						
			applyUpdate();
			
		}		
	}
	
	public void send(String text){
		outbox.add(text);
	}
	
	public void applyUpdate(){
		
		if(!outbox.isEmpty()){
			
			String text = outbox.poll();
			
			try{
				InetAddress myAddress = InetAddress.getLocalHost();
				String hostname = myAddress.getHostName();
				System.out.println(hostname);
			
				for(int i=0;i<computers.length;i++){
						new ChatThread(hostname, text, computers[i], 8888).start();}
				
			}
			
			catch(IOException e){
			e.printStackTrace();
			}
			
		}
		
		if(server.hasNextMessage()){
			
			String text = server.getNextMessage();
			messagePainter.addText(text);
			
		}
		
	}
	
	public void addToQueue(String s){
		server.addToQueue(s);
	}
	
	public void attach(PaintMessage p){
		messagePainter = p;	
	}
	
}