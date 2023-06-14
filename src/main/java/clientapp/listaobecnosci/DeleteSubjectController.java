package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.StudentGroup;
import clientapp.listaobecnosci.Shared.Entities.Subject;
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

public class DeleteSubjectController implements Initializable {
    @FXML
    ChoiceBox<String> subject;
    @FXML
    Label resultMsg;
    private Parent root;
    private ArrayList<Subject> subjectList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataHandler<ArrayList<Subject>> dh = new DataHandler<ArrayList<Subject>>("GetSubjectsList", null);
        String json = null;
        try {
            json = JsonConverter.convertClassToJson(dh);
            String respond = Utils.connectToServer(json);
            TypeReference<ResponseHandler<ArrayList<Subject>>> typeReference = new TypeReference<ResponseHandler<ArrayList<Subject>>>() {};
            ResponseHandler<ArrayList<Subject>> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
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
    @FXML
    protected void onDeleteClick() throws Exception {
        String selectedSubjectName = subject.getValue();
        Integer subjectId = -1;
        for (Subject sub : subjectList) {
            if(sub.getName().equals(selectedSubjectName)){
                subjectId = sub.getSubjectId();
                break;
            }
        }
        DataHandler<Integer> dh = new DataHandler<Integer>("DeleteSubject", subjectId);
        String json = JsonConverter.convertClassToJson(dh);
        String respond = Utils.connectToServer(json);
        TypeReference<ResponseHandler<Boolean>> typeReference = new TypeReference<ResponseHandler<Boolean>>() {};
        ResponseHandler<Boolean> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
        if (dataHandler.isSuccess()) {
            resultMsg.setText("Usunięto przedmiot");
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
