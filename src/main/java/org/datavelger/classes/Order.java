package org.datavelger.classes;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    List<Component> components = new ArrayList<>();
    private SimpleStringProperty name, graphicsCard, harddrive, keyboard, memory
            , monitor, motherboard, mouse, processor;
    private SimpleFloatProperty priceTotal;

    public Order(String name, GraphicsCard graphicsCard, Harddrive harddrive,
                 Keyboard keyboard, Memory memory, Monitor monitor,
                 Motherboard motherboard, Mouse mouse, Processor processor) {
        components.addAll(Arrays.asList(graphicsCard, harddrive, keyboard, memory,
                monitor, motherboard, mouse, processor));

        this.name = new SimpleStringProperty(name);
        this.graphicsCard = new SimpleStringProperty(graphicsCard.getName());
        this.harddrive = new SimpleStringProperty(harddrive.getName());
        this.keyboard = new SimpleStringProperty(keyboard.getName());
        this.memory = new SimpleStringProperty(memory.getName());
        this.monitor = new SimpleStringProperty(monitor.getName());
        this.motherboard = new SimpleStringProperty(motherboard.getName());
        this.mouse = new SimpleStringProperty(mouse.getName());
        this.processor = new SimpleStringProperty(processor.getName());
        this.priceTotal = new SimpleFloatProperty(graphicsCard.getPrice()+
                harddrive.getPrice()+keyboard.getPrice()+memory.getPrice()+
                monitor.getPrice()+motherboard.getPrice()+mouse.getPrice()+processor.getPrice());
    }

    public Order(){ }

    public List<Component> getComponents(){
        return components;
    }

    public float getPriceTotal(){
        return priceTotal.get();
    }

    public void setPriceTotal(float priceTotal){
        this.priceTotal = new SimpleFloatProperty(priceTotal);
    }

    public void setPriceTotal(){
        float price = 0;
        for (Component comp : components){
            price += comp.getPrice();
        }
        this.priceTotal = new SimpleFloatProperty(price);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getGraphicsCard() {
        return graphicsCard.get();
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = new SimpleStringProperty(graphicsCard);
    }

    public String getHarddrive() {
        return harddrive.get();
    }

    public void setHarddrive(String harddrive) {
        this.harddrive = new SimpleStringProperty(harddrive);
    }

    public String getKeyboard() {
        return keyboard.get();
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = new SimpleStringProperty(keyboard);
    }

    public String getMemory() {
        return memory.get();
    }

    public void setMemory(String memory) {
        this.memory = new SimpleStringProperty(memory);
    }

    public String getMonitor() {
        return monitor.get();
    }

    public void setMonitor(String monitor) {
        this.monitor = new SimpleStringProperty(monitor);
    }

    public String getMotherboard() {
        return motherboard.get();
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = new SimpleStringProperty(motherboard);
    }

    public String getMouse() {
        return mouse.get();
    }

    public void setMouse(String mouse) {
        this.mouse = new SimpleStringProperty(mouse);
    }

    public String getProcessor() {
        return processor.get();
    }

    public void setProcessor(String processor) {
        this.processor = new SimpleStringProperty(processor);
    }

    public boolean isComplete(){ //sjekker om det er en komplett pc (kanskje unødvendig)
        return this.getGraphicsCard() != null &&
                this.getHarddrive() != null &&
                this.getKeyboard() != null &&
                this.getMemory() != null &&
                this.getMonitor() != null &&
                this.getMotherboard() != null &&
                this.getMouse() != null &&
                this.getProcessor() != null;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        /*if (this.isComplete()) {
            System.out.println("Is complete");
            out.append("Computer{");
        }*/
        out.append("Order{");

        try {
            out.append(this.getGraphicsCard() == null ? "null{}" : this.getGraphicsCard()).
                    append(this.getHarddrive() == null ? "null{}" : this.getHarddrive()).
                    append(this.getKeyboard() == null ? "null{}" : this.getKeyboard()).
                    append(this.getMemory() == null ? "null{}" : this.getMemory()).
                    append(this.getMonitor() == null ? "null{}" : this.getMonitor()).
                    append(this.getMotherboard() == null ? "null{}" : this.getMotherboard()).
                    append(this.getMouse() == null ? "null{}" : this.getMouse()).
                    append(this.getProcessor() == null ? "null{}" : this.getProcessor());

        }catch (Exception e){
            e.printStackTrace();
        }
        out.append("}");
        return out.toString();
    }
}
