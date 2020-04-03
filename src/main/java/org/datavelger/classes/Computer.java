package org.datavelger.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.module.ModuleDescriptor;

public class Computer {
    private GraphicsCard graphicsCard;
    private Harddrive harddrive;
    private Keyboard keyboard;
    private Memory memory;
    private Monitor monitor;
    private Motherboard motherboard;
    private Mouse mouse;
    private Processor processor;

    public Computer(GraphicsCard graphicsCard, Harddrive harddrive, Keyboard keyboard, Memory memory, Monitor monitor,
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

    @Override
    public String toString() {
        return "Computer{" + graphicsCard.toString() +
                "," + harddrive.toString() +
                "," + keyboard.toString() +
                "," + memory.toString() +
                "," + monitor.toString() +
                "," + motherboard.toString() +
                "," + mouse.toString() +
                "," + processor.toString() +
                '}';
    }
}
