module org.example.gestioncultural {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.gestioncultural to javafx.fxml;
    exports org.example.gestioncultural;
}