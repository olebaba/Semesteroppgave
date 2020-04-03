package org.datavelger.classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileOpenerBinary implements FileOpener {
    @Override
    public String openFile(String path, boolean semicolon) throws IOException {
        File file = new File(path);
        byte[] bytes = Files.readAllBytes(file.toPath());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes){
            sb.append((char) b);
        }
        return sb.toString();
    }
}
