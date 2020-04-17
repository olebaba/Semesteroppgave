package org.datavelger.classes;

import javafx.concurrent.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class FileOpenerCsv extends Task<String> implements FileOpener {
    private String path;
    private boolean semicolon;

    public FileOpenerCsv(String path, boolean semicolon){
        this.path = path;
        this.semicolon = semicolon;
    }

    @Override
    public String openFile(String path, boolean semicolon) throws IOException {
        String delimiter = ",";
        if(semicolon) delimiter = ";";

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(delimiter);
        StringBuilder out = new StringBuilder();
        while (scanner.hasNext()){
            out.append(scanner.next());
        }
        scanner.close();
        return out.toString();
    }

    @Override
    protected String call() throws Exception {
        try{
            Thread.sleep(3000); //vise at GUI blir slått av
        }catch (InterruptedException e){
        }
        String out;
        try {
            out = openFile(path, semicolon);
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("Fant ingen fil med det navnet.");
        }
        return out;
    }
}
