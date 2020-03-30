module org.datavelger {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.datavelger to javafx.fxml;
    exports org.datavelger;
}