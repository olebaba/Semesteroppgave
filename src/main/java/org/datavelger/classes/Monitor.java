package org.datavelger.classes;

public class Monitor extends Component {
    private int size;

    public Monitor(int price, String name, int size) {
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
    public String toString() {
        return String.format("Monitor{%d,%s,%s}", getPrice(), getName(), getSize());
    }
}
