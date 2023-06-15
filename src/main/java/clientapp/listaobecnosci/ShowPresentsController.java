package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.Period;
import clientapp.listaobecnosci.Shared.Entities.StudentGroup;
import clientapp.listaobecnosci.Shared.Entities.Subject;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
import clientapp.listaobecnosci.Shared.Helpers.JsonConverter;
import clientapp.listaobecnosci.Shared.Helpers.ResponseHandler.ResponseHandler;
import clientapp.listaobecnosci.Shared.ViewModels.GetPeriodsListVm;
import clientapp.listaobecnosci.Shared.ViewModels.PresenceVm;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Klasa kontrolująca szablon dla akcji wyświetl obecność
 * */
public class ShowPresentsController implements Initializable {
    /**
     * Pole wyboru przechowujące grupę
     * */
    @FXML
    ChoiceBox<String> group;
    /**
     * Pole wyboru przechowujące przedmiot
     * */
    @FXML
    ChoiceBox<String> subject;
    /**
     * Pole wyboru przechowujące termin
     * */
    @FXML
    ChoiceBox<String> period;
    /**
     * Etykieta przechowująca komunikat dla użytkownika
     * */
    @FXML
    ListView<String> list;
    private Parent root;
    /**
     * Lista terminów z serwera
     * */
    private ArrayList<Period> periodList;
    /**
     * Lista grup z serwera
     * */
    private ArrayList<StudentGroup> groupList;
    /**
     * Lista przedmiotów z serwera
     * */
    private ArrayList<Subject> subjectList;
    /**
     * Funkcja inicjująca kontroler po całkowitym przetworzeniu jego elementu głównego. Pobiera z serwera listę grup i przedmiotów
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataHandler<ArrayList<StudentGroup>> dh = new DataHandler<ArrayList<StudentGroup>>("GetStudentGroupList", null);
        TypeReference<ResponseHandler<ArrayList<StudentGroup>>> typeReference = new TypeReference<ResponseHandler<ArrayList<StudentGroup>>>() {};
        DataHandler<ArrayList<Subject>> dhs = new DataHandler<ArrayList<Subject>>("GetSubjectsList", null);
        TypeReference<ResponseHandler<ArrayList<Subject>>> typeReference2 = new TypeReference<ResponseHandler<ArrayList<Subject>>>() {};
        try {
            ResponseHandler<ArrayList<StudentGroup>> dataHandler = Utils.getFromServer(dh, typeReference);
            if (dataHandler.isSuccess()) {
                groupList = dataHandler.getData();
                for (StudentGroup gr : groupList) {
                    group.getItems().add(gr.getGroupName());
                }
            }
            ResponseHandler<ArrayList<Subject>> dataHandler2 = Utils.getFromServer(dhs, typeReference2);
            if (dataHandler2.isSuccess()) {
                subjectList = dataHandler2.getData();
                for (Subject sub : subjectList) {
                    subject.getItems().add(sub.getName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    /**
     * Funkcja pobierająca z serwera listę terminów na podstawie wybranej grupy i przedmiotu
     * @throws Exception wyjątek
     * */
    @FXML
    protected void getPeriodList() throws Exception {
        if(subject.getValue() != null && group.getValue() != null){
            Integer groupId = Utils.getGroupIdFromListView(group.getValue(), groupList);
            Integer subjectId = Utils.getSubjectIdFromListView(subject.getValue(), subjectList);

            GetPeriodsListVm periodListVm = new GetPeriodsListVm(groupId, subjectId);
            DataHandler<GetPeriodsListVm> dh = new DataHandler<GetPeriodsListVm>("GetListForGroupAndSubject", periodListVm);
            String json = JsonConverter.convertClassToJson(dh);
            String respond = Utils.connectToServer(json);
            TypeReference<ResponseHandler<ArrayList<Period>>> typeReference = new TypeReference<ResponseHandler<ArrayList<Period>>>() {};
            ResponseHandler<ArrayList<Period>> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
            if (dataHandler.isSuccess()) {
                periodList = dataHandler.getData();
                for (Period per : periodList) {
                    period.getItems().add(per.getDate()+" "+per.getStartTime()+" - "+per.getEndTime());
                }
            }
        }
    }
    /**
     * Funkcja pobierająca z serwera listę obecności studentów na podstawie wybranego terminu i wyświetla ją
     * @throws Exception wyjątek
     * */
    @FXML
    protected void showStudentList() throws Exception {
        Integer periodId = Utils.getPeriodIdFromListView(period.getValue(), periodList);

        DataHandler<Integer> dh = new DataHandler<Integer>("GetPresenceList", periodId);
        String json = JsonConverter.convertClassToJson(dh);
        String respond = Utils.connectToServer(json);
        TypeReference<ResponseHandler<ArrayList<PresenceVm>>> typeReference = new TypeReference<ResponseHandler<ArrayList<PresenceVm>>>() {};
        ResponseHandler<ArrayList<PresenceVm>> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
        if (dataHandler.isSuccess()) {
            ObservableList<String> listElements = FXCollections.observableArrayList();
            for (PresenceVm pres : dataHandler.getData()) {
                listElements.add(pres.getStudentIndex()+" "+pres.getFirstName()+" "+pres.getLastName()+" - "+pres.getStatus());
            }
            list.setItems(listElements);
        }
    }
}
