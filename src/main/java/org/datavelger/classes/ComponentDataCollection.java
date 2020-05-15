package org.datavelger.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.datavelger.ErrorDialog;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
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
        if(component.isBlank()){
            ErrorDialog.showErrorDialog("Skriv inn søkeord for komponenttypen.");
        }
        return list.stream().filter(x -> x.getCompType().toLowerCase().
                matches(String.format("%s%s%s",".*",component.toLowerCase(),".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Component> filterByName(String name){
        if(name.isBlank()) {
            ErrorDialog.showErrorDialog("Skriv inn søkeord for navnet på komponentet.");
            return null;
        }
            return list.stream().filter(x -> x.getName().toLowerCase().
                    matches(String.format("%s%s%s", ".*", name.toLowerCase(), ".*"))).
                    collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Component> filterByPrice(String price){
        if(price.isBlank() || (!price.matches("^[0-9]+(-[0-9]+)"))){
            ErrorDialog.showErrorDialog("Skriv inn gyldige siffer på formatet \"startverdi-sluttverdi\" (f.eks 1500-2000)");
            return null;
        }else{
            String split[] = price.split("-");
            String firstValue = split[0];
            String lastValue = split[1];
            int finalFirstValue = Integer.parseInt(firstValue);
            int finalLastValue = Integer.parseInt(lastValue);
            if(finalLastValue<finalFirstValue){
                ErrorDialog.showErrorDialog("Startverdien for søket kan ikke være større enn sluttverdien.");
                return null;
            }else {
                //Teste om startverdien og sluttverdien
                return list.stream().filter(x -> finalFirstValue <= (x.getPrice()) && (x.getPrice() <= finalLastValue)).
                        collect(Collectors.toCollection(FXCollections::observableArrayList));
            }
        }
    }
}