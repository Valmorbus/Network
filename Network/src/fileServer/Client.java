package fileServer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	File myFile = new File("C:/Users/Simons/git/HamsterMP3player/mp3Player/Data/Dancinghamster.gif");
	
	public static void main(String[] argv){
	    try {
			new Client();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	Client() throws UnknownHostException, IOException{
		Socket clientSocket = new Socket("127.0.0.1", 8000);
	    DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
		FileInputStream fis = new FileInputStream(myFile);
		byte[] bArray = new byte[2048];
		
		while (fis.read(bArray) > 0) {
			dos.write(bArray);
		}
		
		fis.close();
		dos.close();	
	}
}
