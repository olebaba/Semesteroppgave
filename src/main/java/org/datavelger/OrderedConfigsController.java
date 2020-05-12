package org.datavelger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.datavelger.classes.DataCollection;
import org.datavelger.classes.FileOpenerCsv;
import org.datavelger.classes.Order;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderedConfigsController implements Initializable{
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
            processorCol, harddriveCol, motherboardCol, priceCol;
    @FXML
    private TableView<Order> tableView;

    DataCollection collection = new DataCollection();

    public Order createOrder(List<String> orderlist){
        Order order = new Order();
        order.setName(orderlist.get(0));
        order.setGraphicsCard(orderlist.get(1));
        order.setHarddrive(orderlist.get(2));
        order.setKeyboard(orderlist.get(3));
        order.setMemory(orderlist.get(4));
        order.setMonitor(orderlist.get(5));
        order.setMotherboard(orderlist.get(6));
        order.setMouse(orderlist.get(7));
        order.setProcessor(orderlist.get(8));
        return order;
    }

    public void loadOrders(){
        fileOpenerCsv = new FileOpenerCsv("file.csv", true);
        fileOpenerCsv.setOnSucceeded(event -> {
            //henter tidligere konfigurerte pcer og legger dem i tableview
            List<List<String>> list = fileOpenerCsv.getValue();
            for(int i = 1; i<list.size(); i++){ //første linje er headers
                collection.addElement(createOrder(list.get(i)));
            }
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
        if (enable) anchorPane.setDisable(true);
        else anchorPane.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        graphicsCol.setCellValueFactory(new PropertyValueFactory<>("GraphicsCard"));
        screenCol.setCellValueFactory(new PropertyValueFactory<>("Monitor"));
        keyboardCol.setCellValueFactory(new PropertyValueFactory<>("Keyboard"));
        processorCol.setCellValueFactory(new PropertyValueFactory<>("Processor"));
        harddriveCol.setCellValueFactory(new PropertyValueFactory<>("Harddrive"));
        motherboardCol.setCellValueFactory(new PropertyValueFactory<>("Motherboard"));

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
        App.setRoot("costumerView");
    }

}
