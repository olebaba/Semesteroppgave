package org.datavelger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;
import org.datavelger.classes.*;

import java.lang.reflect.Array;

public class showChooser {

    public static void pressedButton(String pressedButton) throws InvalidNameException, InvalidPriceException {
        ComponentDataCollection collection = new ComponentDataCollection();
        System.out.println("Velger "+pressedButton+"...");
        Stage window = new Stage();
        window.setWidth(400);
        window.setHeight(400);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(pressedButton);
        Label label = new Label();
        label.setText("Velg ønsket "+pressedButton);

        TableView<Component> table = new TableView<>();
        TableColumn<Component, String> name = new TableColumn<>("Navn");
        //TableColumn<ComponentDataCollection, String>  series = new TableColumn<>("Serie");
        TableColumn<Component, String> price = new TableColumn<>("Pris");
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));

        //TODO: legge til komponenter som passer med type komponent valgt, fra fil
        collection.addElement(new Keyboard(233, "superduperkeyboard", true));
        collection.attachTableView(table);
        table.getColumns().setAll(name, price);

        table.setOnMouseClicked((MouseEvent event)->{
            //TODO: Sjekke hvilken entry som er klikket på i tableview
        });

         Label valgt = new Label();
        valgt.setText("Du har valgt følgende "+pressedButton);
        //TODO: skrive ut valgt grafikkort basert på tableview

        Button closeButton = new Button("Tilbake");
        closeButton.setOnAction(e -> window.close());


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,valgt, table,closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
