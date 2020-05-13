package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

public class Harddrive extends Component{
    private static final long serialVersionUID = 1L;
    private double size;
    private int capasity;


    public Harddrive(int price, String name, String compType, double size, int capasity) throws InvalidNameException, InvalidPriceException {
        super(price, name, compType);
        this.size = size;
        this.capasity = capasity;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getCapasity() {
        return capasity;
    }

    public void setCapasity(int capasity) {
        this.capasity = capasity;
    }

    @Override
    public String toJSON() {
        return String.format("Harddrive{%d;%s;%.2f;%d}", getPrice(), getName(), getSize(), getCapasity());
    }

    @Override
    public String toString(){
        return String.format("Harddrive: " + super.toString() +
                ", size = %.2f, Capasity = %d", getSize(), getCapasity());
    }
}
