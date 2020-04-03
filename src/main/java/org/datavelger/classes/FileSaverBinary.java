package org.datavelger.classes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSaverBinary implements FileSaver {
    @Override
    public void saveFile(Computer computer, boolean semicolon) throws IOException {
        Path path = Paths.get("binaryfile.txt");
        byte[] bytes = computer.toString().getBytes(StandardCharsets.UTF_8);

        try {
            Files.write(path, bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
