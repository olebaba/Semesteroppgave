package org.datavelger.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

public class DataCollection {

    private ObservableList<List<String>> list = FXCollections.observableArrayList();

    public void attachTableView(TableView<List<String>> tv) {
        tv.setItems(list);
    }

    public void addElement(List<String> string) {
        list.add(string);
    }

    public ObservableList<List<String>> getList(){
        return list;
    }

}