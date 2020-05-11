package org.datavelger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private Button btnAdd, btnLoadAll, btnAvbryt;
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

        //for å slette flere rader samtidig
        table.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        //oppdatere tabell for å endre navn
        table.setEditable(true);
        namecol.setCellFactory((TextFieldTableCell.forTableColumn()));

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
    /*public void changePriceCellEvent(TableColumn.CellEditEvent edditedCell) throws InvalidPriceException {
        Component componentSelected = table.getSelectionModel().getSelectedItem();
        componentSelected.setPrice(edditedCell.getNewValue().toString());
    }*/

    public static class Validering {
        public static String validerkomponent(Component komponent){
            StringBuilder out = new StringBuilder();
            if (!validernavn(komponent.getName()))out.append("Ugyldig input, ");
            if (!validerpris(komponent.getPrice()))out.append("Ugyldig input, ");


            return out.toString();
        }

        private static boolean validerpris(int txt_price) {
            return (txt_price >= 0);
        }

        private static boolean validernavn(String txt_name) {
            return ((txt_name != null)
                    && (!txt_name.equals(""))
                    && (txt_name.matches("([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})")));

        }
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
        components.add(new Component(  123,"Keyboard") );
        components.add(new Component(1000,"Harddrive") );
        components.add(new Component(2000,"Mouse") );
        components.add(new Component(123,"Keyboard") );



        return components;
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
