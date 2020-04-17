module org.datavelger {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.datavelger to javafx.fxml;
    opens org.datavelger.classes to javafx.base;
    exports org.datavelger;
}