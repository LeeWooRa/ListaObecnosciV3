package clientapp.listaobecnosci;


import clientapp.listaobecnosci.Shared.Entities.Subject;
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
 * Klasa kontrolująca szablon dla akcji dodaj przedmiot
 * */
public class AddSubjectController implements Initializable {
    /**
     * Pole tekstowe przechowujące nazwę przedmiotu
     * */
    @FXML
    TextField subjectName;
    /**
     * Etykieta przechowująca komunikat dla użytkownika
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
     * Funkcja wywoływana po kliknięciu przycisku zapisu. Wykonuje dodanie przedmiotu.
     * */
    @FXML
    protected void onAddClick() throws Exception {
        Subject subject = new Subject(null, subjectName.getText());
        DataHandler<Subject> dh = new DataHandler<Subject>("CreateSubject", subject);
        Utils.sendToServer(dh, resultMsg, "Dodano przedmiot");
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
