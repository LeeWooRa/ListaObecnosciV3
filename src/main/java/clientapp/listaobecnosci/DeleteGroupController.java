package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.StudentGroup;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
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

/**
 * Klasa kontrolująca szablon dla akcji usuń grupę
 * */
public class DeleteGroupController implements Initializable {
    /**
     * Pole wyboru przechowujące grupę
     * */
    @FXML
    ChoiceBox<String> group;
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
     * Funkcja wywoływana po kliknięciu przycisku zapisu. Wykonuje usuwanie grupy.
     * */
    @FXML
    protected void onDeleteClick() throws Exception {
        Integer groupId = Utils.getGroupIdFromListView(group.getValue(), groupList);

        DataHandler<Integer> dh = new DataHandler<Integer>("DeleteStudentGroup", groupId);
        Utils.sendToServer(dh, resultMsg, "Usunięto grupę");
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

}
