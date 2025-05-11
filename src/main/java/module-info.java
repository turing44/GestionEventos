module org.example.gestioncultural {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.gestioncultural to javafx.fxml;
    exports org.example.gestioncultural;
    exports org.example.gestioncultural.controladores;
    opens org.example.gestioncultural.controladores to javafx.fxml;
    exports org.example.gestioncultural.vista;
    opens org.example.gestioncultural.vista to javafx.fxml;
}