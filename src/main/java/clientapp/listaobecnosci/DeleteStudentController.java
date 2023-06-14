package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.Student;
import clientapp.listaobecnosci.Shared.Entities.StudentGroup;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
import clientapp.listaobecnosci.Shared.Helpers.JsonConverter;
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

public class DeleteStudentController implements Initializable {
    @FXML
    ChoiceBox<String> studentIndex;
    @FXML
    Label resultMsg;
    private Parent root;
    private ArrayList<Student> studentList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataHandler<ArrayList<Student>> dh = new DataHandler<ArrayList<Student>>("GetStudentsList", null);
        String json = null;
        try {
            json = JsonConverter.convertClassToJson(dh);
            String respond = Utils.connectToServer(json);
            TypeReference<ResponseHandler<ArrayList<Student>>> typeReference = new TypeReference<ResponseHandler<ArrayList<Student>>>() {};
            ResponseHandler<ArrayList<Student>> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
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
    @FXML
    protected void onDeleteClick() throws Exception {
        String selectedStudent = studentIndex.getValue();
        String[] splitedString = selectedStudent.split(" ");
        DataHandler<String> dh = new DataHandler<String>("DeleteStudent", splitedString[2]);
        String json = JsonConverter.convertClassToJson(dh);
        String respond = Utils.connectToServer(json);
        TypeReference<ResponseHandler<Boolean>> typeReference = new TypeReference<ResponseHandler<Boolean>>() {};
        ResponseHandler<Boolean> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
        if (dataHandler.isSuccess()) {
            resultMsg.setText("Usunięto studenta");
        } else {
            resultMsg.setText("Coś poszło nie tak");
        }
    }
    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Utils.switchScene(event, root);
    }
}
