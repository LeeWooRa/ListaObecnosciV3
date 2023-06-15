package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.Student;
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
 * Klasa kontrolująca szablon dla akcji usuń studenta
 * */
public class DeleteStudentController implements Initializable {
    /**
     * Pole wyboru przechowujące indeks studenta
     * */
    @FXML
    ChoiceBox<String> studentIndex;
    /**
     * Etykieta przechowująca komunikat dla użytkownika
     * */
    @FXML
    Label resultMsg;
    private Parent root;
    /**
     * Lista studentów z serwera
     * */
    private ArrayList<Student> studentList;
    /**
     * Funkcja inicjująca kontroler po całkowitym przetworzeniu jego elementu głównego. Pobiera z serwera listę studentów
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataHandler<ArrayList<Student>> dh = new DataHandler<ArrayList<Student>>("GetStudentsList", null);
        TypeReference<ResponseHandler<ArrayList<Student>>> typeReference = new TypeReference<ResponseHandler<ArrayList<Student>>>() {};
        try {
            ResponseHandler<ArrayList<Student>> dataHandler = Utils.getFromServer(dh, typeReference);
            if (dataHandler.isSuccess()) {
                studentList = dataHandler.getData();
                for (Student student : studentList) {
                    studentIndex.getItems().add(student.getFirstName()+" "+student.getLastName()+" "+student.getStudentIndex());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku zapisu. Wykonuje usuwanie studenta.
     * */
    @FXML
    protected void onDeleteClick() throws Exception {
        String selectedStudent = studentIndex.getValue();
        String[] splitedString = selectedStudent.split(" ");
        DataHandler<String> dh = new DataHandler<String>("DeleteStudent", splitedString[2]);
        Utils.sendToServer(dh, resultMsg, "Usunięto studenta");
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
