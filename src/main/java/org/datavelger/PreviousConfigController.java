package org.datavelger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.datavelger.classes.DataCollection;
import org.datavelger.classes.FileOpenerCsv;
import org.datavelger.classes.GraphicsCard;
import org.datavelger.classes.Parts;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PreviousConfigController implements Initializable{
    private FileOpenerCsv fileOpenerCsv;

    @FXML
    Button back;
    @FXML
    MenuButton sort;
    @FXML
    Label sorting, labInfo;
    @FXML
    MenuItem popularity, price;
    @FXML
    AnchorPane anchorPane;
    @FXML
    private TableColumn<DataCollection, String> nameCol, graphicsCol,screenCol, keyboardCol,
            processorCol, harddriveCol, motherboardCol;
    @FXML
    private TableView<List<String>> tableView;

    DataCollection collection = new DataCollection();


    public void loadOrders(){
        fileOpenerCsv = new FileOpenerCsv("file.csv", false);
        fileOpenerCsv.setOnSucceeded(event -> {
            //legg til verdiene i Tableview med :
            List<List<String>> list = fileOpenerCsv.getValue();
            for(int i = 1; i<list.size(); i++){
                collection.addElement(list.get(i));
                System.out.println(list.get(i));
            }
            //System.out.println(list.get(1).get(4));
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
        //TODO disable elements
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Navn"));
        graphicsCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Grafikkort"));
        screenCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Skjerm"));
        keyboardCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Tastatur"));
        processorCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Prosessor"));
        harddriveCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Harddisk"));
        motherboardCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Hovedkort"));

        collection.attachTableView(tableView);

        loadOrders();

        back.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                switchToCustomer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
       /* popularity.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            sortPopularity();
        });
        price.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            sortPrice();
        });*/
     //  popularity.setOnAction(actionEvent -> System.out.println("Sorterer på popularitet"));
       //price.setOnAction(actionEvent -> System.out.println("Sorterer på pris"));
    }

    /*public void sortPopularity(){
                System.out.println("Sorterer etter popularitet");
                sorting.setText("Sorterer etter popularitet");
                anchorPane.getChildren().add(sorting);
            }
            public void sortPrice(){
                System.out.println("Sorterer etter pris");
                sorting.setText("Sorterer etter pris");
                anchorPane.getChildren().add(sorting);
            }*/
    public void switchToCustomer() throws IOException {
        App.setRoot("kundeside2");
    }

}
