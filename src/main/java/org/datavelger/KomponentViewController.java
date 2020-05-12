package org.datavelger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.datavelger.classes.Component;
import org.datavelger.classes.Harddrive;

import java.net.URL;
import java.util.ResourceBundle;

public class KomponentViewController implements Initializable {

    private Component selectedComponent;

    @FXML private Label komponentLabel;

    @FXML private Label navnLabel;

    @FXML private Label PrisLabel;

    @FXML private Label MerkeLabel;





    public void initData (Component component)
    {
        selectedComponent = component;
        navnLabel.setText(selectedComponent.getName());
        PrisLabel.setText(Integer.toString(selectedComponent.getPrice()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
