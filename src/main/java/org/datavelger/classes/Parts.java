package org.datavelger.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.module.ModuleDescriptor;

public class Parts {
    private GraphicsCard graphicsCard;
    private Harddrive harddrive;
    private Keyboard keyboard;
    private Memory memory;
    private Monitor monitor;
    private Motherboard motherboard;
    private Mouse mouse;
    private Processor processor;

    public Parts(GraphicsCard graphicsCard, Harddrive harddrive, Keyboard keyboard, Memory memory, Monitor monitor,
                 Motherboard motherboard, Mouse mouse, Processor processor) {
        this.graphicsCard = graphicsCard;
        this.harddrive = harddrive;
        this.keyboard = keyboard;
        this.memory = memory;
        this.monitor = monitor;
        this.motherboard = motherboard;
        this.mouse = mouse;
        this.processor = processor;
    }

    public Parts(){

    }

    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(GraphicsCard graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public Harddrive getHarddrive() {
        return harddrive;
    }

    public void setHarddrive(Harddrive harddrive) {
        this.harddrive = harddrive;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public boolean isComplete(){ //sjekker om det er en komplett pc
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
        if (this.isComplete()) {
            System.out.println("Is complete");
            out.append("Computer{");
        }else out.append("Parts{");

        try {
            out.append(this.getGraphicsCard() == null ? "null," : this.getGraphicsCard()).
                    append(this.getHarddrive() == null ? "null," : this.getHarddrive()).
                    append(this.getKeyboard() == null ? "null," : this.getKeyboard()).
                    append(this.getMemory() == null ? "null," : this.getMemory()).
                    append(this.getMonitor() == null ? "null," : this.getMonitor()).
                    append(this.getMotherboard() == null ? "null," : this.getMotherboard()).
                    append(this.getMouse() == null ? "null," : this.getMouse()).
                    append(this.getProcessor() == null ? "null" : this.getProcessor());

        }catch (Exception e){
            e.printStackTrace();
        }
        out.append("}");
        return out.toString();
    }
}
