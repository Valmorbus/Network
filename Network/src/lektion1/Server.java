package lektion1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) {
		new Server();

	}

	public Server() {
		super();
		connectToClient();
	}
	
	private void connectToClient(){
		ServerSocket serverSocket;
		boolean loop = true;
		
			try {
				serverSocket = new ServerSocket(8000); //lyssnar på port 8000
				System.out.println("connection made " + new Date());
				
				Socket clientSocket = serverSocket.accept(); //sparar klienten
				DataOutputStream dataOutput = new DataOutputStream(clientSocket.getOutputStream());
				DataInputStream dataInput = new DataInputStream(clientSocket.getInputStream());
				
				
				while (loop){
					double radie = dataInput.readDouble();
					double area = radie*radie*Math.PI;
					dataOutput.writeDouble(area);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

	
	
}
