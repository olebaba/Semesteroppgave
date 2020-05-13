package org.datavelger;

import javafx.beans.property.SimpleIntegerProperty;
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
import org.datavelger.classes.*;

import java.io.IOException;

public class showChooser {
    static TableView<Component> table = new TableView<>();
    static Component chosenComp;

    static private void setChosenComp(Component comp){
        chosenComp = comp;
    }

    public static Component getChosenComp() {
        return chosenComp;
    }

    static private void setColProperties(TableColumn<Component, String> tableColumn, String name){
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(name));
        tableColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
        tableColumn.setResizable(false);
    }

    public static void pressedButton(String pressedButton) throws IOException, ClassNotFoundException {
        ComponentDataCollection collection = new ComponentDataCollection();
        System.out.println("Velger "+pressedButton+"...");
        Stage window = new Stage();
        window.setWidth(400);
        window.setHeight(400);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(pressedButton);
        Label label = new Label();
        label.setText("Velg ønsket "+pressedButton);

        //table.prefWidthProperty().bind(new SimpleIntegerProperty(350));
        table.setEditable(false);
        TableColumn<Component, String> name = new TableColumn<>("Navn");
        TableColumn<Component, String> price = new TableColumn<>("Pris");
        setColProperties(name, "name");
        setColProperties(price, "price");

        //TODO: legge til komponenter som passer med type komponent valgt, fra fil

        //load files
        FileOpenerBinary fileOpenerBinary = new FileOpenerBinary();
        Component aComp = fileOpenerBinary.openFile("components.jobj", true);

        System.out.println(pressedButton);
        switch (pressedButton){
            case "tastatur" : collection.addElement(aComp);
        }

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

        Button chooseButton = new Button("Velg");
        chooseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            setChosenComp(table.getSelectionModel().getSelectedItem());
            System.out.println(getChosenComp());

        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,valgt, table, chooseButton,closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
