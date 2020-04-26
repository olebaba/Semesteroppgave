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
            //legg til verdiene i Tableview
            List<List<String>> list = fileOpenerCsv.getValue();
            for(int i = 1; i<list.size(); i++){ //første linje er headers
                //System.out.println(list.get(i).get(3)); //test
                collection.addElement(createOrder(list.get(i)));

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
        nameCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Name"));
        graphicsCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("GraphicsCard"));
        screenCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Monitor"));
        keyboardCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Keyboard"));
        processorCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Processor"));
        harddriveCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Harddrive"));
        motherboardCol.setCellValueFactory(new PropertyValueFactory<DataCollection, String>("Motherboard"));

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
