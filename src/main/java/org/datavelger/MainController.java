package org.datavelger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kunde.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                switchToKunde();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void switchToKunde() throws IOException {
        App.setRoot("kundeside2");
    }

    @FXML
    Button admin, kunde;

}



