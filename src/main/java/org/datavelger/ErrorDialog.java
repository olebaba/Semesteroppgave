package org.datavelger;
import javafx.scene.control.Alert;

public class ErrorDialog {
    public static void showErrorDialog(String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Det skjedde en feil");
        alert.setHeaderText("Har du skrevet riktig i søkefeltet?");
        alert.setContentText(info);
        alert.showAndWait();
    }
}
