package clientapp.listaobecnosci;

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

public class DeleteGroupController implements Initializable {
    @FXML
    ChoiceBox<String> group;
    @FXML
    Label resultMsg;
    private Parent root;
    private ArrayList<StudentGroup> groupList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataHandler<ArrayList<StudentGroup>> dh = new DataHandler<ArrayList<StudentGroup>>("GetStudentGroupList", null);
        String json = null;
        try {
            json = JsonConverter.convertClassToJson(dh);
            String respond = Utils.connectToServer(json);
            TypeReference<ResponseHandler<ArrayList<StudentGroup>>> typeReference = new TypeReference<ResponseHandler<ArrayList<StudentGroup>>>() {};
            ResponseHandler<ArrayList<StudentGroup>> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
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
    @FXML
    protected void onDeleteClick() throws Exception {
        Integer groupId = Utils.getGroupIdFromListView(group.getValue(), groupList);

        DataHandler<Integer> dh = new DataHandler<Integer>("DeleteStudentGroup", groupId);
        Utils.sendToServer(dh, resultMsg, "Usunięto grupę");
    }
    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Utils.switchScene(event, root);
    }

}
