package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Helpers.JsonConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import clientapp.listaobecnosci.Shared.Entities.Student;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
import clientapp.listaobecnosci.Shared.Enums.ServerAction;
import java.io.IOException;

public class AddStudentController {
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField index;
    @FXML
    ChoiceBox group;
    private Parent root;

    @FXML
    protected void onAddClick() throws Exception {
        Student student = new Student(firstName.getText(), lastName.getText(), index.getText(), (Integer) group.getValue());
        DataHandler<Student> dh = new DataHandler<Student>("CreateStudent", student);
        String json = JsonConverter.convertClassToJson(dh);
        String respond = Utils.connectToServer(json);
    }

    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Utils.switchScene(event, root);
    }
}
