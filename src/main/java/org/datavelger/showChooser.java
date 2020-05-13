package org.datavelger;

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
import org.datavelger.classes.Component;
import org.datavelger.classes.ComponentDataCollection;
import org.datavelger.classes.FileOpenerBinary;

import java.io.IOException;
import java.util.List;

public class showChooser {
    static TableView<Component> table = new TableView<>();
    static Component chosenComp;
    static String folderpath = "komponenter";

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
        VBox layout = new VBox(10);

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
        System.out.println(pressedButton);
        switch (pressedButton){
            case "grafikkort" : {

                FileOpenerBinary graphicsCardsFolder = new FileOpenerBinary(folderpath + "/grafikkort");
                graphicsCardsFolder.setOnSucceeded(event -> {
                    layout.setDisable(false);
                    List<Component> graphicsCards = graphicsCardsFolder.getValue();

                    for (Component card : graphicsCards){
                        collection.addElement(card);
                        System.out.println(card.getName());
                    }
                });
                graphicsCardsFolder.setOnFailed(event -> {
                    try {
                        throw event.getSource().getException();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    layout.setDisable(false);
                    //labInfo.setText("Fant ikke angitt fil.");
                    System.out.println("Fant ikke angitt fil. Har du lagret grafikkort? Har du valgt riktig path?");
                });


                Thread thread = new Thread(graphicsCardsFolder);
                thread.setDaemon(true);
                layout.setDisable(true);
                thread.start();
            }
        }

        collection.attachTableView(table);
        System.out.println(table.getItems());
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



        layout.getChildren().addAll(label,valgt, table, chooseButton,closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
