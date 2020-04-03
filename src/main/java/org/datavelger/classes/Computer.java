package org.datavelger.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Computer {
    private GraphicsCard graphicsCard;
    private Harddrive harddrive;
    private Keyboard keyboard;
    private Memory memory;
    private Monitor monitor;
    private Motherboard motherboard;
    private Processor processor;
    private Mouse mouse;

    public Computer(GraphicsCard graphicsCard, Harddrive harddrive, Keyboard keyboard, Memory memory, Monitor monitor,
                    Motherboard motherboard, Processor processor, Mouse mouse) {
        this.graphicsCard = graphicsCard;
        this.harddrive = harddrive;
        this.keyboard = keyboard;
        this.memory = memory;
        this.monitor = monitor;
        this.motherboard = motherboard;
        this.processor = processor;
        this.mouse = mouse;
    }



    public void toCSV(boolean semicolon) throws IOException {
        String delimiter = ",";
        if(semicolon) delimiter = ";";

        File file = new File("resources/file.csv");
        FileWriter writer = new FileWriter(file);
        writer.append(graphicsCard.getName()).append(delimiter);
        writer.append(harddrive.getName()).append(delimiter);
        writer.append(keyboard.getName()).append(delimiter);
        writer.append(memory.getName()).append(delimiter);
        writer.append(monitor.getName()).append(delimiter);
        writer.append(motherboard.getName()).append(delimiter);
        writer.append(processor.getName()).append(delimiter);
        writer.append(mouse.getName()).append(delimiter);
    }
}
