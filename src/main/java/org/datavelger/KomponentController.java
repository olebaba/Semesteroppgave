package org.datavelger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.datavelger.classes.Component;
import org.datavelger.classes.FileOpenerCsv;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KomponentController implements Initializable {
    private FileOpenerCsv fileOpenerCsv;

    @FXML
    private TableView<Component> table;
    @FXML
    private TableColumn<Component, String> namecol;
    @FXML
    private TableColumn<Component, Integer> pricecol;
    @FXML
    private TextField txt_name, txt_price;
    @FXML
    private Button btn_add, btnLoadAll, btnAvbryt;
    @FXML
    private Label labInfo;
    @FXML
    private ChoiceBox <String> komponent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLoadAll.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            loadComponents();
        });

        btnAvbryt.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                App.setRoot("main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        namecol.setCellValueFactory(new PropertyValueFactory<>("price"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(getComponentList());
    }


    public void AddRecord(){
        ChoiceBox<String> komponent = new ChoiceBox<>();
        komponent.getItems().addAll( "Komponent", "Grafikkkort", "Harddisk", "Tastatur", "Prosessor", "Skjerm", "Hovedkort", "Minne", "Mus");

        komponent.setValue("Komponent");

        Component comp = new Component();

        comp.setPrice(Integer.parseInt(txt_price.getText()));
        comp.setName(txt_name.getText());
        table.getItems().add(comp);
        txt_name.clear();
        txt_price.clear();
    }
    ObservableList<Component> getComponentList() {
        ObservableList<Component> components= FXCollections.observableArrayList();
        components.add(new Component(123,"Keyboard") );
        components.add(new Component(1000,"harddrive") );
        components.add(new Component(2000,"Mouse") );
        components.add(new Component(123,"Keyboard") );

        return components;
    }

    public void loadComponents(){
        fileOpenerCsv = new FileOpenerCsv("file.csv", false);
        fileOpenerCsv.setOnSucceeded(event -> {
            //legg til verdiene i Tableview med :
            fileOpenerCsv.getValue();
            enableGUI(false);
            labInfo.setText("Filen er lastet inn.");
        });
        //om filen ikke blir funnet
        fileOpenerCsv.setOnFailed(event -> {
            try {
                throw event.getSource().getException();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            enableGUI(false);
            labInfo.setText("Fant ikke angitt fil.");
            System.out.println("Fant ikke angitt fil.");
        });

        Thread thread = new Thread(fileOpenerCsv);
        thread.setDaemon(true);
        enableGUI(true);
        thread.start();
    }

    public void enableGUI(boolean enable){
        if (enable){
            txt_name.setDisable(true);
            txt_price.setDisable(true);
            table.setDisable(true);
        }else {
            txt_name.setDisable(false);
            txt_price.setDisable(false);
            table.setDisable(false);
        }
    }

}
