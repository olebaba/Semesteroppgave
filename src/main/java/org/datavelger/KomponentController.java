package org.datavelger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.datavelger.classes.Component;

import java.net.URL;
import java.util.ResourceBundle;

public class KomponentController implements Initializable {

    @FXML
    private TableView<Component> table;
    @FXML
    private TableColumn<Component, String> namecol;
    @FXML
    private TableColumn<Component, Integer> pricecol;
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_price;
    @FXML
    private Button btn_add;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namecol.setCellValueFactory(new PropertyValueFactory<>("price"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(getComponentList());
    }

    public void AddRecord(){
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
}
