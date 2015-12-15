package bmiServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Server extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane bp = new BorderPane();
		
		Label bmi = new Label("BMI: ");
		TextField bmiText = new TextField();
		VBox vbox = new VBox();
		vbox.getChildren().addAll(bmi, bmiText);
		bp.setCenter(vbox);
		
		Scene scene = new Scene(bp);
		primaryStage.setScene(scene);
		primaryStage.setTitle("BMI Server");
		primaryStage.show();
		Thread thread = new Thread(()->{
			connectToClient(bmiText);
		});
		thread.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
	private void connectToClient(TextField bmiText){
		try {
			ServerSocket server = new ServerSocket(8003);
			Platform.runLater(()->{
				System.out.println("connectionEstablished");
			});
			Socket clientSocket = server.accept();
			System.out.println("connected");
			System.out.println(clientSocket.getRemoteSocketAddress().toString()); //getIP
			float weight = 0, lenght = 0; 
			float bmi = 0;
			
			//DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
			//OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
			PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
			
			while (true){
				weight = dis.readFloat();
				lenght = dis.readFloat();
				bmi = calculatebmi(weight, lenght);
				//dos.writeFloat(bmi);
				//dos.flush();
				pw.println(result(bmi) + " " + "BMI på hela jävla " + bmi);
				pw.flush();
				
				Platform.runLater(()->{
					bmiText.setText("sent data");
				});
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private float calculatebmi(float weight, float length){
		float bmi = (weight)/((length*length)*0.0001f);
		return bmi;
	}
	private String result(float bmi){
		if (bmi <= 18.5)
			return "undervikt";
		else if (bmi <= 24.9)
			return "normalvikt";
		else if (bmi <= 29.9)
			return "övervikt";
		else 
			return "för fet för ett fuck";
	}
	
	
}
