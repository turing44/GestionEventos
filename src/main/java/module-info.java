module org.example.gestioncultural {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.gestioncultural to javafx.fxml;
    exports org.example.gestioncultural;
    exports org.example.gestioncultural.controladores;
    opens org.example.gestioncultural.controladores to javafx.fxml;
    exports org.example.gestioncultural.utilidades;
    opens org.example.gestioncultural.utilidades to javafx.fxml;
}