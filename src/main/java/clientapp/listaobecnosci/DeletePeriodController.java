package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.Period;
import clientapp.listaobecnosci.Shared.Entities.StudentGroup;
import clientapp.listaobecnosci.Shared.Entities.Subject;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
import clientapp.listaobecnosci.Shared.Helpers.JsonConverter;
import clientapp.listaobecnosci.Shared.Helpers.ResponseHandler.ResponseHandler;
import clientapp.listaobecnosci.Shared.ViewModels.GetPeriodsListVm;
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

public class DeletePeriodController implements Initializable {
    @FXML
    ChoiceBox<String> period;
    @FXML
    ChoiceBox<String> group;
    @FXML
    ChoiceBox<String> subject;
    @FXML
    Label resultMsg;
    private Parent root;
    private ArrayList<Period> periodList;
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
    }
    @FXML
    protected void onDeleteClick() throws Exception {
        Integer periodId = Utils.getPeriodIdFromListView(period.getValue(), periodList);

        DataHandler<Integer> dh = new DataHandler<Integer>("DeletePeriod", periodId);
        Utils.sendToServer(dh, resultMsg, "Usunięto termin");
    }
    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Utils.switchScene(event, root);
    }
    @FXML
    protected void getPeriodList() throws Exception {
        if(subject.getValue() != null && group.getValue() != null){
            Integer groupId = Utils.getGroupIdFromListView(group.getValue(), groupList);
            Integer subjectId = Utils.getSubjectIdFromListView(subject.getValue(), subjectList);

            GetPeriodsListVm periodListVm = new GetPeriodsListVm(groupId, subjectId);
            DataHandler<GetPeriodsListVm> dh = new DataHandler<GetPeriodsListVm>("GetListForGroupAndSubject", periodListVm);
            TypeReference<ResponseHandler<ArrayList<Period>>> typeReference = new TypeReference<ResponseHandler<ArrayList<Period>>>() {};
            try {
                ResponseHandler<ArrayList<Period>> dataHandler = Utils.getFromServer(dh, typeReference);
                if (dataHandler.isSuccess()) {
                    periodList = dataHandler.getData();
                    for (Period per : periodList) {
                        period.getItems().add(per.getDate()+" "+per.getStartTime()+" - "+per.getEndTime());
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
