package chat;
import gui.BuildGUI;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JTextArea;


public class Server extends Thread {
	
	private ServerSocket serversocket;
	ServerThread serverthread;
	JTextArea textArea;
	BuildGUI gui;
	LinkedBlockingQueue<String> queue;
	
	public Server(int port) throws IOException {
		queue = new LinkedBlockingQueue<String>();
			serversocket = new ServerSocket(port);
			System.out.println("Server IP address is: "+ serversocket.getInetAddress());
		
	}
	
	public void run(){
		for(;;){
			try{
				Socket newSocket = serversocket.accept();
				serverthread = new ServerThread(newSocket, this);
				serverthread.start();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void addToQueue(String text){
		queue.add(text);
	}
	
	public boolean hasNextMessage(){
		
		return !(queue.isEmpty());
		
	}
	
	public String getNextMessage(){
		
		return queue.poll();
		
	}
	
	
}
