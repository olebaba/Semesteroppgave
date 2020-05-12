package org.datavelger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.datavelger.classes.Component;
import org.datavelger.classes.Harddrive;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ComponentViewController implements Initializable {

    private Component selectedComponent;

    @FXML private Label komponentLabel, navnLabel, PrisLabel, MerkeLabel;
    @FXML private Button btnTilbake;

    public void initData (Component component)
    {
        selectedComponent = component;
        navnLabel.setText(selectedComponent.getName());
        PrisLabel.setText(Integer.toString(selectedComponent.getPrice()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnTilbake.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                App.setRoot("addComponent");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
