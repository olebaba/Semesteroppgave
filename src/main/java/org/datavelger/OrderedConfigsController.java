package org.datavelger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.datavelger.classes.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private TableColumn<OrderDataCollection, String> nameCol, graphicsCol,screenCol, keyboardCol,
             harddriveCol, memoryCol, motherboardCol, mouseCol, processorCol, priceTotalCol;
    @FXML
    private TableView<Order> tableView;

    OrderDataCollection collection = new OrderDataCollection();

    public Order createOrder(List<String> orderlist) throws IOException, ClassNotFoundException {

        List<Component> components = new ArrayList<>();
        for (int i = 1; i<orderlist.size()-1; i++){ //første og siste er ikke komponenter
            try {
                components.add(FileOpenerCsv.findComponent(orderlist.get(i)));
            }catch (FileNotFoundException e ){
                labInfo.setText(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }

        try {
            return new Order(orderlist.get(0), components.get(0), components.get(1), components.get(2),
                    components.get(3), components.get(4), components.get(5), components.get(6), components.get(7));
        }catch (Exception e){
            e.printStackTrace();
        }
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
        fileOpenerCsv = new FileOpenerCsv("orders.csv", true);
        fileOpenerCsv.setOnSucceeded(event -> {
            //henter tidligere konfigurerte pcer og legger dem i tableview
            List<List<String>> list = fileOpenerCsv.getValue();
            for(int i = 1; i<list.size(); i++){ //første linje er headers
                try {
                    collection.addElement(createOrder(list.get(i)));
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
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
        anchorPane.setDisable(enable);
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
        mouseCol.setCellValueFactory(new PropertyValueFactory<>("Mouse"));
        memoryCol.setCellValueFactory(new PropertyValueFactory<>("Memory"));
        priceTotalCol.setCellValueFactory(new PropertyValueFactory<>("PriceTotal"));


        collection.attachTableView(tableView);

        loadOrders();

        back.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                switchToCustomer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void switchToCustomer() throws IOException {
        App.setRoot("costumerView");
    }

}
