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
 * Klasa kontrolująca szablon dla akcji usuń studenta z grupy
 * */
public class RemoveStudentFromGroupController implements Initializable {
    /**
     * Pole wyboru przechowujące grupę
     * */
    @FXML
    ChoiceBox<String> group;
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
     * Lista grup z serwera
     * */
    private ArrayList<StudentGroup> groupList;
    /**
     * Lista studentów z serwera
     * */
    private ArrayList<Student> studentList;
    /**
     * Funkcja inicjująca kontroler po całkowitym przetworzeniu jego elementu głównego. Pobiera z serwera listę grup
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataHandler<ArrayList<StudentGroup>> dh = new DataHandler<ArrayList<StudentGroup>>("GetStudentGroupList", null);
        TypeReference<ResponseHandler<ArrayList<StudentGroup>>> typeReference = new TypeReference<ResponseHandler<ArrayList<StudentGroup>>>() {};

        try {
            ResponseHandler<ArrayList<StudentGroup>> dataHandler = Utils.getFromServer(dh, typeReference);
            if (dataHandler.isSuccess()) {
                groupList = dataHandler.getData();
                for (StudentGroup gr : groupList) {
                    group.getItems().add(gr.getGroupName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku zapisu. Wykonuje usuwanie studenta z grupy.
     * */
    @FXML
    protected void onDeleteClick() throws Exception {
        String selectedStudent = studentIndex.getValue();
        String[] splitedString = selectedStudent.split(" ");
        Integer groupId = Utils.getGroupIdFromListView(group.getValue(), groupList);

        StudentToGroupVm studendToGroup = new StudentToGroupVm(groupId, splitedString[2]);
        DataHandler<StudentToGroupVm> dh = new DataHandler<StudentToGroupVm>("DeleteStudentFromGroup", studendToGroup);
        Utils.sendToServer(dh, resultMsg, "Usunięto studenta z grupy");
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
    /**
     * Funkcja pobierająca z serwera listę studentów na podstawie wybranej grupy
     * @throws Exception wyjątek
     * */
    @FXML
    protected void getStudents(ActionEvent event) throws IOException {
        if(group.getValue() != null){
            Integer groupId = Utils.getGroupIdFromListView(group.getValue(), groupList);
            DataHandler<Integer> dhs = new DataHandler<Integer>("GetStudentsListForGroup", groupId);
            TypeReference<ResponseHandler<ArrayList<Student>>> typeReference2 = new TypeReference<ResponseHandler<ArrayList<Student>>>() {};

            try {
                ResponseHandler<ArrayList<Student>> dataHandler2 = Utils.getFromServer(dhs, typeReference2);
                if (dataHandler2.isSuccess()) {
                    studentList = dataHandler2.getData();
                    for (Student student : studentList) {
                        studentIndex.getItems().add(student.getFirstName()+" "+student.getLastName()+" "+student.getStudentIndex());
                    }
                }
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
}
