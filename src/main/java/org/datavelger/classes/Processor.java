package org.datavelger.classes;

public class Processor extends Component{
    private String type;


    public Processor(int price, String name, String type) {
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
        return String.format("Harddrive{%d,%s,%s}", getPrice(), getName(), getType());
    }
}
