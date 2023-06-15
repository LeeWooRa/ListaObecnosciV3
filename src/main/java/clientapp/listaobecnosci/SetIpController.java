package clientapp.listaobecnosci;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class SetIpController {
    /**
     * Pole tekstowe przechowujące ip
     *
     * */
    @FXML
    TextField ip;
    /**
     * Pole tekstowe przechowujące port
     *
     * */
    @FXML
    TextField port;
    /**
     * Etykieta przechowująca komunikat dla użytkownika
     * */
    @FXML
    Label resultMsg;
    private Parent root;

    /**
     * Funkcja wywoływana po kliknięciu przycisku zapisu. Wykonuje zapis ustawień.
     * */
    @FXML
    protected void onAddClick() throws Exception {
        Utils.ip = ip.getText();
        Utils.port = Integer.valueOf(port.getText());
        resultMsg.setText("Zapisano ustawienia");
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku powrotu. Przełącza scenę na główny widok
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Utils.switchScene(event, root);
    }
}
