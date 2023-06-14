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
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckPresentsController implements Initializable {
    @FXML
    ChoiceBox<String> group;
    @FXML
    ChoiceBox<String> subject;
    @FXML
    ChoiceBox<String> period;
    @FXML
    ListView<SplitMenuButton> list;
    private Parent root;
    private ArrayList<Period> periodList;
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
    }

    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Utils.switchScene(event, root);
    }

    @FXML
    protected void getPeriodList() throws Exception {
        if(subject.getValue() != null && group.getValue() != null){
            String selectedGroupName = group.getValue();
            Integer groupId = -1;
            for (StudentGroup gr : groupList) {
                if(gr.getGroupName().equals(selectedGroupName)){
                    groupId = gr.getGroupId();
                    break;
                }
            }
            String selectedSubjectName = subject.getValue();
            Integer subjectId = -1;
            for (Subject sub : subjectList) {
                if(sub.getName().equals(selectedSubjectName)){
                    subjectId = sub.getSubjectId();
                    break;
                }
            }

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

    @FXML
    protected void showStudentList() throws Exception {
        String selectedPeriod = period.getValue();
        Integer periodId = -1;
        for (Period per : periodList) {
            if((per.getDate() + " " + per.getStartTime() + " - " + per.getEndTime()).equals(selectedPeriod)){
                periodId = per.getPeriodId();
                break;
            }
        }
        String selectedGroupName = group.getValue();
        Integer groupId = -1;
        for (StudentGroup gr : groupList) {
            if(gr.getGroupName().equals(selectedGroupName)){
                groupId = gr.getGroupId();
                break;
            }
        }
        DataHandler<Integer> dh = new DataHandler<Integer>("getPresenceList", periodId);
        String json = JsonConverter.convertClassToJson(dh);
        String respond = Utils.connectToServer(json);
        TypeReference<ResponseHandler<ArrayList<PresenceVm>>> typeReference = new TypeReference<ResponseHandler<ArrayList<PresenceVm>>>() {};
        ResponseHandler<ArrayList<PresenceVm>> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
        if (dataHandler.isSuccess()) {
            MenuItem choice1 = new MenuItem("Obecny");
            MenuItem choice2 = new MenuItem("Nieobecny");
            MenuItem choice3 = new MenuItem("Usprawiedliwiony");
            MenuItem choice4 = new MenuItem("Spóźniony");
            ObservableList<SplitMenuButton> listElements = FXCollections.observableArrayList();
            for (PresenceVm pres : dataHandler.getData()) {
                SplitMenuButton splitMenuButton = new SplitMenuButton();
                splitMenuButton.setText(pres.getStudentIndex()+" "+pres.getFirstName()+" "+pres.getLastName());
                splitMenuButton.getItems().addAll(choice1, choice2, choice3);
                listElements.add(splitMenuButton);
            }
            list.setItems(listElements);
        }
    }
}
