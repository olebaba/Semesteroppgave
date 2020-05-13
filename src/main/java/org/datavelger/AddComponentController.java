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
import java.util.jar.JarOutputStream;

public class AddComponentController implements Initializable {
    private final String componentPath = "komponenter";

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
        compCol.setCellValueFactory(new PropertyValueFactory<>("CompType"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("Price"));


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

        //Initialiserer resten //////////////////////////////////////////////////

    }

    //slette rader som er valgt i tabellen
    public void deleteComponent()
    {
        Component component = table.getSelectionModel().getSelectedItem();
        String filepath = componentPath+"/"+component.getCompType()+"/"+component.getName()+".jobj";
        File file = new File(filepath);
        try {
            if (file.delete()) {
                System.out.println("Fil " + filepath + " ble slettet.");
                labInfo.setText("Fil " + filepath + " ble slettet.");
                collection.deleteElement(component);
            }
            else {
                System.out.println("Fil " + filepath +" ikke slettet.");
                labInfo.setText("Fil " + filepath + " ble ikke slettet. Prøv på nytt.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //if (file.delete()) System.out.println("Fil" + filepath + " ble slettet");
        //else System.out.println("Fil " + filepath +" ikke slettet");

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
        comp.setCompType(componentBox.getValue());

        collection.addElement(comp);

        txt_name.clear();
        txt_price.clear();
        labInfo.setText(comp.getName() + " ble lagt til.");
    }

    public void loadComponents() {
        File folder = new File(componentPath);
        FileOpenerBinary fileOpenerBinary = new FileOpenerBinary(folder.getPath());
        fileOpenerBinary.setOnSucceeded(event -> {
            enableGUI(false);
            ArrayList<Component> components = (ArrayList<Component>) fileOpenerBinary.getValue();
            for (Component comp : components){
                collection.addElement(comp);
                labInfo.setText(comp.getName() + " er lastet inn.");
            }
            labInfo.setText("Komponentene er lastet inn.");
        });
        fileOpenerBinary.setOnFailed(event -> {
            try {
                throw event.getSource().getException();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            enableGUI(false);
            labInfo.setText("Fant ikke angitte filer.");
            System.out.println("Fant ikke angitt fil. Har du lagret noen komponenter?");
        });

        Thread thread = new Thread(fileOpenerBinary);
        thread.setDaemon(true);
        enableGUI(true);
        labInfo.setText("Komponentene lastes inn.");
        thread.start();
    }

    public void saveComponents(){
        ArrayList<Component> components = new ArrayList<>(collection.getList());
        for (Component component : components) {
            FileSaverBinary fileSaverBinary = new FileSaverBinary(component);
            String pathname = componentPath + "/" + component.getCompType()
                    + "/" + component.getName() + ".jobj";
            System.out.println(pathname);
            fileSaverBinary.saveFile(pathname);
        }

        labInfo.setText("Komponentene er lagret.");
    }

    public void enableGUI(boolean enable){
        anchorPane.setDisable(enable);
    }

}
