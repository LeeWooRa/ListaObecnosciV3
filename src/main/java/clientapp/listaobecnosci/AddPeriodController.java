package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.Period;
import clientapp.listaobecnosci.Shared.Entities.StudentGroup;
import clientapp.listaobecnosci.Shared.Entities.Subject;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
import clientapp.listaobecnosci.Shared.Helpers.ResponseHandler.ResponseHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;
/**
 * Klasa kontrolująca szablon dla akcji dodaj termin
 *
 * */
public class AddPeriodController implements Initializable {
    /**
     * Pole do wyboru daty przechowujące datę dla terminu
     *
     * */
    @FXML
    DatePicker periodDate;
    /**
     * Lista wyboru przechowująca cas startu
     *
     * */
    @FXML
    ChoiceBox<Time> startTime;
    /**
     * Lista wyboru przechowująca cas końca
     *
     * */
    @FXML
    ChoiceBox<Time> endTime;
    /**
     * Lista wyboru przechowująca przedmiot
     *
     * */
    @FXML
    ChoiceBox<String> subject;
    /**
     * Lista wyboru przechowująca grupę
     *
     * */
    @FXML
    ChoiceBox<String> group;
    /**
     * Etykieta przechowująca komunikat dla użytkownika
     *
     * */
    @FXML
    Label resultMsg;
    private Parent root;
    /**
     * Lista grup z serwera
     *
     * */
    private ArrayList<StudentGroup> groupList;
    /**
     * Lista przedmiotów z serwera
     *
     * */
    private ArrayList<Subject> subjectList;
    /**
     * Funkcja inicjująca kontroler po całkowitym przetworzeniu jego elementu głównego. Pobiera z serwera listę przedmiotów i grup
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

        for (Integer i = 0; i<24;i++) {
            Time time = new Time(i,0,0);
            startTime.getItems().add(time);
            endTime.getItems().add(time);
        }
    }
    /**
     * Funkcja wywoływana po kliknięciu przycisku zapisu. Wykonuje dodanie przedmiotu.
     * */
    @FXML
    protected void onAddClick() throws Exception {
        Integer groupId = Utils.getGroupIdFromListView(group.getValue(), groupList);
        Integer subjectId = Utils.getSubjectIdFromListView(subject.getValue(), subjectList);

        Period period = new Period(Date.valueOf(periodDate.getValue()), startTime.getValue(), endTime.getValue(), groupId, subjectId);
        DataHandler<Period> dh = new DataHandler<Period>("AssignePeriodToGroup", period);
        Utils.sendToServer(dh, resultMsg, "Dodano Termin");
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
