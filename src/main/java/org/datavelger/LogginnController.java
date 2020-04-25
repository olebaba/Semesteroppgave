package org.datavelger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogginnController implements Initializable {

    @FXML
    private Text Kommentar;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink avbryt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if(Login()) {
                try {
                    App.setRoot("addKomponent");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        password.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                if(Login()) {
                    try {
                        App.setRoot("addKomponent");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
    public boolean Login(){
        if(username.getText().equals("Admin") && password.getText().equals("Admin")) {
            Kommentar.setText("Logget inn");
            return true;
        }
        else{
            Kommentar.setText("Feil brukernavn eller passord");
            return false;
        }
    }
}
