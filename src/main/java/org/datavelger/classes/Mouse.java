package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

public class Mouse extends Component {
    private static final long serialVersionUID = 1L;
    boolean rgb;

    public Mouse(int price, String name, String compType, boolean rgb) throws InvalidNameException, InvalidPriceException {
        super(price, name, compType);
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
        return String.format("\"Mouse\":{" + super.toJSON() + ",\"Is RGB\":\"%b\"}", isRgb());
    }

    @Override
    public String toString(){
        return String.format("Mouse: " + super.toString() + ", has RGB = %b", isRgb());
    }
}
