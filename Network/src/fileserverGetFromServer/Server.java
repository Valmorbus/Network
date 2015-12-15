package fileserverGetFromServer;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server();
	}

	Server() {
		connectToClient();
	}

	private void connectToClient() {
		File file = new File("C:/Users/borgs_000/workspace/Network/src/lektion1/server.txt");

		try (ServerSocket serverSocket = new ServerSocket(8000)) {
			while (true) {
				Socket clientSocket = serverSocket.accept(); // sparar klienten
				byte[] bArray = new byte[(int) file.length()];
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				bis.read(bArray, 0, bArray.length);
				DataOutputStream dataOutput = new DataOutputStream(clientSocket.getOutputStream());
				dataOutput.write(bArray, 0, bArray.length);
				dataOutput.flush();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
