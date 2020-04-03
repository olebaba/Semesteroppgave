package org.datavelger.classes;

public class Harddrive extends Component{
    private double size;
    private int capasity;


    public Harddrive(int price, String name, double size, int capasity) {
        super(price, name);
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
}
