package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.Subject;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
import clientapp.listaobecnosci.Shared.Helpers.ResponseHandler.ResponseHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Klasa kontrolująca szablon dla akcji usuń przedmiot
 * */
public class DeleteSubjectController implements Initializable {
    /**
     * Pole wyboru przechowujące przedmiot
     * */
    @FXML
    ChoiceBox<String> subject;
    /**
     * Etykieta przechowująca komunikat dla użytkownika
     * */
    @FXML
    Label resultMsg;
    private Parent root;
    /**
     * Lista przedmiotów z serwera
     * */
    private ArrayList<Subject> subjectList;
    /**
     * Funkcja inicjująca kontroler po całkowitym przetworzeniu jego elementu głównego. Pobiera z serwera listę przedmiotów
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataHandler<ArrayList<Subject>> dh = new DataHandler<ArrayList<Subject>>("GetSubjectsList", null);
        TypeReference<ResponseHandler<ArrayList<Subject>>> typeReference = new TypeReference<ResponseHandler<ArrayList<Subject>>>() {};
        try {
            ResponseHandler<ArrayList<Subject>> dataHandler = Utils.getFromServer(dh, typeReference);
            if (dataHandler.isSuccess()) {
                subjectList = dataHandler.getData();
                for (Subject sub : subjectList) {
                    subject.getItems().add(sub.getName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku zapisu. Wykonuje usuwanie przedmiotu.
     * */
    @FXML
    protected void onDeleteClick() throws Exception {
        Integer subjectId = Utils.getSubjectIdFromListView(subject.getValue(), subjectList);

        DataHandler<Integer> dh = new DataHandler<Integer>("DeleteSubject", subjectId);
        Utils.sendToServer(dh, resultMsg, "Usunięto przedmiot");
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
