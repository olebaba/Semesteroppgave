package org.datavelger.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaverCsv implements FileSaver {
    @Override
    public void saveFile(Computer computer, boolean semicolon) throws IOException {
        String delimiter = ",";
        if(semicolon) delimiter = ";";


        File file = new File("file.csv");
        FileWriter writer = new FileWriter(file);
        writer.append(computer.getGraphicsCard().getName()).append(delimiter);
        writer.append(computer.getHarddrive().getName()).append(delimiter);
        writer.append(computer.getKeyboard().getName()).append(delimiter);
        writer.append(computer.getMemory().getName()).append(delimiter);
        writer.append(computer.getMonitor().getName()).append(delimiter);
        writer.append(computer.getMotherboard().getName()).append(delimiter);
        writer.append(computer.getMouse().getName()).append(delimiter);
        writer.append(computer.getProcessor().getName()).append(delimiter);
        writer.close();
    }
}
