package org.datavelger.classes;

public class Validator {

    public String validateComponent(Component component){
        if (component.getClass().equals(GraphicsCard.class)) {

        }
        return "";
    }

    public static boolean isValidName(String name){
        return ((name != null)
                && (!name.equals(""))
                && (name.matches("[A-Za-z0-9]*")));
    }

    public static boolean isValidPrice(int price){
        return (price > 0);
    }
}
