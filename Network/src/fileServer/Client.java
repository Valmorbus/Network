package fileServer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class Client {
	static File myFile = new File("C:/Users/Simons/git/HamsterMP3player/mp3Player/Data/Dancinghamster.gif");
	
	public static void main(String[] argv) throws Exception {
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
