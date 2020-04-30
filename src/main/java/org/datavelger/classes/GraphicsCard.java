package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

public class GraphicsCard extends Component{

    public GraphicsCard(int price, String name) throws InvalidNameException, InvalidPriceException {
        super(price, name);
    }



    @Override
    public String toString() {
        return String.format("GraphicsCard{%d;%s}", getPrice(), getName());
    }
}
