package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.Student;
import clientapp.listaobecnosci.Shared.Entities.StudentGroup;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
import clientapp.listaobecnosci.Shared.Helpers.JsonConverter;
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
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddStudentToGroupController implements Initializable {
    @FXML
    ChoiceBox<String> studentIndex;
    @FXML
    ChoiceBox<String> group;
    @FXML
    Label resultMsg;
    private Parent root;
    private ArrayList<StudentGroup> groupList;
    private ArrayList<Student> studentList;
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
    @FXML
    protected void onAddClick() throws Exception {
        Integer groupId = Utils.getGroupIdFromListView(group.getValue(), groupList);

        String selectedStudent = studentIndex.getValue();
        String[] splitedString = selectedStudent.split(" ");

        StudentToGroupVm studendToGroup = new StudentToGroupVm(groupId, splitedString[2]);
        DataHandler<StudentToGroupVm> dh = new DataHandler<StudentToGroupVm>("AssigneStudentToGroup", studendToGroup);
        Utils.sendToServer(dh, resultMsg, "Dodano Studenta do grupy");
    }
    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Utils.switchScene(event, root);
    }
}
