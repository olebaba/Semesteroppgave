package org.datavelger.classes;

public class Harddrive extends Component{
    private int size;
    private int capasity;


    public Harddrive(int price, String name, int size, int capasity) {
        super(price, name);
        this.size = size;
        this.capasity = capasity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapasity() {
        return capasity;
    }

    public void setCapasity(int capasity) {
        this.capasity = capasity;
    }
}
