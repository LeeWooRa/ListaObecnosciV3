package clientapp.listaobecnosci;

import clientapp.listaobecnosci.Shared.Entities.Period;
import clientapp.listaobecnosci.Shared.Entities.StudentGroup;
import clientapp.listaobecnosci.Shared.Entities.Subject;
import clientapp.listaobecnosci.Shared.Helpers.DataHandler.DataHandler;
import clientapp.listaobecnosci.Shared.Helpers.JsonConverter;
import clientapp.listaobecnosci.Shared.Helpers.ResponseHandler.ResponseHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Klasa zawierająca często używane funkcje
 * */
public class Utils {
    public static String ip = "localhost";
    public static Integer port = 1234;
    /**
     * Funkcja do zmieniania sceny po kliknięciu w przycisk
     * @param event zdarzenie z którego wyciągnięte zostanie okno
     * @param root załądowany szablon z pliku .fxml
     * */
    public static void switchScene(ActionEvent event, Parent root) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Funkcja nawiązująca komunikacje z serverem
     * @param jsonToSend wiadomość jaka ma zostać wysłana
     * @return odpowiedź srwera
     * */
    public static String connectToServer(String jsonToSend){
        String json = "";

        try (Socket socket = new Socket(ip, port)) {

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(jsonToSend);
            out.flush();

            json = in.readLine();

            System.out.println("Server replied "+ json);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
    /**
     * Funkcja przetwarzająca wysłanie wiadomości do serwera z poleceniem modyfikacji damych
     * @param dh DataHandler przechowujący obiekt do modyfikacji i typ akcji
     * @param msg Etykieta w której pojawi się komunikat o sukcesie/błędzie
     * @param successMsg Wiadomość do wyświetlenia po powodzeniu
     * */
    public static void sendToServer(DataHandler<?> dh, Label msg, String successMsg) throws Exception {
        String json = JsonConverter.convertClassToJson(dh);
        String respond = Utils.connectToServer(json);
        TypeReference<ResponseHandler<Boolean>> typeReference = new TypeReference<ResponseHandler<Boolean>>() {};
        ResponseHandler<Boolean> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);
        if (dataHandler.isSuccess()) {
            msg.setText(successMsg);
        } else {
            msg.setText("Coś poszło nie tak");
        }
    }
    /**
     * Funkcja przetwarzająca wysłanie wiadomości do serwera z poleceniem przysłania danych
     * @param dh DataHandler przechowujący typ akcji
     * @param typeReference TypeReference przechowujący typ jaki zostanie zwrócony
     * @return ResponseHandler przechowujący dane z przysłane serwera
     * */
    public static <T> ResponseHandler<T> getFromServer(DataHandler<?> dh, TypeReference<ResponseHandler<T>> typeReference) throws Exception {
        String json = JsonConverter.convertClassToJson(dh);
        String respond = Utils.connectToServer(json);
        ResponseHandler<T> dataHandler = JsonConverter.convertJsonToClass(respond, typeReference);

        return dataHandler;
    }
    /**
     * Funkcja wybierająca id terminu z listy na podstawie wyboru użytkownika
     * @param selectedPeriod etykieta terminu wybranego przez użytkownika
     * @param periodList lista terminów
     * @return id terminu
     * */
    public static Integer getPeriodIdFromListView(String selectedPeriod, ArrayList<Period> periodList){
        Integer periodId = null;
        for (Period per : periodList) {
            String test = per.getDate()+" "+per.getStartTime()+" - "+per.getEndTime();
            if(test.equals(selectedPeriod)){
                periodId = per.getPeriodId();
                break;
            }
        }
        return periodId;
    }
    /**
     * Funkcja wybierająca id grupy z listy na podstawie wyboru użytkownika
     * @param selectedGroupName etykieta grupy wybranej przez użytkownika
     * @param groupList lista grup
     * @return id grupy
     * */
    public static Integer getGroupIdFromListView(String selectedGroupName, ArrayList<StudentGroup> groupList){
        Integer groupId = null;
        for (StudentGroup gr : groupList) {
            if(gr.getGroupName().equals(selectedGroupName)){
                groupId = gr.getGroupId();
                break;
            }
        }

        return groupId;
    }
    /**
     * Funkcja wybierająca id przedmiotu z listy na podstawie wyboru użytkownika
     * @param selectedSubjectName etykieta przedmiotu wybranego przez użytkownika
     * @param subjectList lista przedmiotów
     * @return id przedmiotów
     * */
    public static Integer getSubjectIdFromListView(String selectedSubjectName, ArrayList<Subject> subjectList){
        Integer subjectId = null;
        for (Subject sub : subjectList) {
            if(sub.getName().equals(selectedSubjectName)){
                subjectId = sub.getSubjectId();
                break;
            }
        }
        return subjectId;
    }
}
