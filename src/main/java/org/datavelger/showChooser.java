package org.datavelger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.datavelger.classes.Component;

public class showChooser {
    public static void pressedButton(String pressedButton){
        System.out.println("Velger "+pressedButton+"...");
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(pressedButton);
        Label label = new Label();
        label.setText("Velg ønsket "+pressedButton);
/*
        TableView table = new TableView<>();
        TableColumn name = new TableColumn("Navn");
        TableColumn  series = new TableColumn("Serie");
        TableColumn price = new TableColumn("Pris");
        table.getColumns().addAll(name,series,price);
        table.setOnMouseClicked((MouseEvent event)->{
            //TODO: Sjekke hvilken entry som er klikket på i tableview
        });*/

         Label valgt = new Label();
        valgt.setText("Du har valgt følgende "+pressedButton);
        //TODO: skrive ut valgt grafikkort basert på tableview

        Button closeButton = new Button("Tilbake");
        closeButton.setOnAction(e -> window.close());


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,valgt,closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
