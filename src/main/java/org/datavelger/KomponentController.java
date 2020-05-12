package org.datavelger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;
import org.datavelger.classes.Component;
import org.datavelger.classes.FileOpenerBinary;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KomponentController implements Initializable {
    private FileOpenerBinary fileOpenerBinary;

    @FXML
    private TableView<Component> table;
    @FXML
    private TableColumn<Component, String> namecol, compCol;
    @FXML
    private TableColumn<Component, Integer> pricecol;
    @FXML
    private TextField txt_name, txt_price;
    @FXML
    private Button btnAdd, btnLoadAll, btnAvbryt, KomponentInformasjon;
    @FXML
    private Label labInfo;
    @FXML
    private ChoiceBox <String> component;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*btnLoadAll.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            loadComponents();
        });
        */

        btnAvbryt.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                App.setRoot("main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        KomponentInformasjon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                App.setRoot("komponentView");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //for å slette flere rader samtidig
        table.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        //oppdatere tabell for å endre navn
        table.setEditable(true);
        namecol.setCellFactory((TextFieldTableCell.forTableColumn()));
        //pricecol.setCellFactory(((TextFieldTableCell.forTableColumn());


        component.getItems().addAll(" ", "Grafikkort", "Harddisk", "Tastatur", "Minne", "Skjerm",
                "Hovedkort", "Mus", "Prosessor");

        //ikke mulig å gå videre uten å velge type komponent
        component.getSelectionModel().selectedItemProperty().addListener((observableValue, oldChoice, newChoice) ->
                btnAdd.setDisable(newChoice.equals(component.getItems().get(0))));
        component.setValue(component.getItems().get(0));

        namecol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("Price"));

        try {
            table.setItems(getComponentList());
        } catch (InvalidNameException | InvalidPriceException e) {
            e.printStackTrace();
        }
    }
    //slette rader som  er valgt i tabellen
    public void deleteButtonPushed()
    {
        ObservableList<Component> selectedRows, allcomponent;
        allcomponent = table.getItems();

        selectedRows = table.getSelectionModel().getSelectedItems();

        for (Component component: selectedRows){
            allcomponent.remove(component);
        }
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
        table.getItems().add(comp);
        txt_name.clear();
        txt_price.clear();

    }
    public ObservableList<Component> getComponentList() throws InvalidNameException, InvalidPriceException {
        ObservableList<Component> components = FXCollections.observableArrayList();
        components.add(new Component(  1299,"Keyboard") );
        components.add(new Component(1315,"Mouse") );
        components.add(new Component(299,"Motherboard") );
        components.add(new Component(1490,"Apple Magic Keyboard with Numeric Keypad") );



        return components;
    }

    public void changeSceneToComponentView (ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("komponentView.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        KomponentViewController controller = loader.getController();
        controller.initData(table.getSelectionModel().getSelectedItem());

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }





    public void loadComponents() throws IOException {
        fileOpenerBinary = new FileOpenerBinary();
        fileOpenerBinary.openFile("components.bin", true);

    }

    public void enableGUI(boolean enable){
        if (enable) anchorPane.setDisable(true);
        else anchorPane.setDisable(false);
    }

}
