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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddGroupController implements Initializable {
    @FXML
    TextField groupName;
    @FXML
    Label resultMsg;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    protected void onAddClick() throws Exception {
        StudentGroup group = new StudentGroup(groupName.getText());
        DataHandler<StudentGroup> dh = new DataHandler<StudentGroup>("CreateStudentGroup", group);
        String json = JsonConverter.convertClassToJson(dh);
        String respond = Utils.connectToServer(json);
        TypeReference<ResponseHandler<Boolean>> typeReference = new TypeReference<ResponseHandler<Boolean>>() {};
        ResponseHandler<Boolean> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
        if (dataHandler.isSuccess()) {
            resultMsg.setText("Dodano Grupę");
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
