package org.datavelger.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
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
    //private ComponentDataCollection collection = new ComponentDataCollection();

    public ObservableList<Component> filterByComponent(String component){
        return list.stream().filter(x -> x.getCompType().toLowerCase().
                matches(String.format("%s%s%s",".*",component.toLowerCase(),".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Component> filterByName(String name){
        return list.stream().filter(x -> x.getName().toLowerCase().
                matches(String.format("%s%s%s",".*",name.toLowerCase(),".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Component> filterByPrice(String price){
        if(price.isBlank()){
            return null;
       }else if(price.matches("[A-Åa-å*]")){
           return null;
        }else {
            String split[] = price.split("-");

            String firstValue = split[0];
            String lastValue = split[1];
            return list.stream().filter(x -> Integer.parseInt(firstValue) <= (x.getPrice()) && (x.getPrice() <= Integer.parseInt(lastValue))).
                    collect(Collectors.toCollection(FXCollections::observableArrayList));
        }
    }
}