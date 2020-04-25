package org.datavelger.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DataCollection {

    private ObservableList<Parts> list = FXCollections.observableArrayList();

    public void attachTableView(TableView<Parts> tv) {
        tv.setItems(list);
    }

    public void addElement(Parts obj) {
        list.add(obj);
    }

    public ObservableList<Parts> getList(){
        return list;
    }

}