package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.Student;
import clientapp.listaobecnosci.Shared.Entities.StudentGroup;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
import clientapp.listaobecnosci.Shared.Helpers.ResponseHandler.ResponseHandler;
import clientapp.listaobecnosci.Shared.ViewModels.StudentToGroupVm;
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
 * Klasa kontrolująca szablon dla akcji dodaj studenta do grupy
 * */
public class AddStudentToGroupController implements Initializable {
    /**
     * Pole wyboru przechowujące indeks studenta
     * */
    @FXML
    ChoiceBox<String> studentIndex;
    /**
     * Pole wyboru przechowujące grupę
     * */
    @FXML
    ChoiceBox<String> group;
    /**
     * Etykieta przechowująca komunikat dla użytkownika
     * */
    @FXML
    Label resultMsg;
    private Parent root;
    /**
     * Lista grup z serwera
     * */
    private ArrayList<StudentGroup> groupList;
    /**
     * Lista studentów z serwera
     * */
    private ArrayList<Student> studentList;
    /**
     * Funkcja inicjująca kontroler po całkowitym przetworzeniu jego elementu głównego. Pobiera z serwera listę grup i studentów bez przypisanej grupy
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataHandler<ArrayList<StudentGroup>> dh = new DataHandler<ArrayList<StudentGroup>>("GetStudentGroupList", null);
        TypeReference<ResponseHandler<ArrayList<StudentGroup>>> typeReference = new TypeReference<ResponseHandler<ArrayList<StudentGroup>>>() {};

        DataHandler<ArrayList<Student>> dhs = new DataHandler<ArrayList<Student>>("GetStudentsWithoutGroupList", null);
        TypeReference<ResponseHandler<ArrayList<Student>>> typeReference2 = new TypeReference<ResponseHandler<ArrayList<Student>>>() {};

        try {
            ResponseHandler<ArrayList<StudentGroup>> dataHandler = Utils.getFromServer(dh, typeReference);
            if (dataHandler.isSuccess()) {
                groupList = dataHandler.getData();
                for (StudentGroup gr : groupList) {
                    group.getItems().add(gr.getGroupName());
                }
            }

            ResponseHandler<ArrayList<Student>> dataHandler2 = Utils.getFromServer(dhs, typeReference2);
            if (dataHandler2.isSuccess()) {
                studentList = dataHandler2.getData();
                for (Student student : studentList) {
                    studentIndex.getItems().add(student.getFirstName()+" "+student.getLastName()+" "+student.getStudentIndex());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku zapisu. Wykonuje dodanie studenta do grupy.
     * */
    @FXML
    protected void onAddClick() throws Exception {
        Integer groupId = Utils.getGroupIdFromListView(group.getValue(), groupList);

        String selectedStudent = studentIndex.getValue();
        String[] splitedString = selectedStudent.split(" ");

        StudentToGroupVm studendToGroup = new StudentToGroupVm(groupId, splitedString[2]);
        DataHandler<StudentToGroupVm> dh = new DataHandler<StudentToGroupVm>("AssigneStudentToGroup", studendToGroup);
        Utils.sendToServer(dh, resultMsg, "Dodano Studenta do grupy");
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
