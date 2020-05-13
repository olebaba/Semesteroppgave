package org.datavelger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;
import org.datavelger.classes.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddComponentController implements Initializable {

    @FXML
    private TableView<Component> table;
    @FXML
    private TableColumn<Component, String> namecol, compCol;
    @FXML
    private TableColumn<Component, Integer> pricecol;
    @FXML
    private TextField txt_name, txt_price;
    @FXML
    private Button btnAdd, btnDeleteComponent, btnDeleteOrder, btnCancel, btnEditComp, btnSaveChanges;
    @FXML
    private Label labInfo;
    @FXML
    private ChoiceBox <String> componentBox;
    @FXML
    private AnchorPane anchorPane;

    private ComponentDataCollection collection = new ComponentDataCollection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //last inn komponenter og table///////////////////////
        loadComponents();
        collection.attachTableView(table);

        //for å slette flere rader samtidig
        table.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        //oppdatere tabell for å endre navn
        namecol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        namecol.editableProperty().setValue(true);
        pricecol.editableProperty().setValue(true);


        //initialisere knapper////////////////////////////////////////////
        btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                AddRecord();
            } catch (InvalidPriceException | InvalidNameException e) {
                e.printStackTrace();
            }
        });

        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                App.setRoot("main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnDeleteComponent.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            deleteComponent();
        });

        btnEditComp.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            editComponent();
        });

        btnSaveChanges.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            saveComponents();
        });

        //vise ekstra info om komponent
        /*btnComponentInformation.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                App.setRoot("componentView");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
         */

        //gjør klar innlegging av komponenter //////////////////////////////////////////////
        componentBox.getItems().addAll(" ", "Grafikkort", "Harddisk", "Tastatur", "Minne", "Skjerm",
                "Hovedkort", "Mus", "Prosessor");

        //ikke mulig å gå videre uten å velge type komponent
        componentBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldChoice, newChoice) ->
                btnAdd.setDisable(newChoice.equals(componentBox.getItems().get(0))));
        componentBox.setValue(componentBox.getItems().get(0));
    }

    //slette rader som er valgt i tabellen
    public void deleteComponent()
    {
        ObservableList<Component> selectedRows;
        selectedRows = table.getSelectionModel().getSelectedItems();

        for (Component component: selectedRows){
            table.getItems().remove(component);
        }
    }

    public void editComponent(){
        Component editedComp = table.getSelectionModel().getSelectedItem();
        txt_name.setText(editedComp.getName());
        txt_price.setText(String.valueOf(editedComp.getPrice()));
        table.getItems().remove(editedComp);
    }

    public void changeNameCellEvent(TableColumn.CellEditEvent edditedCell) throws InvalidNameException {
        Component componentSelected = table.getSelectionModel().getSelectedItem();
        componentSelected.setName(edditedCell.getNewValue().toString());
    }
    public void changePriceCellEvent(TableColumn.CellEditEvent edditedCell) throws InvalidPriceException {
        Component componentSelected = table.getSelectionModel().getSelectedItem();
        componentSelected.setPrice(((Integer) edditedCell.getNewValue()));
    }

    public void AddRecord() throws InvalidPriceException, InvalidNameException {
        Component comp = new Component();

        comp.setPrice(Integer.parseInt(txt_price.getText()));
        comp.setName(txt_name.getText());

        collection.addElement(comp);

        txt_name.clear();
        txt_price.clear();
    }

    //eksempel-komponenter
    public ObservableList<Component> getComponentList() throws InvalidNameException, InvalidPriceException {
        ObservableList<Component> components = FXCollections.observableArrayList();
        components.add(new Keyboard(1299,"Keyboard",false));
        components.add(new Component(1315,"Mouse") );
        components.add(new Component(299,"Motherboard") );
        components.add(new Component(1490,"Apple Magic Keyboard with Numeric Keypad") );

        return components;
    }

    public void loadComponents() {
        File folder = new File("components");
        FileOpenerBinary fileOpenerBinary = new FileOpenerBinary(folder.getPath());
        fileOpenerBinary.setOnSucceeded(event -> {
            enableGUI(false);
            ArrayList<Component> components = (ArrayList<Component>) fileOpenerBinary.getValue();
            for (Component comp : components){
                collection.addElement(comp);
            }
        });
        fileOpenerBinary.setOnFailed(event -> {
            try {
                throw event.getSource().getException();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            enableGUI(false);
            //labInfo.setText("Fant ikke angitt fil.");
            System.out.println("Fant ikke angitt fil.");
        });

        Thread thread = new Thread(fileOpenerBinary);
        thread.setDaemon(true);
        enableGUI(true);
        thread.start();
    }

    public void saveComponents(){
        ArrayList<Component> components = new ArrayList<>(collection.getList());
        for (Component component : components) {
            String folder = "";
            if(component.getClass().equals(GraphicsCard.class)) folder = "GraphicsCards";
            if(component.getClass().equals(Harddrive.class)) folder = "Harddrives";
            if(component.getClass().equals(Keyboard.class)) folder = "Keyboards";
            if(component.getClass().equals(Memory.class)) folder = "Memory";
            if(component.getClass().equals(Mouse.class)) folder = "Mice";
            if(component.getClass().equals(Monitor.class)) folder = "Monitors";
            if(component.getClass().equals(Motherboard.class)) folder = "Motherboards";
            if(component.getClass().equals(Processor.class)) folder = "Processord";

            FileSaverBinary fileSaverBinary = new FileSaverBinary(component);
            fileSaverBinary.saveFile("components/" + folder
                    + "/" + component.getName() + ".jobj");
        }
    }

    public void enableGUI(boolean enable){
        anchorPane.setDisable(enable);
    }

}
