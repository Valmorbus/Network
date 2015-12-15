package bmiServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application {
	DataInputStream instream;
	DataOutputStream outstream;
	InputStreamReader isr;
	TextField weightText;
	TextField heightText;
	Button send; 

	@Override
	public void start(Stage primaryStage) {
		BorderPane bp = new BorderPane();

		Label weight = new Label("weight: ");
		weightText = new TextField();
		Label height = new Label("height: ");
		heightText = new TextField();
		TextArea bmiArea = new TextArea();
		send = new Button("Send");
		VBox vbox = new VBox();
		HBox hbox = new HBox();
		hbox.getChildren().addAll(bmiArea, send);
		vbox.getChildren().addAll(weight, weightText, height, heightText);
		bp.setCenter(vbox);
		bp.setBottom(hbox);

		Scene scene = new Scene(bp);
		primaryStage.setScene(scene);
		primaryStage.setTitle("BMI client");
		primaryStage.show();
		Thread thread = new Thread(() -> {
			connect(bmiArea);
		});
		thread.start();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void connect(TextArea txtArea) {
		Socket socket; // socket slutpunkt i en anslutning. ish vägguttag
		boolean loop = true;

		try {
			socket = new Socket("LocalHosT", 8003); // ip som string
			instream = new DataInputStream(socket.getInputStream());
			outstream = new DataOutputStream(socket.getOutputStream());
			isr = new InputStreamReader(socket.getInputStream());

			while (loop) {
				
				send.setOnAction((e)->{
					String weight = weightText.getText();
					String hight = heightText.getText();
					float weightf;
					float heightf;
					weightf = Float.parseFloat(weight);
					heightf = Float.parseFloat(hight);
					send(weightf);
					send(heightf);

					try {
						txtArea.setText(recive());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void send(float f) {
		try {
			outstream.writeFloat(f);
			outstream.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String recive() throws IOException {
		//float bmi = instream.readFloat();
		//return Float.toString(bmi);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
