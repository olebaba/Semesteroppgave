package org.datavelger.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class OrderDataCollection {

    private ObservableList<Order> list = FXCollections.observableArrayList();

    public void attachTableView(TableView<Order> tv) {
        tv.setItems(list);
    }

    public void addElement(Order string) {
        list.add(string);
    }

    public ObservableList<Order> getList(){
        return list;
    }

}