package org.datavelger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminLoginController implements Initializable {

    @FXML
    private Text txtComment;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink hypCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        btnLogin.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Login();
        });
        password.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                Login();
            }
        });

        hypCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                App.setRoot("main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    public void Login(){
        if(txtUsername.getText().equals("Admin") && password.getText().equals("Admin")) {
            try {
                App.setRoot("addComponentView");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            txtComment.setText("Feil brukernavn eller passord!");
        }
    }
}
