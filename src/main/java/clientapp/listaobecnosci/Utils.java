package clientapp.listaobecnosci;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Utils {
    public static void switchScene(ActionEvent event, Parent root) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static String connectToServer(String jsonToSend){
        String json = "";

        try (Socket socket = new Socket("localhost", 1234)) {

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // sending the user input to server
            out.println(jsonToSend);
            out.flush();

            json = in.readLine();
            // displaying server reply
            System.out.println("Server replied "
                    + json);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
