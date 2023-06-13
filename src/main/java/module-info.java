module clientapp.listaobecnosci {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens clientapp.listaobecnosci to javafx.fxml;
    exports clientapp.listaobecnosci;
    exports clientapp.listaobecnosci.Shared.Helpers.DataHandler;
    exports clientapp.listaobecnosci.Shared.Helpers.ResponseHandler;
    exports clientapp.listaobecnosci.Shared.Helpers;
    exports clientapp.listaobecnosci.Shared.Entities;
    exports clientapp.listaobecnosci.Shared.Enums;
    exports clientapp.listaobecnosci.Shared.ViewModels;
}