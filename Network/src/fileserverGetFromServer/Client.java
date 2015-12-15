package fileserverGetFromServer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
	FileInputStream fileInput;
	FileOutputStream fileOutput;
	File file = new File("C:/Users/borgs_000/workspace/Network/src/lektion1/client.txt");
	Socket socket;

	public static void main(String[] args) {
		new Client();
	}

	Client() {
		try {
			socket = new Socket("LocalHost", 8000);
			byte[] bArray = new byte[1024]; 
			InputStream is = socket.getInputStream();
			
			fileOutput = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fileOutput);
			int bytesRead = is.read(bArray, 0, bArray.length);
			bos.write(bArray,0,bytesRead);
			bos.close();
			socket.close();
			
			fileInput = new FileInputStream(file);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
