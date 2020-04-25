package org.datavelger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreviousConfigController implements Initializable{
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                switchToCustomer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
       /* popularity.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            sortPopularity();
        });
        price.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            sortPrice();
        });*/
     //  popularity.setOnAction(actionEvent -> System.out.println("Sorterer på popularitet"));
       //price.setOnAction(actionEvent -> System.out.println("Sorterer på pris"));
    }

    /*public void sortPopularity(){
                System.out.println("Sorterer etter popularitet");
                sorting.setText("Sorterer etter popularitet");
                anchorPane.getChildren().add(sorting);
            }
            public void sortPrice(){
                System.out.println("Sorterer etter pris");
                sorting.setText("Sorterer etter pris");
                anchorPane.getChildren().add(sorting);
            }*/
    public void switchToCustomer() throws IOException {
        App.setRoot("kundeside2");
    }
    @FXML
    Button back;
    @FXML
    MenuButton sort;
    @FXML
    Label sorting;
    @FXML
    MenuItem popularity, price;
    @FXML
    AnchorPane anchorPane;
}
