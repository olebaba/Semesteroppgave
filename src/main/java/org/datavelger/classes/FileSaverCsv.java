package org.datavelger.classes;

import javafx.concurrent.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaverCsv implements FileSaver {
    @Override
    public void saveFile(Parts parts, boolean semicolon) throws IOException {
        String delimiter = ",";
        if(semicolon) delimiter = ";";


        File file = new File("file.csv");
        FileWriter writer = new FileWriter(file);
        writer.append(parts.toString());
        /*
        writer.append(parts.getGraphicsCard().getName()).append(delimiter);
        writer.append(parts.getHarddrive().getName()).append(delimiter);
        writer.append(parts.getKeyboard().getName()).append(delimiter);
        writer.append(parts.getMemory().getName()).append(delimiter);
        writer.append(parts.getMonitor().getName()).append(delimiter);
        writer.append(parts.getMotherboard().getName()).append(delimiter);
        writer.append(parts.getMouse().getName()).append(delimiter);
        writer.append(parts.getProcessor().getName()).append(delimiter);
        */
        writer.close();


    }
}
