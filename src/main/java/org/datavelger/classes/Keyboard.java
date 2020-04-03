package org.datavelger.classes;

public class Keyboard extends Component {
    private boolean rgb;

    public Keyboard(int price, String name, boolean rgb) {
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
        return String.format("Keyboard{%d,%s,%b}", getPrice(), getName(), isRgb());
    }
}
