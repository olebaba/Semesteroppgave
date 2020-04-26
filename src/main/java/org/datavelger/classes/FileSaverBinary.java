package org.datavelger.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileSaverBinary implements FileSaver {
    private final Component component;

    public FileSaverBinary(Component component){
        this.component = component;
    }

    @Override
    public void saveFile(String filepath) throws IOException {
        Path path = Paths.get(filepath);

        try {
            if(!Files.exists(path)) Files.createFile(path);
            Files.writeString(path, component.toString() + System.lineSeparator(), StandardOpenOption.APPEND);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
