package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.StudentGroup;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * Klasa kontrolująca szablon dla akcji dodaj grupę
 *
 * */
public class AddGroupController implements Initializable {
    /**
     * Pole tekstowe przechowujące nazwę grupy
     *
     * */
    @FXML
    TextField groupName;
    /**
     * Etykieta przechowująca komunikat dla użytkownika
     *
     * */
    @FXML
    Label resultMsg;
    private Parent root;
    /**
     * Funkcja inicjująca kontroler po całkowitym przetworzeniu jego elementu głównego.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku zapisu. Wykonuje dodanie grupy.
     * */
    @FXML
    protected void onAddClick() throws Exception {
        StudentGroup group = new StudentGroup(groupName.getText());
        DataHandler<StudentGroup> dh = new DataHandler<StudentGroup>("CreateStudentGroup", group);
        Utils.sendToServer(dh, resultMsg, "Dodano Grupę");
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
