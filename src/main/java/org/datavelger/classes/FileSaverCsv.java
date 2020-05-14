package org.datavelger.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileSaverCsv implements FileSaver {
    private final boolean semicolon;
    private final Order order;

    public FileSaverCsv(Order order, boolean semicolon){
        this.order = order;
        this.semicolon = semicolon;
    }

    @Override
    public void saveFile(String filepath) throws IOException {
        String delimiter = ",";
        if(semicolon) delimiter = ";";

        File file = new File(filepath);
        FileWriter writer = new FileWriter(file, true);
        Scanner scanner = new Scanner(file);
        if(!scanner.hasNext()){
            writer.append(String.format("Name%sGraphicscard%sHarddrive%sKeyboard%sMemory" +
                            "%sMonitor%sMotherboard%sMouse%sProcessor%sPriceTotal"
                    , delimiter, delimiter, delimiter, delimiter, delimiter, delimiter,
                    delimiter, delimiter, delimiter));
            scanner.close();
        }
        writer.append(System.lineSeparator());
        writer.append(order.getName()).append(delimiter);
        writer.append(order.getGraphicsCard()).append(delimiter);
        writer.append(order.getHarddrive()).append(delimiter);
        writer.append(order.getKeyboard()).append(delimiter);
        writer.append(order.getMemory()).append(delimiter);
        writer.append(order.getMonitor()).append(delimiter);
        writer.append(order.getMotherboard()).append(delimiter);
        writer.append(order.getMouse()).append(delimiter);
        writer.append(order.getProcessor()).append(delimiter);
        writer.append(String.valueOf(order.getPriceTotal()));
        writer.flush();
        writer.close();


    }
}
