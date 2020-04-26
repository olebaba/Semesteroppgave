package org.datavelger.classes;

import javafx.concurrent.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileSaverCsv implements FileSaver {
    @Override
    public void saveFile(Parts parts, boolean semicolon) throws IOException {
        String delimiter = ",";
        if(semicolon) delimiter = ";";

        File file = new File("file.csv");
        FileWriter writer = new FileWriter(file, true);
        Scanner scanner = new Scanner(file);
        if(!scanner.hasNext()){
            writer.append("Graphics,Harddrive,Keyboard,Memory,Monitor,Motherboard,Mouse,Processor");
            scanner.close();
        }
        writer.append(System.lineSeparator());
        writer.append(parts.getGraphicsCard().getName()).append(delimiter);
        writer.append(parts.getHarddrive().getName()).append(delimiter);
        writer.append(parts.getKeyboard().getName()).append(delimiter);
        writer.append(parts.getMemory().getName()).append(delimiter);
        writer.append(parts.getMonitor().getName()).append(delimiter);
        writer.append(parts.getMotherboard().getName()).append(delimiter);
        writer.append(parts.getMouse().getName()).append(delimiter);
        writer.append(parts.getProcessor().getName());
        writer.flush();
        writer.close();


    }
}
