package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;

public class Mouse extends Component {
    boolean rgb;

    public Mouse(int price, String name, boolean rgb) throws InvalidNameException, InvalidPriceException {
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
        return String.format("\"Mouse\":{" + super.toJSON() + ",\"Is RGB\":\"%b\"}", isRgb());
    }
}
