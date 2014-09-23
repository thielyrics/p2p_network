package examples;

/* HTTP Example:
 * GET http://ozark.hendrix.edu/~ferrer/index.html HTTP/1.1
 * Host: ozark.hendrix.edu
 * [Blank line]
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class SocketThreadDemo extends JFrame {
	private JTextArea output, input;
	private JTextField port, host;
	private JButton openClose, send;
	private Socket sock;
	
	public SocketThreadDemo() throws IOException {
		setSize(900, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		output = new JTextArea(40, 40);
		output.setBorder(BorderFactory.createTitledBorder("Enter message"));
		
		input = new JTextArea(40, 40);
		input.setBorder(BorderFactory.createTitledBorder("View response"));
		input.setEditable(false);
		
		openClose = new JButton("Open connection");
		openClose.addActionListener(new OpenCloser());
		send = new JButton("Send message");
		send.addActionListener(new Sender());
		
		port = new JTextField(10);
		port.setBorder(BorderFactory.createTitledBorder("Port number"));
		
		host = new JTextField(30);
		host.setBorder(BorderFactory.createTitledBorder("Host name"));
		
		getContentPane().setLayout(new BorderLayout());
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		top.add(port);
		top.add(host);
		top.add(openClose);
		top.add(send);
		
		JPanel middle = new JPanel();
		middle.setLayout(new GridLayout(2, 1));
		middle.add(new JScrollPane(output));
		middle.add(new JScrollPane(input));
		
		getContentPane().add(top, BorderLayout.NORTH);
		getContentPane().add(middle, BorderLayout.CENTER);
		
		send.addActionListener(new Sender());
		
		new LocalServer(8888).start();
	}
	
	private class OpenCloser implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (sock == null) {
				int portNum = Integer.parseInt(port.getText());
				String hostName = host.getText();
				try {
					sock = new Socket(hostName, portNum);
					openClose.setText("Close connection");
					input.setText("Connection open");
				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(rootPane, e1.getMessage());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(rootPane, e1.getMessage());
				}
				
			} else {
				try {
					sock.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
				openClose.setText("Open connection");
			}
		}
		
	}
	
	private class Sender implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new MessageThread(output.getText()).start();
		}
	}
	
	private class MessageThread extends Thread {
		private String msg;
		
		public MessageThread(String msg) {
			this.msg = msg;
		}
		
		public void run() {
			try {
				sendOutputTo(sock, msg);
				input.setText("message sent; awaiting response...");
				input.setText(getInputFrom(sock));
				
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}			
		}
	}
	
	private class LocalServer extends Thread {
		private ServerSocket accepter;
		
		public LocalServer(int port) throws IOException {
			accepter = new ServerSocket(port);
		}

		public void run() {
			try {
				for (;;) {
					Socket s = accepter.accept();
					new ServerTalker(s).run();
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Server is toast; bailing out...");
			}
		}
	}
	
	public String getInputFrom(Socket sock) throws IOException {
		BufferedReader responses = 
			new BufferedReader
			(new InputStreamReader(sock.getInputStream()));
		while (!responses.ready()){}
		StringBuilder response = new StringBuilder();
		while (responses.ready()) {
			response.append(responses.readLine() + "\n");
		}
		return response.toString();
	}
	
	public void sendOutputTo(Socket sock, String msg) throws IOException {
		PrintWriter writer = new PrintWriter(sock.getOutputStream());
		writer.print(msg);
		writer.flush();
	}
	
	private class ServerTalker extends Thread {
		private Socket sock;
		
		public ServerTalker(Socket s) {
			sock = s;
		}

		public void run() {
			try {
				String incoming = getInputFrom(sock);
				input.setText(incoming);
				
				sendOutputTo(sock, "Your message was " + incoming.length() + " characters");
				sock.close();
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Server socket is toast; bailing out...");
			}

		}
	}
	
	public static void main(String[] args) throws IOException {
		new SocketThreadDemo().setVisible(true);
	}
}