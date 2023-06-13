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
        String json;
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

        DataHandler<ArrayList<Subject>> dhs = new DataHandler<ArrayList<Subject>>("GetSubjectsList", null);
        try {
            json = JsonConverter.convertClassToJson(dhs);
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

        for (Integer i = 0; i<24;i++) {
            Time time = new Time(i,0,0);
            startTime.getItems().add(time);
            endTime.getItems().add(time);
        }
    }

    @FXML
    protected void onAddClick() throws Exception {
        String selectedGroupName = group.getValue();
        Integer groupId = -1;
        for (StudentGroup gr : groupList) {
            if(gr.getGroupName() == selectedGroupName){
                groupId = gr.getGroupId();
                break;
            }
        }
        String selectedSubjectName = subject.getValue();
        Integer subjectId = -1;
        for (Subject sub : subjectList) {
            if(sub.getName() == selectedGroupName){
                subjectId = sub.getSubjectId();
                break;
            }
        }
        Period period = new Period(Date.valueOf(periodDate.getValue()), startTime.getValue(), endTime.getValue(), groupId, subjectId);
        DataHandler<Period> dh = new DataHandler<Period>("AssignePeriodToGroup", period);
        String json = JsonConverter.convertClassToJson(dh);
        String respond = Utils.connectToServer(json);
        TypeReference<ResponseHandler<Boolean>> typeReference = new TypeReference<ResponseHandler<Boolean>>() {};
        ResponseHandler<Boolean> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
        if (dataHandler.isSuccess()) {
            resultMsg.setText("Dodano Studenta");
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
