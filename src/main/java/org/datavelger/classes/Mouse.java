package org.datavelger.classes;

public class Mouse extends Component {
    boolean rgb;

    public Mouse(int price, String name, boolean rgb) {
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
    public String toString() {
        return String.format("Mouse{%d;%s;%b}", getPrice(), getName(), isRgb());
    }
}
