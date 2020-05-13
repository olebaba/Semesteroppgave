package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

public class Motherboard extends Component{
    private static final long serialVersionUID = 1L;
    private String type;


    public Motherboard(int price, String name, String compType, String type) throws InvalidNameException, InvalidPriceException {
        super(price, name, compType);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toJSON() {
        return String.format("Motherboard{%d;%s;%s}", getPrice(), getName(), getType());
    }

    @Override
    public String toString(){
        return String.format("Keyboard: " + super.toString() + ", has RGB = %s", getType());
    }
}
