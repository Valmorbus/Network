package fileServer;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static File myFile = new File("C:/Users/Simons/workspace/Network/scr/fileServer/ta.txt");

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8000);
		Socket clientSocket = serverSocket.accept();
		
		DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
		FileOutputStream fos = new FileOutputStream("C:/Users/Simons/workspace/Network/Recive.gif");
		byte[] bArray = new byte[2048];

		int read = 0;
		while((read = dis.read(bArray, 0, bArray.length)) > 0) {
			fos.write(bArray, 0, read);
		}
		
		fos.close();
		dis.close();
	}
}
