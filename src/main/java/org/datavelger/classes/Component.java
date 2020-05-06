package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

import java.io.Serializable;

public class Component implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public String toJSON() {
        return String.format("\"Price\":\"%d\",\"Name\":\"%s\"", getPrice(), getName());
    }

    @Override
    public String toString(){
        return String.format("price = %d, name = %s", getPrice(), getName());
    }
}
