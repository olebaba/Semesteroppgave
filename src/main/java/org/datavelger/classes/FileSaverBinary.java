package org.datavelger.classes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileSaverBinary implements FileSaver {
    @Override
    public void saveFile(Parts parts, boolean semicolon) throws IOException {
        Path path = Paths.get("binaryfile.txt");

        try {
            Files.writeString(path, parts.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
