package org.datavelger.classes;

public class GraphicsCard extends Component{

    public GraphicsCard(int price, String name) {
        super(price, name);
    }



    @Override
    public String toString() {
        return String.format("GraphicsCard{%d,%s}", getPrice(), getName());
    }
}
