package clientapp.listaobecnosci;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class RemoveStudentFromGroupController {
    @FXML
    ChoiceBox group;
    @FXML
    ChoiceBox studentIndex;
    private Parent root;

    @FXML
    protected void onDeleteClick() {

    }
    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Utils.switchScene(event, root);
    }
}
