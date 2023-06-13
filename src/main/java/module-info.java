module clientapp.listaobecnosci {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens clientapp.listaobecnosci to javafx.fxml;
    exports clientapp.listaobecnosci;
}