package org.datavelger.classes;

public class Motherboard extends Component{
    private String type;


    public Motherboard(int price, String name, String type) {
        super(price, name);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
