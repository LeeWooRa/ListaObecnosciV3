package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.Period;
import clientapp.listaobecnosci.Shared.Entities.Student;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;

public class AddPeriodController implements Initializable {
    @FXML
    DatePicker periodDate;
    @FXML
    ChoiceBox<Time> startTime;
    @FXML
    ChoiceBox<Time> endTime;
    @FXML
    ChoiceBox<String> subject;
    @FXML
    ChoiceBox<String> group;
    @FXML
    Label resultMsg;
    private Parent root;
    private ArrayList<StudentGroup> groupList;
    private ArrayList<Subject> subjectList;

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

    @FXML
    protected void onAddClick() throws Exception {
        Integer groupId = Utils.getGroupIdFromListView(group.getValue(), groupList);
        Integer subjectId = Utils.getSubjectIdFromListView(subject.getValue(), subjectList);

        Period period = new Period(Date.valueOf(periodDate.getValue()), startTime.getValue(), endTime.getValue(), groupId, subjectId);
        DataHandler<Period> dh = new DataHandler<Period>("AssignePeriodToGroup", period);
        Utils.sendToServer(dh, resultMsg, "Dodano Termin");
    }
    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Utils.switchScene(event, root);
    }
}
