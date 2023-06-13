package clientapp.listaobecnosci;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    private Label welcomeText;
    private Parent root;

    @FXML
    protected void onAddStudentClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
        Utils.switchScene(event, root);
    }
    @FXML
    protected void onDeletStudentClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeleteStudent.fxml"));
        Utils.switchScene(event, root);
    }
    @FXML
    protected void onAddGroupClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddGroup.fxml"));
        Utils.switchScene(event, root);
    }
    @FXML
    protected void onDeleteGroupClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeleteGroup.fxml"));
        Utils.switchScene(event, root);
    }
    @FXML
    protected void onAddStudentToGroupClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddStudentToGroup.fxml"));
        Utils.switchScene(event, root);
    }
    @FXML
    protected void onRemoveStudentFromGroupClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RemoveStudentFromGroup.fxml"));
        Utils.switchScene(event, root);
    }
    @FXML
    protected void onAddPeriodClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddPeriod.fxml"));
        Utils.switchScene(event, root);
    }
    @FXML
    protected void onDeletePeriodClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeletePeriod.fxml"));
        Utils.switchScene(event, root);
    }
    @FXML
    protected void onCheckPresentsClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CheckPresentsList.fxml"));
        Utils.switchScene(event, root);
    }
    @FXML
    protected void onShowPresentsClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ShowPresents.fxml"));
        Utils.switchScene(event, root);
    }

    @FXML
    protected void onAddSubjectClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddSubject.fxml"));
        Utils.switchScene(event, root);
    }

    @FXML
    protected void onDeleteSubjectClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeleteSubject.fxml"));
        Utils.switchScene(event, root);
    }
}