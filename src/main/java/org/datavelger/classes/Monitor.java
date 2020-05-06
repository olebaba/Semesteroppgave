package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

public class Monitor extends Component {
    private int size;

    public Monitor(int price, String name, int size) throws InvalidNameException, InvalidPriceException {
        super(price, name);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toJSON() {
        return String.format("Monitor{%d;%s;%s}", getPrice(), getName(), getSize());
    }
}
