package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

public class Keyboard extends Component {
    private boolean rgb;

    public Keyboard(int price, String name, boolean rgb) throws InvalidNameException, InvalidPriceException {
        super(price, name);
        this.rgb = rgb;
    }

    public boolean isRgb() {
        return rgb;
    }

    public void setRgb(boolean rgb) {
        this.rgb = rgb;
    }

    @Override
    public String toJSON() {
        return String.format("Keyboard{%d;%s;%b}", getPrice(), getName(), isRgb());
    }
}
