package lektion1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	DataInputStream instream;
	DataOutputStream outstream;
	

	public static void main(String[] args) {
		new Client();

	}
	
	Client(){
		Scanner sc = new Scanner(System.in);
		Socket socket; //socket slutpunkt i en anslutning. ish vägguttag 
		boolean loop = true;
		
		try {
			socket = new Socket("172.20.200.178",1005); //ip som string
			instream = new DataInputStream(socket.getInputStream());
			outstream = new DataOutputStream(socket.getOutputStream());
			
			while(loop){
				String radieString = sc.next();
				double radie = Double.parseDouble(radieString);
				send(radie);
				
			}
			sc.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void send(double d){
		try {
			outstream.writeDouble(d);
			outstream.flush();
			double area = instream.readDouble();
			System.out.println(area + " from server");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
