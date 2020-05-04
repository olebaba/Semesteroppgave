package org.datavelger.classes;

import javafx.collections.ObservableList;
import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

public class Component {
    private int price;
    private String name;

    public Component(){}

    public Component(int price, String name) throws InvalidPriceException, InvalidNameException {
        setPrice(price);
        setName(name);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws InvalidPriceException {
        if (!Validator.isValidPrice(price)) throw new InvalidPriceException();
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidNameException {
        if(!Validator.isValidName(name)) throw new InvalidNameException();
        this.name = name;
    }
}
