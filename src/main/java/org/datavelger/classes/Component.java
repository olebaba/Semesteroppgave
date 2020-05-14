package org.datavelger.classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Component implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient SimpleIntegerProperty price;
    private transient SimpleStringProperty name, compType;

    public Component(){}

    public Component(int price, String name, String compType) throws InvalidPriceException, InvalidNameException {
        setPrice(price);
        setName(name);
        setCompType(compType);
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

    public String getCompType() {
        return compType.get();
    }
    public void setCompType(String compType) {
        this.compType = new SimpleStringProperty(compType);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getPrice());
        s.writeUTF(getName());
        s.writeUTF(getCompType());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException,
            InvalidPriceException, InvalidNameException {
        setPrice(s.readInt());
        setName(s.readUTF());
        setCompType(s.readUTF());
    }

    public String toJSON() {
        return String.format("\"Price\":\"%d\",\"Name\":\"%s\"", getPrice(), getName());
    }

    @Override
    public String toString(){
        return String.format("price = %d, name = %s, type = %s", getPrice(), getName(), getCompType());
    }
}
