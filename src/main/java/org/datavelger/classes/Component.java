package org.datavelger.classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Component implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient SimpleIntegerProperty price;
    private transient SimpleStringProperty name;

    public Component(){}

    public Component(int price, String name) throws InvalidPriceException, InvalidNameException {
        setPrice(price);
        setName(name);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) throws InvalidPriceException {
        if (!Validator.isValidPrice(price)) throw new InvalidPriceException();
        this.price = new SimpleIntegerProperty(price);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) throws InvalidNameException {
        if(!Validator.isValidName(name)) throw new InvalidNameException();
        this.name = new SimpleStringProperty(name);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getPrice());
        s.writeUTF(getName());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException,
            InvalidPriceException, InvalidNameException {
        setPrice(s.readInt());
        setName(s.readUTF());
    }

    public String toJSON() {
        return String.format("\"Price\":\"%d\",\"Name\":\"%s\"", getPrice(), getName());
    }

    @Override
    public String toString(){
        return String.format("price = %d, name = %s", getPrice(), getName());
    }
}
