package clientapp.listaobecnosci;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

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
    protected void onAddClick() {

    }

    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Utils.switchScene(event, root);
    }
}
