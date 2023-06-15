package clientapp.listaobecnosci;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

/**
 * Klasa kontrolująca szablon dla widoku głównego
 * */
public class HomeController {
    private Parent root;
    /**
     * Funkcja wywoływana po kliknięciu przycisku dodaj studenta. Przełącza scenę na dodaj studenta
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onAddStudentClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku usuń studenta. Przełącza scenę na usuń studenta
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onDeletStudentClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeleteStudent.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku dodaj grupę. Przełącza scenę na dodaj grupę
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onAddGroupClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddGroup.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku usuń grupę. Przełącza scenę na usuń grupę
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onDeleteGroupClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeleteGroup.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku dodaj studenta do grupy. Przełącza scenę na dodaj studenta do grupy
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onAddStudentToGroupClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddStudentToGroup.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku usuń studenta z grupy. Przełącza scenę na usuń studenta z grupy
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onRemoveStudentFromGroupClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RemoveStudentFromGroup.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku dodaj termin. Przełącza scenę na dodaj termin
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onAddPeriodClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddPeriod.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku usuń termin. Przełącza scenę na usuń termin
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onDeletePeriodClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeletePeriod.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku sprawdź obecność. Przełącza scenę na sprawdź obecność
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onCheckPresentsClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CheckPresents.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku wyświetl obecność. Przełącza scenę na wyświetl obecność
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onShowPresentsClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ShowPresents.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku dodaj przedmiot. Przełącza scenę na dodaj przedmiot
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onAddSubjectClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddSubject.fxml"));
        Utils.switchScene(event, root);
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku usuń przedmiot. Przełącza scenę na usuń przedmiot
     * @param event zdarzenie kliknięcia w przycisk
     * */
    @FXML
    protected void onDeleteSubjectClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeleteSubject.fxml"));
        Utils.switchScene(event, root);
    }
}