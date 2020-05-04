package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

public class Processor extends Component{
    private String type;


    public Processor(int price, String name, String type) throws InvalidNameException, InvalidPriceException {
        super(price, name);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("\"Processor\":{" + super.toString() + ",\"Type\":\"%s\"}", getType());
    }
}
