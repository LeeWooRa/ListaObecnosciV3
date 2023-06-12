module clientapp.listaobecnosci {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens clientapp.listaobecnosci to javafx.fxml;
    exports clientapp.listaobecnosci;
}