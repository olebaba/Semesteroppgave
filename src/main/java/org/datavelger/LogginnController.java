package org.datavelger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogginnController implements Initializable {

    @FXML
    private Text Kommentar;

    @FXML
    private Text username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loggInn;

    @FXML
    private Hyperlink avbryt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loggInn.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                App.setRoot("addKomponent");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        avbryt.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                App.setRoot("main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    public void Login(ActionEvent event){
        if(username.getText().equals("Admin") && password.getText().equals("Admin")) {
            Kommentar.setText("Logget inn");
        }
        else{
            Kommentar.setText("Feil brukernavn eller passord");

        }
    }
}
