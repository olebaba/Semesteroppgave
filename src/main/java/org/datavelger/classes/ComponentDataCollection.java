package org.datavelger.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ComponentDataCollection {

    private ObservableList<Component> list = FXCollections.observableArrayList();

    public void attachTableView(TableView<Component> tv) {
        tv.setItems(list);
    }
    public void addElement(Component comp) {
        list.add(comp);
    }
    public void deleteElement(Component comp) {
        list.remove(comp);
    }

    public ObservableList<Component> getList(){
        return list;
    }


}