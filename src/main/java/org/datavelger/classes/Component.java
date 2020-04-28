package org.datavelger.classes;

import javafx.collections.ObservableList;

public class Component {
    private int price;
    private String name;

    public Component(){}

    public Component(int price, String name){
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setkompononent(ObservableList<String> items) {
    }
}
