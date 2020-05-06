package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

public class GraphicsCard extends Component{
    private static final long serialVersionUID = 1L;

    public GraphicsCard(int price, String name) throws InvalidNameException, InvalidPriceException {
        super(price, name);
    }

    @Override
    public String toJSON() {
        return String.format("GraphicsCard{%d;%s}", getPrice(), getName());
    }

    @Override
    public String toString(){
        return "Graphics card: " + super.toString();
    }
}
