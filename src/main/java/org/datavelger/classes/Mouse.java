package org.datavelger.classes;

public class Mouse extends Component {
    boolean rgb;

    public Mouse(int price, String name, boolean rgb) {
        super(price, name);
        this.rgb = rgb;
    }
}
