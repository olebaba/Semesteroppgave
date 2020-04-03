package org.datavelger.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class FileOpenerCsv implements FileOpener {
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
}
