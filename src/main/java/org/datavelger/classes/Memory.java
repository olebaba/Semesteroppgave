package org.datavelger.classes;

public class Memory extends Component {
    private String speed;

    public Memory(int price, String name, String speed) {
        super(price, name);
        this.speed = speed;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return String.format("Memory{%d;%s;%s}", getPrice(), getName(), getSpeed());
    }
}
