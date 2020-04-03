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




   /* @FXML
    Button admin, kunde;
    btnOpenNewWindow.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"), resources);
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}*/