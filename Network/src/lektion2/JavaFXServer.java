package lektion2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFXServer extends Application {

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Server");
		TextArea area = new TextArea();
		root.setCenter(area);
		primaryStage.setTitle("Server");
		Thread thread = new Thread(()->{
			connectToClient(area);
		});
		thread.start();

	}

	public static void main(String[] args) {
		launch(args);
	}

	private void connectToClient(TextArea textarea) {

		boolean loop = true;
		ServerSocket serverSocket;
		Platform.runLater(()-> //är det bara en kodrad i lambda behövs inte måsvingar
			textarea.appendText("Server created " + new Date())
		);

		try {
			serverSocket = new ServerSocket(1005);

			Socket clientSocket = serverSocket.accept();
			DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
			while (loop) {
				double radie = inputStream.readDouble();
				double area = radie * radie * Math.PI;
				outputStream.writeDouble(area);
				Platform.runLater(()->{
					textarea.appendText("radie from client " + radie +'\n');
					textarea.appendText("calculated area by server: " +area+'\n');
				});
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}