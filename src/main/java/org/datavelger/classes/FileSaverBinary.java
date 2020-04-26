package org.datavelger.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileSaverBinary implements FileSaver {
    @Override
    public void saveFile(String filepath, Parts parts, boolean semicolon) throws IOException {
        Path path = Paths.get(filepath);

        try {
            if(!Files.exists(path)) Files.createFile(path);
            Files.writeString(path, parts.toString() + System.lineSeparator(), StandardOpenOption.APPEND);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
